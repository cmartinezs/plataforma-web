package cl.smartware.apps.web.platform.controller.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.repository.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.entity.enums.ManagementTypes;
import cl.smartware.apps.web.platform.service.FilerEntityService;
import cl.smartware.apps.web.platform.service.exeption.FileEntityServiceException;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/dashboard/modules/library")
public class LibraryWebController
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;
		
	@Autowired
	private FilerEntityService filerEntityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryWebController.class);
	
	@Secured({ "ROLE_USER" })
	@GetMapping("")
	public String library(Model model)
	{
		return viewsComponentUtils.addThemeFolderToView("library");
	}
	
	@Secured({ "ROLE_USER" })
	@GetMapping("/upload-file")
	public String uploadFile(ModelMap model)
	{
		model.addAttribute("fileForm", new FileForm());
		chargeUploadFileModel(model);
		return viewsComponentUtils.addThemeFolderToView("upload-file");
	}

	private void chargeUploadFileModel(ModelMap model)
	{
		model.addAttribute("module", "Biblioteca");
		model.addAttribute("moduleTitle", "Ingreso de documentos");
		model.addAttribute("formTitle", "Formulario de ingreso de documentos");
		model.addAttribute("fileTypeList", FileTypes.values());
		model.addAttribute("managementTypeList", ManagementTypes.values());
	}
	
	@Secured({ "ROLE_USER" })
	@PostMapping("/upload-file")
	public String uploadFile(ModelMap model, @Valid @ModelAttribute("fileForm") FileForm fileForm, BindingResult bindingResult) 
	{
		if(!bindingResult.hasErrors()) 
		{
			try
			{
				filerEntityService.save(fileForm);
				model.addAttribute("uploadSucces", "Se ha guardado correctamente el archivo.");
				model.addAttribute("fileForm", new FileForm());
			}
			catch (FileEntityServiceException e)
			{
				LOGGER.error("Error to save uploaded file");
				
				model.addAttribute("errorUploadFile", "No se ha podido guardar el archivo, intente nuevamente. Si el problema persiste avise al proveedor del servicio.");
			}
		}
		
		chargeUploadFileModel(model);
		
	    return viewsComponentUtils.addThemeFolderToView("upload-file");
	}
}
