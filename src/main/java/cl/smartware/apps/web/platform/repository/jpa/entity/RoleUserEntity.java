package cl.smartware.apps.web.platform.repository.jpa.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "WP_ROLE_USER")
public class RoleUserEntity implements EntityBase
{
	private static final long serialVersionUID = -2484249599382362060L;
	
	@Embeddable
	public static class RoleUserId implements Serializable
	{
		private static final long serialVersionUID = -1599561875174685442L;
		
	    @Column(name = "USER_ID")  
		private Integer userId;

		@Column(name = "ROLE_ID")
		private Integer roleId;

		/**
		 * @return the userId
		 */
		public Integer getUserId() {
			return userId;
		}

		/**
		 * @param userId the userId to set
		 */
		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		/**
		 * @return the roleId
		 */
		public Integer getRoleId() {
			return roleId;
		}

		/**
		 * @param roleId the roleId to set
		 */
		public void setRoleId(Integer roleId) {
			this.roleId = roleId;
		}
	}

	@EmbeddedId
	private RoleUserId roleUserId;
	
	@ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)  
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private RoleEntity role;

	@Column(name = "ACTIVE", nullable = false)
	private Boolean active = true;

	@Column(name = "CREATED_AT", updatable = false, nullable = false)
	@CreationTimestamp
	private Timestamp createdAt = new Timestamp(Calendar.getInstance().getTimeInMillis());

	public RoleUserId getRoleUserId() {
		return roleUserId;
	}

	public void setRoleUserId(RoleUserId roleUserId) {
		this.roleUserId = roleUserId;
	}
	
	/**
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

	/**
	 * @return the role
	 */
	public RoleEntity getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleEntity role) {
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
}
