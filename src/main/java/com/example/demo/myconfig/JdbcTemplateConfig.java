package com.example.demo.myconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by caoyixian on 2018/6/8.
 */
@Configuration
public class JdbcTemplateConfig {

    @Bean(name = "centerJdbcTemplate")
    @Qualifier(value = "centerJdbcTemplate")
    public JdbcTemplate getCenterJdbclate(@Qualifier(value = "centerDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
