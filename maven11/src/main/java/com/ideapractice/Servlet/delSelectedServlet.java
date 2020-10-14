package com.ideapractice.Servlet;

import com.ideapractice.Service.Impl.NewsServiceImpl;
import com.ideapractice.Service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class delSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取被选中的复选框uid
        String[] uids = request.getParameterValues("uid");
        //调用Service
        NewsService service=new NewsServiceImpl();
        service.delSelectedNews(uids);
        //返回到（重定向）查询的Servlet
        response.sendRedirect(request.getContextPath()+"/findNewsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
