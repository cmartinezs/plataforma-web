package cl.smartware.apps.web.platform.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.controller.web.form.SearchForm;
import cl.smartware.apps.web.platform.repository.jpa.crud.FileEntityCrudRepository;
import cl.smartware.apps.web.platform.repository.jpa.dao.FileEntityDAO;
import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;
import cl.smartware.apps.web.platform.service.exception.FileEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.FoundRegisterFileEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.InvalidTypeFileEntityServiceException;
import cl.smartware.apps.web.platform.utils.builders.MapBuilder;

@Service
public class FileEntityServiceImpl implements FilerEntityService
{
	@Autowired
	private UserLoggedService userLoggedService;

	@Autowired
	private FileEntityCrudRepository fileEntityCrudRepository;
	
	@Autowired
	private FileEntityDAO fileEntityDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileEntityServiceImpl.class);

	@Override
	public FileEntity save(FileEntity fileEntity)
	{
		return fileEntityCrudRepository.save(fileEntity);
	}

	@Override
	public FileEntity save(FileForm fileForm) throws FileEntityServiceException, FoundRegisterFileEntityServiceException, InvalidTypeFileEntityServiceException
	{
		Optional<FileEntity> optionalFileEntity = fileEntityCrudRepository.findByName(fileForm.getName());

		if (optionalFileEntity.isPresent())
		{
			throw new FoundRegisterFileEntityServiceException(fileForm.getName());
		}

		return fileEntityCrudRepository.save(formToEntity(fileForm));
	}

	@Override
	public FileEntity formToEntity(FileForm fileForm) throws FileEntityServiceException, InvalidTypeFileEntityServiceException
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
			String msg = MessageFormat.format("Error to get bytes from uploaded file {0}", file.getOriginalFilename());
			LOGGER.error(msg, e);
			throw new FileEntityServiceException(msg, e);
		}

		fileEntity.setContentType(file.getContentType());
		fileEntity.setFileName(file.getOriginalFilename());
		fileEntity.setSize(file.getSize());
		
		aditionalValidations(fileEntity);

		return fileEntity;
	}

	private void aditionalValidations(FileEntity fileEntity) throws InvalidTypeFileEntityServiceException 
	{
		if(!fileEntity.getContentType().equalsIgnoreCase(fileEntity.getType().getMediaType()))
		{
			throw new InvalidTypeFileEntityServiceException();
		}
		
		String filename = fileEntity.getFileName();
		
		if(filename.length() > 100) 
		{
			fileEntity.setFileName(filename.substring(0, 100));
		}
	}

	@Override
	public List<FileEntity> findBySearchForm(String name, Integer anio, FileTypes type, ManagementTypes management, String enterprise)
	{
		return fileEntityCrudRepository.findByNameOrAnioOrTypeOrManagementOrEnterprise(name, anio, type, management, enterprise);
	}
	
	@Override
	public List<FileEntity> findBySearchForm(SearchForm searchForm)
	{
		LOGGER.info("Searching file by form...");
		LOGGER.info("Validating fields...");
		
		MapBuilder<String, Object> findMap = MapBuilder.unordered();
		
		Field[] fields = searchForm.getClass().getDeclaredFields();
		
		for(Field field: fields)
		{
			Object fieldValue = null;
			field.setAccessible(true);
			
			try 
			{
				fieldValue = field.get(searchForm);
				
				if(fieldValue != null && isValidValue(field, fieldValue))
				{
					findMap.put(field.getName(), fieldValue);
				}
			} 
			catch (IllegalArgumentException | IllegalAccessException e) 
			{
				LOGGER.warn(MessageFormat.format("Inaccesible value for field {0}", field.getName()));
			}
		}
		
		return fileEntityDAO.find(findMap.build());
	}

	private boolean isValidValue(Field field, Object fieldValue) 
	{
		boolean isValid = true;

		if(fieldValue instanceof String)
		{
			isValid = !Strings.isEmpty(String.valueOf(fieldValue).trim());
		}
		
		LOGGER.info(MessageFormat.format("The value \"{0}\" for field {1} {2} valid", fieldValue, field.getName(), isValid ? "is" : "is not"));
		
		return isValid;
	}

	@Override
	public Optional<FileEntity> findByName(String name)
	{
		return fileEntityCrudRepository.findByName(name);
	}

	@Override
	public List<FileEntity> findByCreatedBy(UserEntity userEntity) 
	{
		return fileEntityCrudRepository.findByCreatedById(userEntity.getId());
	}

	@Override
	public void saveAll(List<FileEntity> files) 
	{
		fileEntityCrudRepository.saveAll(files);
	}

}
