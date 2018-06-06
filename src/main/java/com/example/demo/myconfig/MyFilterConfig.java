package com.example.demo.myconfig;

/**
 * Created by Caoyixian on 2018/2/14 0014.
 */

import com.example.demo.filter.MyTestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

    @Bean
    public MyTestFilter myTestFilter(){
        return new MyTestFilter();
    }
    @Bean
    public FilterRegistrationBean filterRegist(){
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(myTestFilter());
        frBean.addUrlPatterns("/hello/*");
        frBean.addInitParameter("excludedPages","/hello/login,/test/*,/home/*" +
                "");
        return frBean;
    }

}
