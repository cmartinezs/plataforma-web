package cl.smartware.apps.web.platform.service;

import java.util.Optional;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity.RoleUserId;

public interface RoleUserEntityService 
{
	public Optional<RoleUserEntity> findById(RoleUserId id);

	public RoleUserEntity save(RoleUserEntity newRoleUserEntity);

	public void delete(RoleUserEntity roleUser);
}
