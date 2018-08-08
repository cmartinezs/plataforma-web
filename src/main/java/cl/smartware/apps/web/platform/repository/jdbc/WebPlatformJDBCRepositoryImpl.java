package cl.smartware.apps.web.platform.repository.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import cl.smartware.apps.web.platform.configuration.databases.ContabillityProperties;
import cl.smartware.apps.web.platform.configuration.databases.ManagementProperties;
import cl.smartware.apps.web.platform.configuration.databases.RemunerationProperties;

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
	public List<Map<String, Object>> getAllRegistresFromTable(String database, String tableName) 
	{
		LOGGER.info(MessageFormat.format("Get registers for {0} in {1}", tableName, database));
		
		return getJdbcTemplateFromDataBaseName(database).queryForList("SELECT * FROM " + tableName);
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
}
