package cl.smartware.apps.web.platform.repository.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cl.smartware.apps.web.platform.repository.entity.RoleEntity;

public interface RoleEntityCrudRepository extends CrudRepository<RoleEntity, Integer>
{
	List<RoleEntity> findByRoleUsersUserId(Integer id);
}
