package cl.smartware.apps.web.platform.repository.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import cl.smartware.apps.web.platform.repository.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.entity.enums.ManagementTypes;

@Entity
@Table(name="file")
public class FileEntity implements EntityBase
{
	private static final long serialVersionUID = 2494111458883818575L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "NAME", length = 30, nullable = false)
	private String name;
	
	@Column(name = "FILE_NAME", length = 50, nullable = false)
	private String fileName;
	
	@Column(name = "CONTENT_TYPE", length = 50, nullable = false)
	private String contentType;
	
	@Lob
    @Column(name = "FILE", columnDefinition="BLOB")
    private byte[] file;
	
	@Column(name = "SIZE", nullable = false)
	private Long size;
	
	@Column(name =  "ANIO", length = 4, nullable = false)
	private Integer anio;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", length = 4, nullable = false, columnDefinition = "ENUM('PDF', 'XLS', 'XLSX')")
	private FileTypes type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MANMAGEMENT", length = 10, nullable = false, columnDefinition = "ENUM('CONTABLE', 'RRHH', 'TESORERIA')")
	private ManagementTypes management;
	
	@Column(name = "ENTERPRISE", length = 100, updatable = false, nullable = false)
	private String enterprise;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CREATED_BY")
	private UserEntity createdBy;

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

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
	 * @return the fileName
	 */
	public String getFileName()
	{
		return this.fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType()
	{
		return this.contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String fileExtension)
	{
		this.contentType = fileExtension;
	}

	/**
	 * @return the file
	 */
	public byte[] getFile()
	{
		return this.file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(byte[] file)
	{
		this.file = file;
	}

	/**
	 * @return the size
	 */
	public Long getSize()
	{
		return this.size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size)
	{
		this.size = size;
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
	public void setEnterprise(String empresa)
	{
		this.enterprise = empresa;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive()
	{
		return this.active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active)
	{
		this.active = active;
	}

	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt()
	{
		return this.createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt)
	{
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdBy
	 */
	public UserEntity getCreatedBy()
	{
		return this.createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(UserEntity createdBy)
	{
		this.createdBy = createdBy;
	}
}
