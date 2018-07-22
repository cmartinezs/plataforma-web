package cl.smartware.apps.web.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("cl.smartware.apps.web.platform.repository.crud")
public class WebPlatformApplication extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(WebPlatformApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(WebPlatformApplication.class, args);
	}
}
