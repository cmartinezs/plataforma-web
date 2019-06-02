package cl.smartware.apps.web.platform.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleEntity.ERole;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;

@Component
@SessionScope
public class UserLoggedServiceImpl implements UserLoggedService
{
	private UserEntity userEntity;

	private Optional<Boolean> isAdmin;

	@PostConstruct
	private void init()
	{
		isAdmin = Optional.<Boolean>empty();
	}

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

	@Override
	public boolean isAdmin()
	{
		if (!isAdmin.isPresent())
		{
			isAdmin = Optional.of(false);
			
			for (RoleUserEntity roleuser : userEntity.getRoleUsers())
			{
				if (roleuser.getRole().getName().equalsIgnoreCase(ERole.ROLE_ADMIN.name()))
				{
					isAdmin = Optional.of(true);
					break;
				}
			}
		}

		return userEntity != null ? isAdmin.get() : false;
	}
}
