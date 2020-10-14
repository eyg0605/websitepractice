package com.ideapractice.Servlet;

import com.ideapractice.Service.Impl.NewsServiceImpl;
import com.ideapractice.Service.NewsService;
import com.ideapractice.domain.News;
import com.ideapractice.domain.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findNewsServlet")
public class findNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //1.获取参数 currentPage and rows
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //在NewsList中，超链接的路径会指向这里，但是如果只有路径，没有上面两个参数，系统会报错，因为Servlet无法获取参数
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "10";
        }
        //获取请求参数集合（设置条件查询）
        Map<String, String[]> condition = request.getParameterMap();
        //2.调用service查询
        NewsService service = new NewsServiceImpl();
        PageBean<News> pb = service.findUserByPage(currentPage, rows, condition);
        System.out.println(pb);
        //请求转发到list.jsp
        request.setAttribute("pb", pb);
        //判断Referer Root
        //String referer = request.getHeader("Referer");
        //if (referer.contains("CmsAdmini.jsp")||referer.contains("findNewsServlet")||referer.contains("findUserServlet")){
        request.getRequestDispatcher("/cmslist.jsp").forward(request, response);
    }





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
