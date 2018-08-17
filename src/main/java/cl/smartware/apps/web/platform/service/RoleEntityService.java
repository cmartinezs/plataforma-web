package cl.smartware.apps.web.platform.service;

import java.util.List;

import cl.smartware.apps.web.platform.repository.jpa.entity.RoleEntity;

public interface RoleEntityService {

	public List<RoleEntity> findAll();
}
