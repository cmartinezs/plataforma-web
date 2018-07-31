package cl.smartware.apps.web.platform.repository.jpa.crud;

import org.springframework.data.repository.CrudRepository;

import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;

public interface UserEntityCrudRepository extends CrudRepository<UserEntity, Integer>
{
	UserEntity findByUsername(String username);
}
		