package cl.smartware.apps.web.platform.repository.jdbc.dummy;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class DummyJDBCRepositoryImpl implements DummyJDBCRepository {
	@Autowired
	@Qualifier("dummyJdbcTemplate")
	JdbcTemplate dummyJdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listOfTables() {
		List<String> list = null;
		GetTableNames getTableNames = new GetTableNames();
		try {
			list = (List<String>) JdbcUtils.extractDatabaseMetaData(dummyJdbcTemplate.getDataSource(),
					getTableNames);
			System.out.println(list);
		} catch (MetaDataAccessException e) {
			System.out.println(e);
		}
		return list;
	}

	class GetTableNames implements DatabaseMetaDataCallback {

		public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
			ResultSet rs = dbmd.getTables(null, null, "%", new String[] { "TABLE" });
			List<String> list = new ArrayList<>();
			while (rs.next()) {
				list.add(rs.getString(3));
			}
			return list;
		}
	}
}
