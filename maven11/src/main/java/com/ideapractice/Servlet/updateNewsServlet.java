package com.ideapractice.Servlet;


import com.ideapractice.Service.Impl.NewsServiceImpl;
import com.ideapractice.Service.NewsService;
import com.ideapractice.domain.News;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateNewsServlet")
public class updateNewsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取map
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        News user=new News();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service
        NewsService service=new NewsServiceImpl();
        service.updateNews(user);

        //重定向到查询Servlet中
        response.sendRedirect(request.getContextPath()+"/findNewsServlet");
        //request.getRequestDispatcher("findNewsServlet").forward(request, response);
        System.out.println("我在这里");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
