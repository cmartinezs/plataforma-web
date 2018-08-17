package cl.smartware.apps.web.platform.controller.web.form.validator.file;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile>
{
	private int maxSize;
	private String[] contentType;
	
	@Override
	public void initialize(ValidFile validFile)
	{
		this.maxSize = validFile.maxSize();
		this.contentType = validFile.contentType();
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context)
	{
		if(file == null || file.getSize() <= 0)
		{
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("Debe seleccionar un archivo").addConstraintViolation();
	        return false;
		}
		else if(file.getSize() > maxSize)
		{
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("No debe exeder el tamaño máximo ("+maxSize/1024/1024+"Mb)").addConstraintViolation();
	        return false;
		}
		else if(!isValidContentType(file))
		{
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("Solo debe ingresar archivos PDF [*.pdf] o Excel [*.xls, *.xlsx]").addConstraintViolation();
	        return false;
		}
		
		return true;
	}

	private boolean isValidContentType(MultipartFile file) 
	{
		boolean isValid = false;
		
		String fileContentType = file.getContentType();
		
		for(String mediaType: contentType)
		{
			if(fileContentType.equalsIgnoreCase(mediaType))
			{
				isValid = true;
				break;
			}
		}
		
		return isValid;
	}
}
