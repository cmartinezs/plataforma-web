package cl.smartware.apps.web.platform.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusRestController
{
	@GetMapping("")
	public String main() 
	{
		return "ok";
	}
	
	@GetMapping("/server")
	public String server() 
	{
		return "The config of server";
	}
}