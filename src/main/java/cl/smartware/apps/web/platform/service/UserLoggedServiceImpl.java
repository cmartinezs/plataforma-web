package cl.smartware.apps.web.platform.service;

import org.springframework.stereotype.Service;

import cl.smartware.apps.web.platform.repository.entity.UserEntity;

@Component
@SessionScope
public class UserLoggedServiceImpl implements UserLoggedService
{
	private UserEntity userEntity;

	@Override
	public UserEntity getUserEntity()
	{
		return userEntity;
	}

	@Override
	public void setUserEntity(UserEntity userEntity)
	{
		this.userEntity = userEntity;
	}
}
