package cl.smartware.apps.web.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("cl.smartware.apps.web.platform.repository.crud")
public class WebPlatformApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(WebPlatformApplication.class, args);
	}
}
