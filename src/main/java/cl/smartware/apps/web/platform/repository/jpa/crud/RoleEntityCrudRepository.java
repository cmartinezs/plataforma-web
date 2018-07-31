package cl.smartware.apps.web.platform.repository.jpa.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleEntity;

public interface RoleEntityCrudRepository extends CrudRepository<RoleEntity, Integer>
{
	List<RoleEntity> findByRoleUsersUserId(Integer id);
}
