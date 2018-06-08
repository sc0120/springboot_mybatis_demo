package com.example.demo.myconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置 使用阿里druid
 * Created by Caoyixian on 2018/2/7 0007.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "centerDataSource")
    @Qualifier(value = "centerDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.center")
    public DataSource setCenterDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
//        return DataSourceBuilder.create().build();
    }



}
