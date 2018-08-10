package cl.smartware.apps.web.platform.repository.jpa.entity.enums;

public enum ManagementTypes
{
	SELECT("-- Seleccione --"), CONTABLE("Contable"), RRHH("Recursos Humanos (RRHH)"), TESORERIA("Tesorería");
	
	private String value;
	
	ManagementTypes(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return this.value;
	}
}
