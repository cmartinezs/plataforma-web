package cl.smartware.apps.web.platform.configuration.databases;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ManagementDatabaseCfg {

	@Bean
	@Qualifier("management01DS")
	@ConfigurationProperties(prefix = "management.management-01.datasource")
	DataSource management01DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management01JDCBTemplate")
	JdbcTemplate management01JDCBTemplate(@Qualifier("management01DS") DataSource contabilidad01DS) {
		return new JdbcTemplate(contabilidad01DS);
	}
	
	@Bean
	@Qualifier("management02DS")
	@ConfigurationProperties(prefix = "management.management-02.datasource")
	DataSource management02DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management02JDCBTemplate")
	JdbcTemplate management02JDCBTemplate(@Qualifier("management02DS") DataSource contabilidad02DS) {
		return new JdbcTemplate(contabilidad02DS);
	}

	@Bean
	@Qualifier("management03DS")
	@ConfigurationProperties(prefix = "management.management-03.datasource")
	DataSource management03DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management03JDCBTemplate")
	JdbcTemplate management03JDCBTemplate(@Qualifier("management03DS") DataSource contabilidad03DS) {
		return new JdbcTemplate(contabilidad03DS);
	}
	
	@Bean
	@Qualifier("management04DS")
	@ConfigurationProperties(prefix = "management.management-04.datasource")
	DataSource management04DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management04JDCBTemplate")
	JdbcTemplate management04JDCBTemplate(@Qualifier("management04DS") DataSource contabilidad04DS) {
		return new JdbcTemplate(contabilidad04DS);
	}
	
	@Bean
	@Qualifier("management05DS")
	@ConfigurationProperties(prefix = "management.management-05.datasource")
	DataSource management05DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management05JDCBTemplate")
	JdbcTemplate management05JDCBTemplate(@Qualifier("management05DS") DataSource contabilidad05DS) {
		return new JdbcTemplate(contabilidad05DS);
	}
	
	@Bean
	@Qualifier("management06DS")
	@ConfigurationProperties(prefix = "management.management-06.datasource")
	DataSource management06DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management06JDCBTemplate")
	JdbcTemplate management06JDCBTemplate(@Qualifier("management06DS") DataSource contabilidad06DS) {
		return new JdbcTemplate(contabilidad06DS);
	}
	
	@Bean
	@Qualifier("management07DS")
	@ConfigurationProperties(prefix = "management.management-07.datasource")
	DataSource management07DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management07JDCBTemplate")
	JdbcTemplate management07JDCBTemplate(@Qualifier("management07DS") DataSource contabilidad07DS) {
		return new JdbcTemplate(contabilidad07DS);
	}
	
	@Bean
	@Qualifier("management08DS")
	@ConfigurationProperties(prefix = "management.management-08.datasource")
	DataSource management08DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management08JDCBTemplate")
	JdbcTemplate management08JDCBTemplate(@Qualifier("management08DS") DataSource contabilidad08DS) {
		return new JdbcTemplate(contabilidad08DS);
	}
	
	@Bean
	@Qualifier("management09DS")
	@ConfigurationProperties(prefix = "management.management-09.datasource")
	DataSource management09DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management09JDCBTemplate")
	JdbcTemplate management09JDCBTemplate(@Qualifier("management09DS") DataSource contabilidad09DS) {
		return new JdbcTemplate(contabilidad09DS);
	}
	
	@Bean
	@Qualifier("management10DS")
	@ConfigurationProperties(prefix = "management.management-10.datasource")
	DataSource management10DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("management10JDCBTemplate")
	JdbcTemplate management10JDCBTemplate(@Qualifier("management10DS") DataSource contabilidad10DS) {
		return new JdbcTemplate(contabilidad10DS);
	}
}
