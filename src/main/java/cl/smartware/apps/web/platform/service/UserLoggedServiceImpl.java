package cl.smartware.apps.web.platform.service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

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
