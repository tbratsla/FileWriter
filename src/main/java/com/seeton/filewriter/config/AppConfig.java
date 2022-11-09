package com.seeton.filewriter.config;

import com.querydsl.sql.OracleTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.function.Supplier;

@Configuration
public class AppConfig {

    @Bean
    com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = OracleTemplates.builder()
                .printSchema()
                .build();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setUseLiterals(true);
        return configuration;
    }


    @Bean
    @Inject
    public SQLQueryFactory getSqlQueryFactory(DataSource dataSource) {
        Supplier<Connection> provider = new SpringConnectionProvider(dataSource);
        return new SQLQueryFactory(querydslConfiguration(), provider);
    }

}
