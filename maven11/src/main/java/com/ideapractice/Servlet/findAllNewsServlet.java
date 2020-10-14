package com.ideapractice.Servlet;

import com.ideapractice.Service.Impl.NewsServiceImpl;
import com.ideapractice.Service.NewsService;
import com.ideapractice.domain.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAllNewsServlet")
public class findAllNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsService service=new NewsServiceImpl();
        //调用业务类 完成查询
        List<News> list = service.findAll();
        //将users共享到request域中
        request.setAttribute("list", list);
        //请求转发给/list.jsp
        request.getRequestDispatcher("/CMS_LIST.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
