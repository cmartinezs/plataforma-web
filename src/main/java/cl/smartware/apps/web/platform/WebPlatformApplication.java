package cl.smartware.apps.web.platform;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebPlatformApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(WebPlatformApplication.class, args);
	}

	@Bean
	public TomcatServletWebServerFactory containerFactory() 
	{
		return new TomcatServletWebServerFactory() {
			protected void customizeConnector(Connector connector) 
			{
				super.customizeConnector(connector);
				
				if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) 
				{

					((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
					logger.info("Set MaxSwallowSize " + -1);
				}
			}
		};

	}
}
