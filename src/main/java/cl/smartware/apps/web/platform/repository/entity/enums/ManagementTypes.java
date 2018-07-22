package cl.smartware.apps.web.platform.repository.entity.enums;

public enum ManagementTypes
{
	CONTABLE("Contable"), RRHH("Recursos Humanos (RRHH)"), TESORERIA("Tesorería");
	
	private String value;
	
	ManagementTypes(String name)
	{
		this.value = name;
	}

	/**
	 * @return the value
	 */
	public String getName()
	{
		return this.value;
	}

	/**
	 * @param value the value to set
	 */
	public void setName(String name)
	{
		this.value = name;
	}
	
	
}
