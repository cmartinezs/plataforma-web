package cl.smartware.apps.web.platform.configuration.databases.jpa;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import cl.smartware.apps.web.platform.utils.builders.MapBuilder;

@Configuration
@PropertySource("file:./config/application.properties")
@ConfigurationProperties(prefix = "spring.jpa.properties")
public class WebPlatformDatabaseProperties 
{
	@Autowired
    private Environment env;

    public Object getProperty(String property) 
    {
        return env.getProperty(property, Object.class);
    }
    
    public Map<String, Object> map()
    {
    	return MapBuilder.<String, Object>unordered()
    			.put("hibernate.ddl-auto", env.getProperty("hibernate.ddl-auto"))
    			.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"))
    			.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"))
    			.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"))
    			.put("hibernate.dialect", env.getProperty("hibernate.dialect"))
    			.build();
    }
}
