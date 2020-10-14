package com.ideapractice.Filter;

import com.ideapractice.domain.News;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/CmsAdmini.jsp")
public class cmsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0.强制转换
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        //判断session中是否有loginUser
        HttpSession session = request.getSession();
        News loginUser = (News) session.getAttribute("loginUser");
        //如果有 直接跳转到CmsAdmini.jsp
        if (loginUser!=null){
            //登录成功，直接放行
            chain.doFilter(req, resp);
        }else {
            //说明还未登录，跳转页面
            request.setAttribute("login_msg", "まだ登録してないてす、登録してください");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
