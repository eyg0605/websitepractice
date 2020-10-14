package com.ideapractice.Servlet;

import com.ideapractice.Service.Impl.NewsServiceImpl;
import com.ideapractice.Service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delServlet")
public class delServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String id = request.getParameter("id");
        //调用service
        NewsService service=new NewsServiceImpl();
        service.delUser(id);
        //跳转页面
        response.sendRedirect(request.getContextPath()+"/findNewsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
