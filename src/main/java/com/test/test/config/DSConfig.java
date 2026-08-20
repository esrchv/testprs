package com.test.test.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * DataSource configuration backed by application server managed JNDI resources.
 */
@Configuration
@Profile("!test")
public class DSConfig {

    @Primary
    @Lazy
    @Bean(name = "testoneDataSource")
    public DataSource testoneDataSource(@Value("${testone.datasource.jndi-name}") String jndiName) {
        return lookupJndiDataSource(jndiName);
    }

    @Lazy
    @Bean(name = "testtwoDataSource")
    public DataSource testtwoDataSource(@Value("${testtwo.datasource.jndi-name}") String jndiName) {
        return lookupJndiDataSource(jndiName);
    }

    @Primary
    @Lazy
    @Bean(name = "testoneTransactionManager")
    public PlatformTransactionManager testoneTransactionManager(
            @Qualifier("testoneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Lazy
    @Bean(name = "testtwoTransactionManager")
    public PlatformTransactionManager testtwoTransactionManager(
            @Qualifier("testtwoDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private DataSource lookupJndiDataSource(String jndiName) {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        lookup.setResourceRef(true);
        return lookup.getDataSource(jndiName);
    }
}
