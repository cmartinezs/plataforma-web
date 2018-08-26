package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Map;

import cl.smartware.apps.web.platform.repository.jdbc.WebPlatformJDBCRepositoryImpl.PagedResult;

public interface MultiDatabaseService
{
	List<String> getDatabasesListFromModule(String module);
	
	public List<String> getDatabasesListFromModule(WebPlatformModules module);
	
	public List<String> getTableList(String database);

	public List<Map<String, Object>> getAll(String database, String table);
	
	public PagedResult getPagedAll(String database, String table, Integer page, Integer pageSize);

	public List<String> getColumns(String database, String table);

	boolean isValidDatabase(WebPlatformModules webPlatformModule, String database);
}
