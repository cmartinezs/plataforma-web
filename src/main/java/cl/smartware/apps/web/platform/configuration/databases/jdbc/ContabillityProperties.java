package cl.smartware.apps.web.platform.configuration.databases.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("file:./config/contability.properties")
public class ContabillityProperties 
{
	@Autowired
    private Environment env;

    public String getProperty(String property) 
    {
        return env.getProperty(property);
    }
}
