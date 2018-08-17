package cl.smartware.apps.web.platform.configuration.databases.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RemunerationDatabaseCfg {

	@Bean
	@Qualifier("remuneration01DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-01.datasource")
	DataSource remuneration01DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration01JDCBTemplate")
	JdbcTemplate remuneration01JDCBTemplate(@Qualifier("remuneration01DS") DataSource contabilidad01DS) {
		return new JdbcTemplate(contabilidad01DS);
	}
	
	@Bean
	@Qualifier("remuneration02DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-02.datasource")
	DataSource remuneration02DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration02JDCBTemplate")
	JdbcTemplate remuneration02JDCBTemplate(@Qualifier("remuneration02DS") DataSource contabilidad02DS) {
		return new JdbcTemplate(contabilidad02DS);
	}

	@Bean
	@Qualifier("remuneration03DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-03.datasource")
	DataSource remuneration03DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration03JDCBTemplate")
	JdbcTemplate remuneration03JDCBTemplate(@Qualifier("remuneration03DS") DataSource contabilidad03DS) {
		return new JdbcTemplate(contabilidad03DS);
	}
	
	@Bean
	@Qualifier("remuneration04DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-04.datasource")
	DataSource remuneration04DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration04JDCBTemplate")
	JdbcTemplate remuneration04JDCBTemplate(@Qualifier("remuneration04DS") DataSource contabilidad04DS) {
		return new JdbcTemplate(contabilidad04DS);
	}
	
	@Bean
	@Qualifier("remuneration05DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-05.datasource")
	DataSource remuneration05DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration05JDCBTemplate")
	JdbcTemplate remuneration05JDCBTemplate(@Qualifier("remuneration05DS") DataSource contabilidad05DS) {
		return new JdbcTemplate(contabilidad05DS);
	}
	
	@Bean
	@Qualifier("remuneration06DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-06.datasource")
	DataSource remuneration06DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration06JDCBTemplate")
	JdbcTemplate remuneration06JDCBTemplate(@Qualifier("remuneration06DS") DataSource contabilidad06DS) {
		return new JdbcTemplate(contabilidad06DS);
	}
	
	@Bean
	@Qualifier("remuneration07DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-07.datasource")
	DataSource remuneration07DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration07JDCBTemplate")
	JdbcTemplate remuneration07JDCBTemplate(@Qualifier("remuneration07DS") DataSource contabilidad07DS) {
		return new JdbcTemplate(contabilidad07DS);
	}
	
	@Bean
	@Qualifier("remuneration08DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-08.datasource")
	DataSource remuneration08DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration08JDCBTemplate")
	JdbcTemplate remuneration08JDCBTemplate(@Qualifier("remuneration08DS") DataSource contabilidad08DS) {
		return new JdbcTemplate(contabilidad08DS);
	}
	
	@Bean
	@Qualifier("remuneration09DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-09.datasource")
	DataSource remuneration09DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration09JDCBTemplate")
	JdbcTemplate remuneration09JDCBTemplate(@Qualifier("remuneration09DS") DataSource contabilidad09DS) {
		return new JdbcTemplate(contabilidad09DS);
	}
	
	@Bean
	@Qualifier("remuneration10DS")
	@ConfigurationProperties(prefix = "remuneration.remuneration-10.datasource")
	DataSource remuneration10DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Qualifier("remuneration10JDCBTemplate")
	JdbcTemplate remuneration10JDCBTemplate(@Qualifier("remuneration10DS") DataSource contabilidad10DS) {
		return new JdbcTemplate(contabilidad10DS);
	}
}
