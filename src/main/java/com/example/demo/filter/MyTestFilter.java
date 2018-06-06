package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Caoyixian on 2018/2/14 0014.
 */
public class MyTestFilter implements Filter {

    private String excludedPages;
    private String[] excludedPageArray;

    @Override
    public void destroy()  {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {//判断是否在过滤url之外
            if(((HttpServletRequest) servletRequest).getServletPath().equals(page)){
                isExcludedPage = true;
                break;
            }
        }
        if(isExcludedPage) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            String access_token = servletRequest.getParameter("access_token");
            if(null != access_token && !access_token.equals("")){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                // 权限验证
                servletResponse.setContentType("application/json; charset=utf-8");
                OutputStream out = servletResponse.getOutputStream();
                out.write("{\"code\":\"1\",\"msg\":\"sign is not valid\"}".getBytes());
                out.flush();
                out.close();
                System.out.println("自定义过滤器->doFilter");
            }
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       excludedPages = filterConfig.getInitParameter("excludedPages");
       if(!excludedPages.isEmpty()){
           excludedPageArray = excludedPages.split(",");
       }
    }

}
