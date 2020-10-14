package com.ideapractice.Servlet;

import com.ideapractice.Service.Impl.NewsServiceImpl;
import com.ideapractice.Service.NewsService;
import com.ideapractice.domain.News;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //获取username and password
        Map map =request.getParameterMap();
        //创建对象
        News news= new News();
        try {
            BeanUtils.populate(news, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service
        NewsService newsService=new NewsServiceImpl();
        News loginUser = newsService.login(news);
        //创建session 将loginUser存进去
        HttpSession session = request.getSession();

        //5.判断是否成功
        if (loginUser!=null){
            //成功
            //存储user到session
            session.setAttribute("loginUser", loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/CmsAdmini.jsp");
        }else{
            //失败
            //提示信息
            request.setAttribute("login_msg", "ユーザー名またはパスワードが無効です");
            //跳转页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
