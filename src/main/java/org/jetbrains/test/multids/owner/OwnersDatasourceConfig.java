package org.jetbrains.test.multids.owner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.jetbrains.test.multids.owner", entityManagerFactoryRef = "ds1EntityManagerFactory", transactionManagerRef = "ds1TransactionManager")
public class OwnersDatasourceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean ds1EntityManagerFactory(@Qualifier("ds1DataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource).packages("org.jetbrains.test.multids.owner").build();
    }

    @Bean
    public PlatformTransactionManager ds1TransactionManager(@Qualifier("ds1EntityManagerFactory") LocalContainerEntityManagerFactoryBean ds1EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(ds1EntityManagerFactory.getObject()));
    }

    @Bean
    public DataSourceInitializer ds1DataSourceInitializer(@Qualifier("ds1DataSource") DataSource dataSource, ResourceLoader resourceLoader) {
        Resource schema = resourceLoader.getResource("classpath:db/postgres/schema.sql");
        Resource data = resourceLoader.getResource("classpath:db/postgres/data.sql");

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        DatabasePopulator populator = new ResourceDatabasePopulator(schema, data);
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}
