package cl.smartware.apps.web.platform.controller.web;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.service.UserLoggedService;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/")
public class LoginWebController
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;
	
	@Autowired
	private UserLoggedService userLoggedService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginWebController.class);
	
	@GetMapping("")
	public void index(HttpServletResponse response) throws IOException
	{
		LOGGER.info("Redirecting to login");
		response.sendRedirect("/login");
	}

	@GetMapping("/login")
	public String login(Model model)
	{
		if(userLoggedService.getUserEntity() != null)
		{
			LOGGER.info("Redirecting to dashboard");
		}
		else
		{
			LOGGER.info("Showing login");
		}
		
		return userLoggedService.getUserEntity() != null ?  "redirect:/dashboard" : viewsComponentUtils.addThemeFolderToView("login");
	}
	
	@GetMapping("/logout-success")
	public void logoutSuccess(HttpServletResponse response) throws IOException
	{
		LOGGER.info(MessageFormat.format("Logout the user {0}", userLoggedService.getUserEntity().getUsername()));
		userLoggedService.setUserEntity(null);
		response.sendRedirect("/login");
	}
}
