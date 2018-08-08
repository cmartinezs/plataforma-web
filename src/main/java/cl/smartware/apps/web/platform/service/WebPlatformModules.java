package cl.smartware.apps.web.platform.service;

public enum WebPlatformModules
{
	CONTABILITY("Contabilidad")
	, MANAGEMENT("Gestión")
	, REMUNERATION("Remuneración")
	, UNKNOWN("Desconocido");
	
	private String moduleName;
	
	WebPlatformModules(String name)
	{
		this.moduleName= name;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName()
	{
		return this.moduleName;
	}
}
