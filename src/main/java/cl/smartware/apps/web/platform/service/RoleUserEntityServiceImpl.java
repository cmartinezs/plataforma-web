package cl.smartware.apps.web.platform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.smartware.apps.web.platform.repository.jpa.crud.RoleUserEntityCrudRepository;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleUserEntity.RoleUserId;

@Service
public class RoleUserEntityServiceImpl implements RoleUserEntityService 
{
	@Autowired
	private RoleUserEntityCrudRepository roleUserEntityCrudRepository;
	
	@Override
	public Optional<RoleUserEntity> findById(RoleUserId id) 
	{
		return roleUserEntityCrudRepository.findById(id);
	}

	@Override
	public RoleUserEntity save(RoleUserEntity newRoleUserEntity) 
	{
		return  roleUserEntityCrudRepository.save(newRoleUserEntity);
	}

	@Override
	public void delete(RoleUserEntity roleUser) 
	{
		roleUserEntityCrudRepository.delete(roleUser);
	}
}
