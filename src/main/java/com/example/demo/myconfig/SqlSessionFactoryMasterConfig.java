package com.example.demo.myconfig;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Mybatis主数据源配置
 * Created by caoyixian on 2018/6/8.
 */
@Configuration
@MapperScan(value = SqlSessionFactoryMasterConfig.PACKAGE,sqlSessionFactoryRef = "centerSqlSessionFactory")
public class SqlSessionFactoryMasterConfig {
    // 精确到目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.example.demo.mapper";
    static final String MAPPER_LOCATION = "classpath:mybatis/*.xml";
    @Bean(name = "centerSqlSessionFactory")
    @Qualifier(value = "centerSqlSessionFactory")
    @Primary
    public SqlSessionFactory centerSqlSessionFactory(@Qualifier(value = "centerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(SqlSessionFactoryMasterConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

}
