package cl.smartware.apps.web.platform.controller.web.form.validator.file;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FileValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFile
{
	String message() default "Invalid file";
	int maxSize();
	String[] contentType();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
