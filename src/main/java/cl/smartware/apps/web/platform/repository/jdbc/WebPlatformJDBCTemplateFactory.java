package cl.smartware.apps.web.platform.repository.jdbc;

import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import cl.smartware.apps.web.platform.configuration.databases.ContabillityDatabaseCfg;
import cl.smartware.apps.web.platform.configuration.databases.ManagementDatabaseCfg;
import cl.smartware.apps.web.platform.configuration.databases.RemunerationDatabaseCfg;
import cl.smartware.apps.web.platform.utils.builders.MapBuilder;

@Configuration
public class WebPlatformJDBCTemplateFactory 
{	
	@Bean
	public Map<String, JdbcTemplate> jdbcTemplatesMap()
	{
		Map<String, JdbcTemplate> map = MapBuilder.<String, JdbcTemplate>unordered()
				.put(getJdbcTemplateMap(ContabillityDatabaseCfg.class))
				.put(getJdbcTemplateMap(ManagementDatabaseCfg.class))
				.put(getJdbcTemplateMap(RemunerationDatabaseCfg.class))
				.build();
		return map;
	}
	
	@SuppressWarnings("resource")
	private Map<String, JdbcTemplate> getJdbcTemplateMap(Class<?> clazz)
	{
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(clazz);
		return ctx.getBeansOfType(JdbcTemplate.class);
	}
}
