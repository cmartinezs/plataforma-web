package cl.smartware.apps.web.platform.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class LibraryWebControllerExceptionHandler 
{
	@Value("${spring.servlet.multipart.max-file-size:5MB}")
	String maxFileSize;
	
	//StandardServletMultipartResolver
    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) 
    {
    	redirectAttributes.addFlashAttribute("errorUploadFile", e.getLocalizedMessage());
        return "redirect:/dashboard/modules/library/upload-file";
    }

    //CommonsMultipartResolver
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError2(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) 
    {
    	redirectAttributes.addFlashAttribute("errorUploadFile", "Se ha exedido el tamaño máximo del archivo (" + maxFileSize  + ")");
        return "redirect:/dashboard/modules/library/upload-file";
    }
}
