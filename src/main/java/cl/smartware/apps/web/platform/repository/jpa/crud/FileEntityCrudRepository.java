package cl.smartware.apps.web.platform.repository.jpa.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;

public interface FileEntityCrudRepository extends CrudRepository<FileEntity, Integer>
{
	public List<FileEntity> findByNameOrAnioOrTypeOrManagementOrEnterprise(String name, Integer anio, FileTypes type, ManagementTypes management, String enterprise);
	
	public Optional<FileEntity> findByName(String name);
	
	public List<FileEntity> findByCreatedById(Integer id);
}
		