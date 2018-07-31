package cl.smartware.apps.web.platform.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = { "cl.smartware.apps.web.platform.repository.jpa" })
public class WebPlatformDatabaseConfig {
	/*
	 * @Bean
	 * 
	 * @Qualifier("dummyDS")
	 * 
	 * @ConfigurationProperties(prefix = "dummy.datasource") DataSource dummyDS() {
	 * return DataSourceBuilder.create().build(); }
	 * 
	 * @Bean
	 * 
	 * @Qualifier("dummyJdbcTemplate") JdbcTemplate
	 * dummyJdbcTemplate(@Qualifier("dummyDS") DataSource dummyDS) { return new
	 * JdbcTemplate(dummyDS); }
	 */

	@Primary
	@Bean(name = "webPlataformDS")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("webPlataformDS") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("cl.smartware.apps.web.platform.repository.jpa.entity")
				.properties(hibernateProperties())
				.persistenceUnit("webPlataformPU")
				.build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	private Map<String, ?> hibernateProperties() {

		Resource resource = new ClassPathResource("hibernate.properties");
		
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			
			return properties.entrySet().stream()
											.collect(Collectors.toMap(
														e -> e.getKey().toString(),
														e -> e.getValue())
													);
		} catch (IOException e) {
			return new HashMap<>();
		}
	}
}
