package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.smartware.apps.web.platform.repository.jdbc.dummy.DummyJDBCRepository;
import cl.smartware.apps.web.platform.utils.builders.MapBuilder;

@Service
public class MultiDatabaseServiceImpl implements MultiDatabaseService
{
	@Value("#{'${app.databases.contabilidad}'.split(',')}")
	private List<String> databasesContabilidad;

	@Value("#{'${app.databases.gestion}'.split(',')}")
	private List<String> databasesGestion;

	@Value("#{'${app.databases.remuneracion}'.split(',')}")
	private List<String> databasesRemuneracion;
	
	@Value("#{'${app.databases.plataforma-web}'.split(',')}")
	private List<String> databasesPlataformaWeb;

	private Map<WebPlatformModules, List<String>> databasesMap;
	
	@Autowired
	private DummyJDBCRepository dummyJDBCRepository;

	@PostConstruct
	private void init()
	{
		databasesMap = MapBuilder.<WebPlatformModules, List<String>>unordered()
				.put(WebPlatformModules.CONTABILIDAD, databasesContabilidad)
				.put(WebPlatformModules.GESTION, databasesGestion)
				.put(WebPlatformModules.REMUNERACION, databasesRemuneracion)
				.put(WebPlatformModules.TEST_MODULE, databasesPlataformaWeb)
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
		return dummyJDBCRepository.listOfTables();
	}
}
