package cl.smartware.apps.web.platform.repository.jpa.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "role_user")
public class RoleUserEntity implements EntityBase
{
	private static final long serialVersionUID = -2484249599382362060L;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")  
	private UserEntity user;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CREATED_BY")
	private UserEntity createdBy;

	/**
	 * @return the user
	 */
	public UserEntity getUser()
	{
		return this.user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user)
	{
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public RoleEntity getRole()
	{
		return this.role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleEntity role)
	{
		this.role = role;
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
