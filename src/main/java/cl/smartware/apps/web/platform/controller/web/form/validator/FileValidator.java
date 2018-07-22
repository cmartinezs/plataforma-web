package cl.smartware.apps.web.platform.controller.web.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile>
{
	private int maxSize;
	
	@Override
	public void initialize(ValidFile validFile)
	{
		this.maxSize = validFile.maxSize();
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
		
		return true;
	}
}
