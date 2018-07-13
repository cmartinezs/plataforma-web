package cl.smartware.apps.web.platform.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.repository.entity.RoleEntity.ERole;
import cl.smartware.apps.web.platform.repository.entity.RoleUserEntity;
import cl.smartware.apps.web.platform.repository.entity.UserEntity;
import cl.smartware.apps.web.platform.service.UserLoggedService;

@Controller
@RequestMapping("/dashboard")
public class DashboardWebController
{
	@Autowired
	private UserLoggedService userLoggedService;

	@GetMapping("")
	public String dashboard()
	{
		String template = null;

		UserEntity userEntity = userLoggedService.getUserEntity();

		if (userEntity != null)
		{
			for (RoleUserEntity roleuser : userEntity.getRoleUsers())
			{
				if(roleuser.getRole().getName().equalsIgnoreCase(ERole.ROLE_ADMIN.name()))
				{
					template = "admin/dashboard";
					break;
				}
				else if(roleuser.getRole().getName().equalsIgnoreCase(ERole.ROLE_USER.name()))
				{
					template = "dashboard-index";
				}
			}
		}

		return template;
	}
}
