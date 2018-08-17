package cl.smartware.apps.web.platform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.smartware.apps.web.platform.repository.jpa.crud.RoleEntityCrudRepository;
import cl.smartware.apps.web.platform.repository.jpa.entity.RoleEntity;

@Service
public class RoleEntityServiceImpl implements RoleEntityService
{
	@Autowired
	private RoleEntityCrudRepository roleEntityCrudRepository;

	public List<RoleEntity> findAll()
	{
		List<RoleEntity> list = new ArrayList<>();
		roleEntityCrudRepository.findAll().forEach(list::add);
		return list;
	}
}
