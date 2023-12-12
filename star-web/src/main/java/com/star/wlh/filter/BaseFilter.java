package com.star.wlh.filter;

import com.star.wlh.common.response.ResponseResult;
import com.star.wlh.common.response.ResultCodeEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class BaseFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        String token = httpRequest.getHeader("token");
        if(token == null || token.isEmpty())
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(ResponseResult.build(null, ResultCodeEnum.LOGIN_AUTH).toString());
            return;
        }
        chain.doFilter(request,response);
    }
}
