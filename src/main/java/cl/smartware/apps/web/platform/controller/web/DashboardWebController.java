package cl.smartware.apps.web.platform.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.repository.entity.RoleEntity.ERole;
import cl.smartware.apps.web.platform.repository.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.entity.UserEntity;
import cl.smartware.apps.web.platform.service.MultiDatabaseService;
import cl.smartware.apps.web.platform.service.UserLoggedService;
import cl.smartware.apps.web.platform.service.WebPlatformModules;

@Controller
@RequestMapping("/dashboard")
public class DashboardWebController
{
	@Autowired
	private UserLoggedService userLoggedService;
	
	@Autowired
	private MultiDatabaseService multiDatabaseService;

	@GetMapping("")
	public String dashboard()
	{
		String template = null;

		UserEntity userEntity = userLoggedService.getUserEntity();

		if (userEntity != null)
		{
			for (RoleUserEntity roleuser : userEntity.getRoleUsers())
			{
				if (roleuser.getRole().getName().equalsIgnoreCase(ERole.ROLE_ADMIN.name()))
				{
					template = "admin/dashboard-index";
					break;
				}
				else if (roleuser.getRole().getName().equalsIgnoreCase(ERole.ROLE_USER.name()))
				{
					template = "dashboard-index";
				}
			}
		}

		return template;
	}

	@Secured({ "ROLE_USER" })
	@GetMapping("/{module}")
	public void module(@PathVariable("module") String module, HttpServletResponse response) throws IOException
	{
		response.sendRedirect("/dashboard/modules/" + module);
	}
	
	@Secured({ "ROLE_USER" })
	@GetMapping("/modules")
	public String modules() throws IOException
	{
		return "modules";
	}
	
	@Secured({ "ROLE_USER" })
	@GetMapping("/modules/{module}")
	public String modules(@PathVariable("module") String module, Model model)
	{
		WebPlatformModules urlModule = WebPlatformModules.valueOf(module.toUpperCase());
		String moduleName = urlModule.getModuleName();
		
		List<String> databases = multiDatabaseService.getDatabasesListFromModule(urlModule);
		
		model.addAttribute("databases", databases);
		model.addAttribute("tableTitle", "Bases de datos en módulo: " + moduleName);
		model.addAttribute("module", module);
		model.addAttribute("moduleTitle", moduleName);
		return "module";
	}
	
	@Secured({ "ROLE_USER" })
	@GetMapping("/modules/{module}/{database}")
	public String databases(@PathVariable("module") String module, @PathVariable("databaseName") String databaseName, Model model)
	{
		return "module-database";
	}
}
