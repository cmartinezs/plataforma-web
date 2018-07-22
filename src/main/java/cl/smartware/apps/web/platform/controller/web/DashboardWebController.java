package cl.smartware.apps.web.platform.controller.web;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.smartware.apps.web.platform.service.UserLoggedService;
import cl.smartware.apps.web.platform.utils.ViewsComponentUtils;

@Controller
@RequestMapping("/dashboard")
public class DashboardWebController implements ErrorController
{
	@Autowired
	private ViewsComponentUtils viewsComponentUtils;
	
	@Autowired
	private UserLoggedService userLoggedService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardWebController.class);

	@GetMapping("")
	public String dashboard()
	{
		LOGGER.info(MessageFormat.format("Showing dashboard for the user {0}", userLoggedService.getUserEntity().getUsername()));
		return viewsComponentUtils.addThemeFolderToView("dashboard-index", userLoggedService.isAdmin());
	}
	
	@Secured({ "ROLE_USER" })
	@GetMapping("/{module}")
	public void module(@PathVariable("module") String module, HttpServletResponse response) throws IOException
	{
		LOGGER.info(MessageFormat.format("Redirecting to the module {0}", module));
		response.sendRedirect("/dashboard/modules/" + module);
	}
	
	@RequestMapping("/error")
	public String handleError()
	{
		return viewsComponentUtils.addThemeFolderToView("errors/error");
	}

	@Override
	public String getErrorPath()
	{
		return "/dashboard/error";
	}
}
