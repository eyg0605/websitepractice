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

@WebServlet("/addServlet")

public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //3.封装数据
        News user=new News();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用service
        NewsService service=new NewsServiceImpl();
        service.addUser(user);
        //跳转页面
        response.sendRedirect(request.getContextPath()+"/findNewsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
