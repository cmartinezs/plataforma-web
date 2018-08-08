package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.smartware.apps.web.platform.repository.jdbc.WebPlatformJDBCRepository;
import cl.smartware.apps.web.platform.utils.builders.MapBuilder;

@Service
public class MultiDatabaseServiceImpl implements MultiDatabaseService
{
	@Value("#{'${app.databases.contability}'.split(',')}")
	private List<String> contabilityDatabases;

	@Value("#{'${app.databases.management}'.split(',')}")
	private List<String> managementDatabases;

	@Value("#{'${app.databases.remuneration}'.split(',')}")
	private List<String> remunerationDatabases;

	private Map<WebPlatformModules, List<String>> databasesMap;
	
	@Autowired
	private WebPlatformJDBCRepository webPlatformJDBCRepository;

	@PostConstruct
	private void init()
	{
		databasesMap = MapBuilder.<WebPlatformModules, List<String>>unordered()
				.put(WebPlatformModules.CONTABILITY, contabilityDatabases)
				.put(WebPlatformModules.MANAGEMENT, managementDatabases)
				.put(WebPlatformModules.REMUNERATION, remunerationDatabases)
				.build();
	}

	@Override
	public List<String> getDatabasesListFromModule(String module)
	{
		return getDatabasesListFromModule(WebPlatformModules.valueOf(module));
	}
	
	public List<String> getDatabasesListFromModule(WebPlatformModules module)
	{
		return databasesMap.get(module);
	}

	@Override
	public List<String> getTableList(String database) {
		return webPlatformJDBCRepository.listOfTables(database);
	}

	@Override
	public List<Map<String, Object>> getRegistres(String database, String tableName) {
		return webPlatformJDBCRepository.getAllRegistresFromTable(database, tableName);
	}

	@Override
	public List<String> getColumns(String database, String table) {
		return webPlatformJDBCRepository.getColumns(database, table);
	}
}
