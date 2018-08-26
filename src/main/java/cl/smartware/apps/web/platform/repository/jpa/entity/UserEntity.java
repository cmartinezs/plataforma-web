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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "WP_USER", uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME"}))
public class UserEntity implements EntityBase
{
	private static final long serialVersionUID = -5731260521355518321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private Integer id;

	@Column(name = "USERNAME", length = 30, updatable = false, nullable = false, unique = true)
	private String username;

	@Column(name = "PASSWORD", length = 60, nullable = false)
	private String password;

	@Column(name = "EMAIL", length = 100, nullable = true)
	private String email;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<RoleUserEntity> roleUsers = new ArrayList<>();

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
