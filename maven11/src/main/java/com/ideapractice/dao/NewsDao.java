package com.ideapractice.dao;

import com.ideapractice.domain.News;

import java.util.List;
import java.util.Map;

public interface NewsDao {
    public News findUserByUsernameandPassword(News news);

    int findTotalCount(Map<String, String[]> condition);

    List<News> findByPage(int start, int rows, Map<String, String[]> condition);

    void del(int parseInt);

    News findNewsById(int parseInt);

    void updateNews(News user);

    void add(News user);

    List<News> findAll();
}
