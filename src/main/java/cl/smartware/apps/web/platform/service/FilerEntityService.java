package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Optional;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.repository.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.entity.enums.ManagementTypes;
import cl.smartware.apps.web.platform.service.exeption.FileEntityServiceException;
import cl.smartware.apps.web.platform.service.exeption.FoundRegisterFileEntityServiceException;

public interface FilerEntityService
{
	public FileEntity save(FileEntity fileEntity);

	public FileEntity save(FileForm fileForm) throws FileEntityServiceException, FoundRegisterFileEntityServiceException;

	public FileEntity formToEntity(FileForm fileForm) throws FileEntityServiceException;
	
	public List<FileEntity> findByNameOrAnioOrTypeOrManagementOrEnterprise(String name, Integer anio, FileTypes type, ManagementTypes management, String enterprise);

	public Optional<FileEntity> findByName(String name);
}