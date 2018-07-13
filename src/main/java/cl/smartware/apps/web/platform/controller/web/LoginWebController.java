package cl.smartware.apps.web.platform.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.service.UserLoggedService;

@Controller
@RequestMapping("/")
public class LoginWebController
{
	@Autowired
	private UserLoggedService userLoggedService;
	
	@GetMapping("")
	public void index(HttpServletResponse response) throws IOException
	{
		response.sendRedirect("/login");
	}

	@GetMapping("/login")
	public String login(Model model)
	{
		return userLoggedService.getUserEntity() != null ? "redirect:/dashboard" : "login" ;
	}
	
	@GetMapping("/logout-success")
	public void logoutSuccess(HttpServletResponse response) throws IOException
	{
		userLoggedService.setUserEntity(null);
		response.sendRedirect("/login");
	}
}
