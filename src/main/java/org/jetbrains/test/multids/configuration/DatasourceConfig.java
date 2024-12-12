package org.jetbrains.test.multids.configuration;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig implements BeanClassLoaderAware {

    private ClassLoader classloader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classloader = classLoader;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds1")
    public DataSourceProperties ds1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource ds1DataSource() {
        return ds1DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.ds2")
    public DataSourceProperties ds2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource ds2DataSource() {
        return ds2DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public DataSource ds3DataSource() {
        return DataSourceBuilder
                .create(classloader)
                .driverClassName("org.apache.derby.iapi.jdbc.AutoloadedDriver")
                .url("jdbc:derby:derbyDB;create=true")
                .username("")
                .password("")
                .build();
    }
}
