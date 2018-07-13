package cl.smartware.apps.web.platform.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/403")
public class AccesDeniedWebController
{
	@GetMapping("")
	public String accessDenied()
	{
		return "errors/403";
	}
}
