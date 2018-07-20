package cl.smartware.apps.web.platform.service;

import java.util.List;

public interface MultiDatabaseService
{
	List<String> getDatabasesListFromModule(String module);
	
	public List<String> getDatabasesListFromModule(WebPlatformModules module);
}
