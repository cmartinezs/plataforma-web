package cl.smartware.apps.web.platform.service;

import java.util.List;
import java.util.Optional;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;
import cl.smartware.apps.web.platform.service.exception.FileEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.FoundRegisterFileEntityServiceException;

public interface FilerEntityService
{
	public FileEntity save(FileEntity fileEntity);

	public FileEntity save(FileForm fileForm) throws FileEntityServiceException, FoundRegisterFileEntityServiceException;

	public FileEntity formToEntity(FileForm fileForm) throws FileEntityServiceException;
	
	public List<FileEntity> findByNameOrAnioOrTypeOrManagementOrEnterprise(String name, Integer anio, FileTypes type, ManagementTypes management, String enterprise);

	public Optional<FileEntity> findByName(String name);
}