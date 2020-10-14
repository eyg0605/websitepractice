package com.ideapractice.Service.Impl;

import com.ideapractice.Service.NewsService;
import com.ideapractice.dao.Impl.NewsDaoImpl;
import com.ideapractice.dao.NewsDao;
import com.ideapractice.domain.News;
import com.ideapractice.domain.PageBean;

import java.util.List;
import java.util.Map;

public class NewsServiceImpl implements NewsService {
    private NewsDao dao = new NewsDaoImpl();
    @Override
    public News login(News news) {
        return dao.findUserByUsernameandPassword(news);
    }

    @Override
    public PageBean<News> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        //1.创建PageBean对象
        PageBean<News> pb=new PageBean<News>();
        //2.设置参数
        int currentPage=Integer.parseInt(_currentPage);
        pb.setCurrentPage(currentPage);
        int rows=Integer.parseInt(_rows);
        pb.setRows(rows);

        //加一个判断 让前端的上一页功能不能点
        if (currentPage<=0){
            currentPage=1;
        }

        //4.调用dao.findTotalPage()   获取总条数
        int totalCount=dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //5.获取总页码
        int totalPage=(totalCount % rows )==0? totalCount /rows: (totalCount/rows)+1;
        pb.setTotalPage(totalPage);

        if (currentPage>totalPage){
            currentPage=totalPage;
        }
        int start=(currentPage-1)*rows;
        //3.调用dao.findUserByPage(start,rows);  获取list
        List<News> list=dao.findByPage(start,rows,condition);
        pb.setList(list);
        return pb;
    }

    @Override
    public void delUser(String id) {
        dao.del(Integer.parseInt(id));
    }

    @Override
    public void delSelectedNews(String[] uids) {
        if (uids != null && uids.length > 0) {
            for (String uid : uids) {
                dao.del(Integer.parseInt(uid));
            }
        }
    }

    @Override
    public News findNewsById(String id) {
        return dao.findNewsById(Integer.parseInt(id));
    }

    @Override
    public void updateNews(News user) {
        dao.updateNews(user);
    }

    @Override
    public void addUser(News user) {
        dao.add(user);
    }

    @Override
    public List<News> findAll() {
        return dao.findAll();
    }
}
