package cl.smartware.apps.web.platform.repository.jdbc;

import java.util.List;
import java.util.Map;

import cl.smartware.apps.web.platform.repository.jdbc.WebPlatformJDBCRepositoryImpl.PagedResult;

public interface WebPlatformJDBCRepository {

	public List<String> listOfTables(String database);

	public List<Map<String, Object>> getAll(String database, String tableName);
	
	public PagedResult getPagedAll(String database, String tableName, Integer page, Integer pageSize);

	public List<String> getColumns(String database, String table);
}
