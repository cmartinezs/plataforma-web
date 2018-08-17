package cl.smartware.apps.web.platform.controller.web.form.validator.password;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.thymeleaf.util.StringUtils;

import cl.smartware.apps.web.platform.controller.web.form.UserForm;

public class ConfirmPasswordValidator implements ConstraintValidator<ValidConfirmPassword, UserForm>
{
	@Override
	public void initialize(ValidConfirmPassword validFile) { }

	@Override
	public boolean isValid(UserForm userForm, ConstraintValidatorContext context)
	{
		Integer id = userForm.getId();
		String password = userForm.getPassword();
		String confirmPassword = userForm.getConfirmPassword();
		
		if(Objects.isNull(id))
		{
			if(Objects.isNull(password) || Objects.isNull(confirmPassword)
					|| StringUtils.trim(password).isEmpty() || StringUtils.trim(confirmPassword).isEmpty())
			{
				context.disableDefaultConstraintViolation();
		        context.buildConstraintViolationWithTemplate("Debe ingresar ambas contraseñas").addConstraintViolation();
		        return false;
			}
		}

		if(!Objects.isNull(password)
				&& !Objects.isNull(confirmPassword)
				&& !password.equals(confirmPassword))
		{
			context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate("Las contraseñas deben coincidir").addConstraintViolation();
	        return false;
		}
		
		return true;
	}
}
