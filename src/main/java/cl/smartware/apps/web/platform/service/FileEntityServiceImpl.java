package cl.smartware.apps.web.platform.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.repository.crud.FileEntityCrudRepository;
import cl.smartware.apps.web.platform.repository.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.entity.enums.ManagementTypes;
import cl.smartware.apps.web.platform.service.exeption.FileEntityServiceException;
import cl.smartware.apps.web.platform.service.exeption.FoundRegisterFileEntityServiceException;

@Service
public class FileEntityServiceImpl implements FilerEntityService
{
	@Autowired
	private UserLoggedService userLoggedService;

	@Autowired
	private FileEntityCrudRepository fileEntityCrudRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileEntityServiceImpl.class);

	@Override
	public FileEntity save(FileEntity fileEntity)
	{
		return fileEntityCrudRepository.save(fileEntity);
	}

	@Override
	public FileEntity save(FileForm fileForm) throws FileEntityServiceException, FoundRegisterFileEntityServiceException
	{
		Optional<FileEntity> optionalFileEntity = fileEntityCrudRepository.findByName(fileForm.getName());

		if (optionalFileEntity.isPresent())
		{
			throw new FoundRegisterFileEntityServiceException(fileForm.getName());
		}

		return fileEntityCrudRepository.save(formToEntity(fileForm));
	}

	@Override
	public FileEntity formToEntity(FileForm fileForm) throws FileEntityServiceException
	{
		FileEntity fileEntity = new FileEntity();

		fileEntity.setAnio(fileForm.getAnio());
		fileEntity.setCreatedAt(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		fileEntity.setCreatedBy(userLoggedService.getUserEntity());
		fileEntity.setEnterprise(fileForm.getEnterprise());
		fileEntity.setManagement(fileForm.getManagement());
		fileEntity.setName(fileForm.getName());
		fileEntity.setType(fileForm.getType());

		MultipartFile file = fileForm.getFile();

		try
		{
			fileEntity.setFile(file.getBytes());
		}
		catch (IOException e)
		{
			String msg = MessageFormat.format("Error to get bytes from uploaded file {}", file.getOriginalFilename());
			LOGGER.error(msg, e);
			throw new FileEntityServiceException(msg, e);
		}

		fileEntity.setContentType(file.getContentType());
		fileEntity.setFileName(file.getOriginalFilename());
		fileEntity.setSize(file.getSize());

		return fileEntity;
	}

	@Override
	public List<FileEntity> findByNameOrAnioOrTypeOrManagementOrEnterprise(String name, Integer anio, FileTypes type,
			ManagementTypes management, String enterprise)
	{
		return fileEntityCrudRepository.findByNameOrAnioOrTypeOrManagementOrEnterprise(name, anio, type, management,
				enterprise);
	}

	@Override
	public Optional<FileEntity> findByName(String name)
	{
		return fileEntityCrudRepository.findByName(name);
	}

}
