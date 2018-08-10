package cl.smartware.apps.web.platform.controller.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.smartware.apps.web.platform.controller.web.form.FileForm;
import cl.smartware.apps.web.platform.controller.web.form.SearchForm;
import cl.smartware.apps.web.platform.repository.jpa.entity.FileEntity;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.FileTypes;
import cl.smartware.apps.web.platform.repository.jpa.entity.enums.ManagementTypes;
import cl.smartware.apps.web.platform.service.FilerEntityService;
import cl.smartware.apps.web.platform.service.exception.FileEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.FoundRegisterFileEntityServiceException;
import cl.smartware.apps.web.platform.service.export.ExportFileService;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/dashboard/modules/library")
public class LibraryWebController
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;

	@Autowired
	private FilerEntityService filerEntityService;
	
	@Autowired
	private ExportFileService exportFileService;

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
		modelForUploadFile(model);
		return viewsComponentUtils.addThemeFolderToView("upload-file");
	}

	@Secured({ "ROLE_USER" })
	@PostMapping("/upload-file")
	public String uploadFile(ModelMap model
			, @Valid @ModelAttribute("fileForm")FileForm fileForm
			, BindingResult bindingResult)
	{
		if (!bindingResult.hasErrors())
		{
			try
			{
				filerEntityService.save(fileForm);
				model.addAttribute("uploadSucces", "Se ha guardado correctamente el archivo.");
				model.addAttribute("fileForm", new FileForm());
			}
			catch (FileEntityServiceException e)
			{
				LOGGER.error("Error to save uploaded file", e);

				model.addAttribute("errorUploadFile",
						"No se ha podido guardar el archivo, intente nuevamente. Si el problema persiste avise al proveedor del servicio.");
			}
			catch (FoundRegisterFileEntityServiceException e)
			{
				LOGGER.error("Error to save uploaded file", e);

				model.addAttribute("errorUploadFile", e.getMessage());
			}
		}

		modelForUploadFile(model);

		return viewsComponentUtils.addThemeFolderToView("upload-file");
	}

	@Secured({ "ROLE_USER" })
	@GetMapping("/search-file")
	public String searchFile(ModelMap model)
	{
		model.addAttribute("searchForm", new SearchForm());
		model.remove("finishedSearch");
		modelForUploadFile(model);
		return viewsComponentUtils.addThemeFolderToView("search-file");
	}

	@Secured({ "ROLE_USER" })
	@PostMapping("/search-file")
	public String searchFile(ModelMap model, @Valid
	@ModelAttribute("searchForm")
	SearchForm searchForm, BindingResult bindingResult)
	{
		if (!bindingResult.hasErrors())
		{
			List<FileEntity> files = filerEntityService.findByNameOrAnioOrTypeOrManagementOrEnterprise(
					searchForm.getName(), searchForm.getAnio(), searchForm.getType(), searchForm.getManagement(),
					searchForm.getEnterprise());

			model.addAttribute("tableTitle", "Resultados de la búsqueda");
			model.addAttribute("files", files);
			model.addAttribute("finishedSearch", "true");
		}

		modelForSearchFile(model);

		return viewsComponentUtils.addThemeFolderToView("search-file");
	}

	@Secured({ "ROLE_USER" })
	@GetMapping("/download-file")
	public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam("name") String name)
	{
		Optional<FileEntity> optionalFileEntity = filerEntityService.findByName(name);

		if (optionalFileEntity.isPresent())
		{
			return exportFileService.getDownloadResponse(optionalFileEntity.get());
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	private void modelForUploadFile(ModelMap model)
	{
		modelForFile(model, "Ingreso de documentos", "Formulario de ingreso de documentos");
	}

	private void modelForSearchFile(ModelMap model)
	{
		modelForFile(model, "Búsqueda de documentos", "Formulario de búsqueda de documentos");
	}

	private void modelForFile(ModelMap model, String moduleTitle, String formTitle)
	{
		model.addAttribute("module", "Biblioteca");
		model.addAttribute("moduleTitle", moduleTitle);
		model.addAttribute("formTitle", formTitle);
		model.addAttribute("fileTypeList", FileTypes.values());
		model.addAttribute("managementTypeList", ManagementTypes.values());
	}
}
