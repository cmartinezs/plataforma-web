package cl.smartware.apps.web.platform.configuration.databases;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ContabillityDatabaseCfg 
{
	@Bean
	@Qualifier("contability01DS")
	@ConfigurationProperties(prefix = "contability.contability01.datasource")
	DataSource contability01DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability01JDCBTemplate")
	JdbcTemplate contability01JDCBTemplate(@Qualifier("contability01DS") DataSource contabilidad01DS) {
		return new JdbcTemplate(contabilidad01DS);
	}
	
	@Bean
	@Qualifier("contability02DS")
	@ConfigurationProperties(prefix = "contability.contability02.datasource")
	DataSource contability02DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability02JDCBTemplate")
	JdbcTemplate contability02JDCBTemplate(@Qualifier("contability02DS") DataSource contabilidad02DS) {
		return new JdbcTemplate(contabilidad02DS);
	}

	@Bean
	@Qualifier("contability03DS")
	@ConfigurationProperties(prefix = "contability.contability03.datasource")
	DataSource contability03DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability03JDCBTemplate")
	JdbcTemplate contability03JDCBTemplate(@Qualifier("contability03DS") DataSource contabilidad03DS) {
		return new JdbcTemplate(contabilidad03DS);
	}
	
	@Bean
	@Qualifier("contability04DS")
	@ConfigurationProperties(prefix = "contability.contability04.datasource")
	DataSource contability04DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability04JDCBTemplate")
	JdbcTemplate contability04JDCBTemplate(@Qualifier("contability04DS") DataSource contabilidad04DS) {
		return new JdbcTemplate(contabilidad04DS);
	}
	
	@Bean
	@Qualifier("contability05DS")
	@ConfigurationProperties(prefix = "contability.contability05.datasource")
	DataSource contability05DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability05JDCBTemplate")
	JdbcTemplate contability05JDCBTemplate(@Qualifier("contability05DS") DataSource contabilidad05DS) {
		return new JdbcTemplate(contabilidad05DS);
	}
	
	@Bean
	@Qualifier("contability06DS")
	@ConfigurationProperties(prefix = "contability.contability06.datasource")
	DataSource contability06DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability06JDCBTemplate")
	JdbcTemplate contability06JDCBTemplate(@Qualifier("contability06DS") DataSource contabilidad06DS) {
		return new JdbcTemplate(contabilidad06DS);
	}
	
	@Bean
	@Qualifier("contability07DS")
	@ConfigurationProperties(prefix = "contability.contability07.datasource")
	DataSource contability07DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability07JDCBTemplate")
	JdbcTemplate contability07JDCBTemplate(@Qualifier("contability07DS") DataSource contabilidad07DS) {
		return new JdbcTemplate(contabilidad07DS);
	}
	
	@Bean
	@Qualifier("contability08DS")
	@ConfigurationProperties(prefix = "contability.contability08.datasource")
	DataSource contability08DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability08JDCBTemplate")
	JdbcTemplate contability08JDCBTemplate(@Qualifier("contability08DS") DataSource contabilidad08DS) {
		return new JdbcTemplate(contabilidad08DS);
	}
	
	@Bean
	@Qualifier("contability09DS")
	@ConfigurationProperties(prefix = "contability.contability09.datasource")
	DataSource contability09DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability09JDCBTemplate")
	JdbcTemplate contability09JDCBTemplate(@Qualifier("contability09DS") DataSource contabilidad09DS) {
		return new JdbcTemplate(contabilidad09DS);
	}
	
	@Bean
	@Qualifier("contability10DS")
	@ConfigurationProperties(prefix = "contability.contability10.datasource")
	DataSource contability10DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("contability10JDCBTemplate")
	JdbcTemplate contability10JDCBTemplate(@Qualifier("contability10DS") DataSource contabilidad10DS) {
		return new JdbcTemplate(contabilidad10DS);
	}
}
