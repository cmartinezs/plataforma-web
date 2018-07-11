package cl.smartware.apps.web.platform.repository.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins
{
	@Id
	@Column(name = "series", length = 64)
	private String series;

	@Column(name = "username", length = 64)
	private String username;
	
	@Column(name = "token", length = 64)
	private String token;
	
	@Column(name = "last_used")
	private Timestamp lastUsed;
}
