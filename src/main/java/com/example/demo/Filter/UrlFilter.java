package com.example.demo.Filter;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 又见赤月君临 on 2019/12/31.
 */
@WebFilter(filterName = "UrlFilter", urlPatterns = "/*")
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("----------------------->过滤器创建成功");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse)  servletResponse;
        String requestURI = req.getRequestURI();
        System.out.println("拦截器过滤器：请求地址"+requestURI);

        if(!requestURI.contains("login")&&!requestURI.contains("doLogin")&&!requestURI.contains("regist")&&!requestURI.contains("doRegist")){
            HttpSession session=req.getSession(false);
            if(session!=null&&session.getAttribute("userLogin")!=null)
            {
                System.out.println("user"+session.getAttribute("userLogin"));
                req.setAttribute("msg","您已登录登陆");
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                System.out.println("用户没有登录");
                req.setAttribute("msg","没有权限请先登陆");
                JSONObject res  = new JSONObject();
                res.put("status",10);
                res.put("arrMsg","您还没有登录");
                servletResponse.getWriter().append(res.toJSONString());
                rep.sendRedirect(req.getContextPath()+"/login");
                return;
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
            //servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
        }
    }
    @Override
    public void destroy(){
        System.out.println("----------------------->过滤器被销毁");
    }
}
