package cl.smartware.apps.web.platform.service;

import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;

public interface UserLoggedService
{
	public UserEntity getUserEntity();
	
	public void setUserEntity(UserEntity userEntity);
	
	public boolean isAdmin();
}
