package cl.smartware.apps.web.platform.repository.jpa.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user")
public class UserEntity implements EntityBase
{
	private static final long serialVersionUID = -5731260521355518321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Integer id;

	@Column(name = "USERNAME", length = 30, updatable = false, nullable = false)
	private String username;

	@Column(name = "PASSWORD", length = 60, nullable = false)
	private String password;

	@Column(name = "EMAIL", length = 100, nullable = true)
	private String email;

	@Column(name = "COMMUNITY_ID", nullable = true)
	private Integer communityId;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "CREATED_BY")
	private UserEntity userCreatedBy;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<RoleUserEntity> roleUsers = new ArrayList<>();

	@OneToMany(mappedBy = "roleCreatedBy", orphanRemoval = true)
	private List<RoleEntity> createdRoles = new ArrayList<>();

	@OneToMany(mappedBy = "userCreatedBy", orphanRemoval = true)
	private List<UserEntity> createdUsers = new ArrayList<>();

	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return this.username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the communityId
	 */
	public Integer getComunityId()
	{
		return this.communityId;
	}

	/**
	 * @param communityId
	 *            the communityId to set
	 */
	public void setComunityId(Integer comunityId)
	{
		this.communityId = comunityId;
	}

	/**
	 * @return the active
	 */
	public Boolean getActive()
	{
		return this.active;
	}

	/**
	 * @param active
	 *            the active to set
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
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt)
	{
		this.createdAt = createdAt;
	}

	/**
	 * @return the userCreatedBy
	 */
	public UserEntity getCreatedBy()
	{
		return this.userCreatedBy;
	}

	/**
	 * @param userCreatedBy
	 *            the userCreatedBy to set
	 */
	public void setCreatedBy(UserEntity createdBy)
	{
		this.userCreatedBy = createdBy;
	}

	/**
	 * @return the createdRoles
	 */
	public List<RoleEntity> getCreatedRoles()
	{
		return this.createdRoles;
	}

	/**
	 * @return the communityId
	 */
	public Integer getCommunityId()
	{
		return this.communityId;
	}

	/**
	 * @param communityId
	 *            the communityId to set
	 */
	public void setCommunityId(Integer communityId)
	{
		this.communityId = communityId;
	}

	/**
	 * @return the roleUsers
	 */
	public List<RoleUserEntity> getRoleUsers()
	{
		return this.roleUsers;
	}

	/**
	 * @param roleUsers
	 *            the roleUsers to set
	 */
	public void setRoleUsers(List<RoleUserEntity> roleUsers)
	{
		this.roleUsers = roleUsers;
	}

	/**
	 * @param createdRoles
	 *            the createdRoles to set
	 */
	public void setCreatedRoles(List<RoleEntity> createdRoles)
	{
		this.createdRoles = createdRoles;
	}

	/**
	 * @return the createdUsers
	 */
	public List<UserEntity> getCreatedUsers()
	{
		return this.createdUsers;
	}

	/**
	 * @param createdUsers
	 *            the createdUsers to set
	 */
	public void setCreatedUsers(List<UserEntity> createdUsers)
	{
		this.createdUsers = createdUsers;
	}
	
	public UserEntity removeRoleUser(RoleUserEntity roleUserEntity)
	{
		this.roleUsers.remove(roleUserEntity);
		return this;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity userEntity = (UserEntity) o;
		return Objects.equals(id, userEntity.id);
	}
}
