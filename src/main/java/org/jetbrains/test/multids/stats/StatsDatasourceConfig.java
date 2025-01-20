package org.jetbrains.test.multids.stats;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJdbcRepositories(basePackages = "org.jetbrains.test.multids.stats", transactionManagerRef = "ds3TransactionManager")
class StatsDatasourceConfig {

    @Bean
    public DataSourceTransactionManager ds3TransactionManager(@Qualifier("ds3DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceInitializer ds3DataSourceInitializer(@Qualifier("ds3DataSource") DataSource dataSource, ResourceLoader resourceLoader) {
        Resource schema = resourceLoader.getResource("classpath:db/derby/schema.sql");
        Resource data = resourceLoader.getResource("classpath:db/derby/data.sql");
        Resource cleanup = resourceLoader.getResource("classpath:db/derby/drop.sql");


        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        initializer.setDatabasePopulator(new ResourceDatabasePopulator(schema, data));
        initializer.setDatabaseCleaner(new ResourceDatabasePopulator(cleanup));

        return initializer;
    }


}
