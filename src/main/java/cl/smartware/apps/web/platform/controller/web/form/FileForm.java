package cl.smartware.apps.web.platform.controller.web.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import cl.smartware.apps.web.platform.controller.web.form.validator.file.ValidFile;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;

public class FileForm
{
	@NotNull(message = "Debe ingresar un nombre")
	@NotBlank(message = "Debe ingresar un nombre")
	@Size(max = 50, message = "El largo del nombre no debe superar los 50 caracteres")
	private String name;
	
	@NotNull(message = "Debe seleccionar un archivo")
	@ValidFile(maxSize = 5242880, contentType = { "application/pdf", "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}) //5Mb
    private MultipartFile file;
	
	@NotNull(message = "Debe ingresar un año")
	@Digits(integer = 4, fraction = 0,  message = "El largo del año debe ser de 4 digitos")
	private Integer anio;
	
	@NotNull(message = "Debe seleccionar un tipo de archivo")
	private FileTypes type;
	
	@NotNull(message = "Debe seleccionar un tipo de gestión")
	private ManagementTypes management;
	
	@NotNull(message = "Debe ingresar un nombre de empresa")
	@NotBlank(message = "Debe ingresar un nombre de empresa")
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
	 * @return the file
	 */
	public MultipartFile getFile()
	{
		return this.file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file)
	{
		this.file = file;
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
