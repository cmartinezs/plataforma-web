package cl.smartware.apps.web.platform.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DummyDatabaseConfig {

	@Bean
	@Qualifier("dummyDS")
	@ConfigurationProperties(prefix = "dummy.datasource")
	DataSource dummyDS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("dummyJdbcTemplate")
	JdbcTemplate dummyJdbcTemplate(@Qualifier("dummyDS") DataSource dummyDS) {
		return new JdbcTemplate(dummyDS);
	}

}
