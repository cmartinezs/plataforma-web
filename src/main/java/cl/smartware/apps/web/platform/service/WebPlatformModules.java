package cl.smartware.apps.web.platform.service;

public enum WebPlatformModules
{
	CONTABILIDAD("Contabilidad")
	, GESTION("Gestión")
	, REMUNERACION("Remuneración")
	, TEST_MODULE("Módulo de prueba")
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
