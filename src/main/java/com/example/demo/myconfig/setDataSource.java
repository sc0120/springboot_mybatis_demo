package com.example.demo.myconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Caoyixian on 2018/2/7 0007.
 */
@Configuration
public class setDataSource {

    @Bean(name = "centerDataSource")
    @Qualifier(value = "centerDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.center")
    public DataSource setCenterDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return  druidDataSource;
//        return DataSourceBuilder.create().build();
    }

    @Bean(name = "centerJdbcTemplate")
    @Qualifier(value = "centerJdbcTemplate")
    public JdbcTemplate getCenterJdbclate(@Qualifier(value = "centerDataSource") DataSource dataSource){
       return new JdbcTemplate(dataSource);
    }
}
