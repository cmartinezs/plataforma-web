package cl.smartware.apps.web.platform.controller.web;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.smartware.apps.web.platform.controller.web.form.UserForm;
import cl.smartware.apps.web.platform.repository.jpa.entity.UserEntity;
import cl.smartware.apps.web.platform.service.RoleEntityService;
import cl.smartware.apps.web.platform.service.UserEntityService;
import cl.smartware.apps.web.platform.service.UserLoggedService;
import cl.smartware.apps.web.platform.service.exception.RoleNotFoundUserEntityServiceException;
import cl.smartware.apps.web.platform.service.exception.UserNotFoundUserEntityServiceException;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/dashboard/users")
public class UserWebController 
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;
	
	@Autowired
	private UserEntityService userEntityService;
	
	@Autowired
	private RoleEntityService roleEntityService;
	
	@Autowired
	private UserLoggedService userLoggedService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserWebController.class);
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("")
	public String index() 
	{
		return "redirect:/dashboard/users/list";
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/list")
	public String list(@ModelAttribute("advertice") String advertice, ModelMap model) 
	{
		if(!Objects.isNull(advertice)
				|| !advertice.isEmpty())
		{
			model.addAttribute("advertice", advertice);
		}
		else
		{
			model.remove("advertice");
		}
		
		List<UserEntity> list = userEntityService.findAll();
		model.addAttribute("userList", list);
		return viewsComponentUtils.addThemeFolderToView("users", true);
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/new")
	public String newUser(@ModelAttribute("advertice") String advertice, ModelMap model) 
	{
		if(!Objects.isNull(advertice)
				|| !advertice.isEmpty())
		{
			model.addAttribute("advertice", advertice);
		}
		else
		{
			model.remove("advertice");
		}
		
		model.addAttribute("userTitle", "nuevo");
		model.addAttribute("isNew", true);
		model.addAttribute("userForm", new UserForm());
		model.addAttribute("formTitle", "Registro de nuevo usuario");
		model.addAttribute("roleList", roleEntityService.findAll());
		model.addAttribute("isEdit", true);
		
		return viewsComponentUtils.addThemeFolderToView("user", true);
	}
	
	@Secured({ "ROLE_ADMIN" })
	@PostMapping("/save")
	public String save(ModelMap model
			, @Valid @ModelAttribute("userForm") UserForm userForm
			, BindingResult bindingResult
			, RedirectAttributes ra) 
	{
		if (!bindingResult.hasErrors())
		{
			UserEntity userEntity = null;
			
			try 
			{
				userEntity = userEntityService.save(userForm, userLoggedService.getUserEntity());
				ra.addFlashAttribute("editSuccess", "Cambios registrados con éxtio");
				return "redirect:/dashboard/users/view/" + userEntity.getId();
			} 
			catch (RoleNotFoundUserEntityServiceException e) 
			{
				model.addAttribute("editError", "El rol seleccionado no es válido");
			} 
			catch (UserNotFoundUserEntityServiceException e) 
			{
				model.addAttribute("editError", "El usuario seleccionado no es válido");
			}
		}
		
		if(Objects.isNull(userForm.getId()))
		{
			model.addAttribute("userTitle", "nuevo");
			model.addAttribute("formTitle", "Registro de nuevo usuario");
			model.addAttribute("isNew", false);
		}
		else
		{
			model.addAttribute("userTitle", userForm.getUsername());
			model.addAttribute("formTitle", "Modificación de usuario");
			model.addAttribute("isNew", true);
		}

		model.addAttribute("isEdit", true);
		model.addAttribute("roleList", roleEntityService.findAll());

		return viewsComponentUtils.addThemeFolderToView("user", true);
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Integer id, ModelMap model,  RedirectAttributes ra) 
	{		
		Optional<UserEntity> optionaUserEntity = userEntityService.findById(id);
		
		if(optionaUserEntity.isPresent())
		{
			UserEntity userEntity = optionaUserEntity.get();
			UserForm userForm = userEntityService.entityToForm(userEntity);
			
			model.addAttribute("userForm", userForm);
			model.addAttribute("userTitle", userEntity.getUsername());
			model.addAttribute("formTitle", "Visualización de usuario");
			model.addAttribute("roleList", roleEntityService.findAll());
			model.addAttribute("isEdit", false);
			model.addAttribute("isNew", false);
			
			String flashEditSuccess = (String) ra.getFlashAttributes().get("editSuccess");
			
			if(!Objects.isNull(flashEditSuccess))
			{
				model.addAttribute("editSuccess", flashEditSuccess);
			}
			
			return viewsComponentUtils.addThemeFolderToView("user", true);
		}
		
		ra.addFlashAttribute("advertice", MessageFormat.format("No existe el usuario solicitado con id. {0}", id));
		
		return "redirect:/dashboard/users/list";
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/edit/{id}")
	public String update(@PathVariable("id") Integer id, ModelMap model,  RedirectAttributes ra) 
	{
		model.remove("editSuccess");
		model.remove("editError");
		model.remove("advertice");
		
		Optional<UserEntity> optionaUserEntity = userEntityService.findById(id);
		
		if(optionaUserEntity.isPresent())
		{
			UserEntity userEntity = optionaUserEntity.get();
			UserForm userForm = userEntityService.entityToForm(userEntity);
			model.addAttribute("userForm", userForm);
			model.addAttribute("userTitle", userEntity.getUsername());
			model.addAttribute("formTitle", "Modificación de usuario");
			model.addAttribute("roleList", roleEntityService.findAll());
			model.addAttribute("isEdit", true);
			model.addAttribute("isNew", false);
			
			return viewsComponentUtils.addThemeFolderToView("user", true);
		}
		
		ra.addFlashAttribute("advertice", MessageFormat.format("No existe el usuario solicitado con id. {0}, pero puede ingresar uno nuevo", id));
		
		return "redirect:/dashboard/users/new";
	}
	
	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/delete/{id}")
	public String delete() 
	{
		return "";
	}
}
