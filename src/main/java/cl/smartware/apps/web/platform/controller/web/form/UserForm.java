package cl.smartware.apps.web.platform.controller.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import cl.smartware.apps.web.platform.controller.web.form.validator.password.ValidConfirmPassword;

@ValidConfirmPassword
public class UserForm {

	private Integer id;
	
	@NotNull(message = "Debe ingresar un nombre de usuario")
	@NotEmpty(message = "Debe ingresar un nombre de usuario")
	private String username;
	
	private String password;
	
	private String confirmPassword;
	
	@NotNull(message = "Debe ingresar un email")
	@NotEmpty(message = "Debe ingresar un email")
	@Email(message = "Debe ingresar un mail válido")
	private String email;
	
	@NotNull(message = "Debe seleccionar un rol")
	private Integer roleId;
	
	private boolean active = true;
	
	private String createdAt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
