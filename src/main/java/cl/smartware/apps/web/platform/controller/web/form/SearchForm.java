package cl.smartware.apps.web.platform.controller.web.form;

import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;

public class SearchForm
{
	private String name;
	
	private Integer anio;
	
	private FileTypes type;
	
	private ManagementTypes management;
	
	private String enterprise;

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the anio
	 */
	public Integer getAnio()
	{
		return this.anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(Integer anio)
	{
		this.anio = anio;
	}

	/**
	 * @return the type
	 */
	public FileTypes getType()
	{
		return this.type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(FileTypes type)
	{
		this.type = type;
	}

	/**
	 * @return the management
	 */
	public ManagementTypes getManagement()
	{
		return this.management;
	}

	/**
	 * @param management the management to set
	 */
	public void setManagement(ManagementTypes management)
	{
		this.management = management;
	}

	/**
	 * @return the enterprise
	 */
	public String getEnterprise()
	{
		return this.enterprise;
	}

	/**
	 * @param enterprise the enterprise to set
	 */
	public void setEnterprise(String enterprise)
	{
		this.enterprise = enterprise;
	}
}
