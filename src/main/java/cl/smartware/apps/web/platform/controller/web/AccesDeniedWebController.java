package cl.smartware.apps.web.platform.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.service.UserLoggedService;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/403")
public class AccesDeniedWebController
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;
	
	@Autowired
	private UserLoggedService userLoggedService;
	
	@GetMapping("")
	public String accessDenied(Model model)
	{;
		return viewsComponentUtils.addAdminFolderToView("errors/403", userLoggedService.isAdmin());
	}
}
