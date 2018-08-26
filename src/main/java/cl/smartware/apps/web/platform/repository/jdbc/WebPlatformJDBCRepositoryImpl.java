package cl.smartware.apps.web.platform.repository.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import cl.smartware.apps.web.platform.configuration.databases.jdbc.ContabillityProperties;
import cl.smartware.apps.web.platform.configuration.databases.jdbc.ManagementProperties;
import cl.smartware.apps.web.platform.configuration.databases.jdbc.RemunerationProperties;

@Repository
public class WebPlatformJDBCRepositoryImpl implements WebPlatformJDBCRepository 
{
	@Autowired
	private ContabillityProperties contabillityProperties;
	
	@Autowired
	private ManagementProperties managementProperties;
	
	@Autowired
	private RemunerationProperties remunerationProperties;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebPlatformJDBCRepositoryImpl.class);
	
	private static final String JDBC_TEMPLATE_SUFIX = "JDCBTemplate";
	
	@Autowired
	private Map<String, JdbcTemplate> jdbcTemplatesMap;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listOfTables(String database) 
	{
		LOGGER.info(MessageFormat.format("List of tables for {0}", database));
		
		List<String> list = null;
		
		try 
		{
			JdbcTemplate jdbcTemplate = getJdbcTemplateFromDataBaseName(database);
			
			list = (List<String>) JdbcUtils.extractDatabaseMetaData(jdbcTemplate.getDataSource(),  new GetTableNames());
			System.out.println(list);
		} 
		catch (MetaDataAccessException e)
		{
			System.out.println(e);
		}
		
		return list;
	}

	private JdbcTemplate getJdbcTemplateFromDataBaseName(String database) 
	{
		String prefix = databasesMapToJdbcTemplatePrefix(database);
		
		LOGGER.info(MessageFormat.format("Prefix for {0}: {1}", database, prefix));
		
		JdbcTemplate jdbcTemplate = jdbcTemplatesMap.get(prefix+JDBC_TEMPLATE_SUFIX);
		
		LOGGER.info(MessageFormat.format("JdbcTemplate for {0}: {1}", database, jdbcTemplate));
		
		return jdbcTemplate;
	}

	class GetTableNames implements DatabaseMetaDataCallback 
	{
		public Object processMetaData(DatabaseMetaData dbmd) throws SQLException 
		{
			ResultSet rs = dbmd.getTables(null, null, "%", new String[] { "TABLE" });
			
			List<String> list = new ArrayList<>();
			
			while (rs.next()) 
			{
				list.add(rs.getString(3));
			}
			
			return list;
		}
	}

	@Override
	public List<Map<String, Object>> getAll(String database, String tableName) 
	{
		LOGGER.info(MessageFormat.format("Get registers for {0} in {1}", tableName, database));
		
		return getJdbcTemplateFromDataBaseName(database).queryForList("SELECT * FROM " + tableName);
	}
	
	@Override
	public PagedResult getPagedAll(String database, String tableName, Integer page, Integer pageSize) 
	{
		LOGGER.info(MessageFormat.format("Get registers for {0} in {1}", tableName, database));
		
		Integer count = getJdbcTemplateFromDataBaseName(database)
				.queryForObject("SELECT COUNT(*) FROM " + tableName, Integer.class);
		
		
		Integer totalPage = count / pageSize;
		Integer mod = count % pageSize;
		
		if(mod > 0)
		{
			totalPage++;
		}
		
		if(page > totalPage)
		{
			page = totalPage;
		}
		
		Integer maxRow = pageSize * page;
		Integer minRow = 1 + maxRow - pageSize;
		
		StringBuilder sbQuery = new StringBuilder()
				.append("SELECT * ")
				.append("FROM ")
					.append("( ")
						.append("SELECT ROW_NUMBER() OVER ( ORDER BY ORD_ ) AS NUM_FILA, * ")
						.append("FROM ")
							.append("( SELECT 0 AS ORD_, * FROM ").append(tableName).append(" ) ROW_TABLE")
					.append(") AS RESULT_TABLE ")
				.append("WHERE NUM_FILA >= ").append(minRow).append(" ")
					.append("AND NUM_FILA <= ").append(maxRow).append(" ")
				.append("ORDER BY NUM_FILA");
		
		List<Map<String, Object>> result = getJdbcTemplateFromDataBaseName(database).queryForList(sbQuery.toString());
		
		List<Map<String, Object>> newResult = new LinkedList<>();
		
		result.forEach(map -> 
		{
			Map<String, Object> newMap = new LinkedCaseInsensitiveMap<>();
			
			map.forEach((key, value) -> 
			{
				if(!"ORD_".equalsIgnoreCase(key))
				{
					newMap.put(key, value);
				}
			});
			
			newResult.add(newMap);
		});
		
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setPageSize(pageSize);
		pagedResult.setResult(newResult);
		pagedResult.setTotalPage(totalPage);
		
		return pagedResult;
	}
	
	private String databasesMapToJdbcTemplatePrefix(String database)
	{
		String jdbcTemplate = contabillityProperties.getProperty(database);
		
		if(jdbcTemplate == null)
		{
			jdbcTemplate = managementProperties.getProperty(database);
		}
		
		if(jdbcTemplate == null)
		{
			jdbcTemplate = remunerationProperties.getProperty(database);
		}
		
		LOGGER.info(MessageFormat.format("{0} map to {1}", database, jdbcTemplate));
		
		return jdbcTemplate;
	}

	@Override
	public List<String> getColumns(String database, String tableName) 
	{
		LOGGER.info(MessageFormat.format("Get columns for {0} in {1}", tableName, database));
		
		JdbcTemplate jdbcTemplate = getJdbcTemplateFromDataBaseName(database);

		TableMetaDataContext tableMetadataContext = new TableMetaDataContext();
	    tableMetadataContext.setTableName(tableName);
	    tableMetadataContext.processMetaData(jdbcTemplate.getDataSource(), Collections.<String>emptyList(), new String[0]);
		return tableMetadataContext.getTableColumns();
	}
	
	public class PagedResult
	{
		private Integer page;
		private Integer pageSize;
		private Integer totalPage;
		private Integer startPage;
		private Integer endPage;
		private List<Map<String, Object>> result;
		
		public void calculateStartAndEndPage()
		{
			startPage = page - 2;
			
			if(startPage <= 1)
			{
				startPage = 2;
			}

			endPage = page + 2;
			
			if(endPage >= totalPage)
			{
				endPage = totalPage - 1;
			}
			
			if(startPage >= totalPage || endPage <= 1)
			{
				startPage = 0;
				endPage = 0;
			}
		}
		
		public Integer getPage() {
			return page;
		}
		public void setPage(Integer page) {
			this.page = page;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public Integer getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(Integer totalSize) {
			this.totalPage = totalSize;
		}
		public List<Map<String, Object>> getResult() {
			return result;
		}
		public void setResult(List<Map<String, Object>> result) {
			this.result = result;
		}
		public Integer getStartPage() {
			return startPage;
		}
		public Integer getEndPage() {
			return endPage;
		}
	}
}
