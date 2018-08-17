package cl.smartware.apps.web.platform.repository.jpa.crud;

import org.springframework.data.repository.CrudRepository;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity.RoleUserId;

public interface RoleUserEntityCrudRepository extends CrudRepository<RoleUserEntity, RoleUserId>
{

}
