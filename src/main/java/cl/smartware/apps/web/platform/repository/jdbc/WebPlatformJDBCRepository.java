package cl.smartware.apps.web.platform.repository.jdbc;

import java.util.List;
import java.util.Map;

public interface WebPlatformJDBCRepository {

	public List<String> listOfTables(String database);

	public List<Map<String, Object>> getAllRegistresFromTable(String database, String tableName);

	public List<String> getColumns(String database, String table);
}
