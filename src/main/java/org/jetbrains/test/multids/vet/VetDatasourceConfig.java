package org.jetbrains.test.multids.vet;

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
@EnableJdbcRepositories(basePackages = "org.jetbrains.test.multids.vet", transactionManagerRef = "ds2TransactionManager")
public class VetDatasourceConfig {

    @Bean
    public DataSourceTransactionManager ds2TransactionManager(@Qualifier("ds2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean
    public DataSourceInitializer ds2DataSourceInitializer(@Qualifier("ds2DataSource") DataSource dataSource, ResourceLoader resourceLoader) {
        Resource schema = resourceLoader.getResource("classpath:db/mysql/schema.sql");
        Resource data = resourceLoader.getResource("classpath:db/mysql/data.sql");

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        DatabasePopulator populator = new ResourceDatabasePopulator(schema, data);
        initializer.setDatabasePopulator(populator);

        return initializer;
    }


}
