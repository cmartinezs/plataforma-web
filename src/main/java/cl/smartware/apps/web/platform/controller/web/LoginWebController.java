package cl.smartware.apps.web.platform.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginWebController
{
	@GetMapping("")
	public void index(HttpServletResponse response) throws IOException
	{
		response.sendRedirect("/login");
	}

	@GetMapping("/login")
	public String loginPage(Model model)
	{
		return "login";
	}
}
