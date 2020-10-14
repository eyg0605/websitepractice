package com.ideapractice.Service;

import com.ideapractice.domain.News;
import com.ideapractice.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface NewsService {
    News login(News news);

    PageBean<News> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    void delUser(String id);

    void delSelectedNews(String[] uids);

    News findNewsById(String id);

    void updateNews(News user);

    void addUser(News user);

    List<News> findAll();
}
