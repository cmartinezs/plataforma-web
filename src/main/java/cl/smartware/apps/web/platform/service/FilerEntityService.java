package cl.smartware.apps.web.platform.service;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.repository.entity.FileEntity;
import cl.smartware.apps.web.platform.service.exeption.FileEntityServiceException;

public interface FilerEntityService
{
	public FileEntity save(FileEntity fileEntity);
	
	public FileEntity save(FileForm fileForm) throws FileEntityServiceException;
	
	public FileEntity formToEntity(FileForm fileForm) throws FileEntityServiceException;
}
