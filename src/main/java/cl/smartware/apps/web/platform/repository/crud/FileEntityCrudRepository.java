package cl.smartware.apps.web.platform.repository.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.smartware.apps.web.platform.repository.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.entity.enums.ManagementTypes;

public interface FileEntityCrudRepository extends CrudRepository<FileEntity, Integer>
{
	public List<FileEntity> findByNameOrAnioOrTypeOrManagementOrEnterprise(String name, Integer anio, FileTypes type, ManagementTypes management, String enterprise);
	
	public Optional<FileEntity> findByName(String name);
}
		