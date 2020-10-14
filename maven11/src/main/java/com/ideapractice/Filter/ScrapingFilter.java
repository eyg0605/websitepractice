package com.ideapractice.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter("/doGetServlet")
public class ScrapingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        //判断是否有session
        HttpSession session = request.getSession();
        List pb = (List) session.getAttribute("scraping");
        //判断
        if (pb!=null){
            //有session已经爬取了数据 直接跳转展示即可
            request.setAttribute("pb", pb);
            request.getRequestDispatcher("/Scraping.jsp").forward(request, response);
        }else{
            //放行 让它访问Servlet 爬取数据
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
