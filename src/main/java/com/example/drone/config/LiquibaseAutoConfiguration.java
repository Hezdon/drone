package com.example.drone.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Liquibase.
 *
 * @author chidalu egeonu
 */
@Configuration
@ConditionalOnClass(SpringLiquibase.class)
@ConditionalOnBean(DataSource.class)
@ConditionalOnExpression("${liquibase.enabled:true}")
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class LiquibaseAutoConfiguration {

    @Configuration
    @ConditionalOnMissingBean(SpringLiquibase.class)
    @EnableConfigurationProperties(LiquibaseProperties.class)
    public static class LiquibaseConfiguration {

        @Autowired
        private LiquibaseProperties properties = new LiquibaseProperties();

        @Autowired
        private ResourceLoader resourceLoader = new DefaultResourceLoader();

        @Value("${spring.datasource.password}")
        private String liquibasePassword;

        @Value("${spring.datasource.username}")
        private String liquibaseUser;

        @Value("${spring.datasource.url}")
        private String liquibaseUrl;

        @Autowired
        private DataSource dataSource;

        @Autowired
        Environment environment;


        public void unlockChangeLog(){
            JdbcTemplate jdbcTemplateObject;
            jdbcTemplateObject = new JdbcTemplate(liquibaseDataSource());
            try {
                int chk = jdbcTemplateObject.update("UPDATE dbo.DATABASECHANGELOGLOCK SET LOCKED=0 WHERE ID=1");
                System.out.println(chk);
            }
            catch(Exception ex){ }
        }

        @PostConstruct
        public void checkChangelogExists() {
            if (this.properties.isEnabled()) {
                Resource resource = this.resourceLoader.getResource(this.properties
                        .getChangeLog());
                Assert.state(resource.exists(), "Cannot find changelog location: "
                        + resource + " (please add changelog or check your Liquibase "
                        + "configuration)");
            }
            unlockChangeLog();
        }


        @Bean
        public SpringLiquibase liquibase() {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setChangeLog(this.properties.getChangeLog());

            liquibase.setContexts(this.properties.getContexts());
            liquibase.setDataSource(liquibaseDataSource());
            liquibase.setDefaultSchema(this.properties.getDefaultSchema());
            liquibase.setDropFirst(this.properties.isDropFirst());
            liquibase.setShouldRun(this.properties.isEnabled());

            return liquibase;
        }

        @Bean
        public DataSource liquibaseDataSource() {

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(liquibaseUrl);
            dataSource.setUsername(liquibaseUser);
            dataSource.setPassword(liquibasePassword);
            return dataSource;
        }

    }

}
