package com.ideapractice.dao.Impl;

import com.ideapractice.Utils.JDBCUtils;
import com.ideapractice.dao.NewsDao;
import com.ideapractice.domain.News;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewsDaoImpl implements NewsDao {

    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDatasource());
    @Override
    public News findUserByUsernameandPassword(News news) {

      try {
          String sql="select*from cms where username=? and password=?";
          News user = template.queryForObject(sql, new BeanPropertyRowMapper<News>(News.class),news.getUsername(),news.getPassword());
          return user;
      }catch (Exception e){
          e.printStackTrace();
          return null;
      }

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //String sql="select count(*)from user";
        //1.定义初始化sql
        String sql="select count(*)from cms where 1=1";
        //2.根据条件字符串拼接sql
        StringBuffer sb=new StringBuffer(sql);
        List<Object> params=new ArrayList<Object>();
        //3.遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            //6.排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            //4.获取 value
            String value = condition.get(key)[0];
            //5.判断 记得加空格
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//?条件值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(), Integer.class,params.toArray());
    }

    @Override
    public List<News> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql="select*from cms where 1=1 ";

        //2.根据条件字符串拼接sql
        StringBuffer sb=new StringBuffer(sql);
        List<Object> params=new ArrayList<Object>();
        //3.遍历map
        Set<String> keySet = condition.keySet();
        for (String key : keySet) {
            //6.排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key)){
                continue;
            }
            //4.获取 value
            String value = condition.get(key)[0];
            //5.判断 记得加空格
            if(value!=null&&!"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//?条件值
            }
        }
        //添加分页查询
        sb.append(" limit ?,?");
        //添加分页查询参数
        params.add(start);
        params.add(rows);

        return template.query(sb.toString(), new BeanPropertyRowMapper<News>(News.class),params.toArray());
    }

    @Override
    public void del(int parseInt) {
        //定义sql
        String sql="delete from cms where id=?";
        template.update(sql, parseInt);
    }

    @Override
    public News findNewsById(int parseInt) {
        String sql="select*from cms where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<News>(News.class), parseInt);
    }

    @Override
    public void updateNews(News user) {
//定义sql
        String sql="update cms set fromdate=?,todate=?,text=?,display=? where id=?";
        template.update(sql,user.getFromdate(),user.getTodate(),user.getText(),user.getDisplay(),user.getId());
    }

    @Override
    public void add(News user) {
        //定义sql
        String sql="insert into cms values(null,?,?,?,?,null,null)";
        template.update(sql,user.getFromdate(),user.getTodate(),user.getText(),user.getDisplay());
    }

    @Override
    public List<News> findAll() {
        String sql="select * from cms";
        List<News> News = template.query(sql, new BeanPropertyRowMapper<com.ideapractice.domain.News>(com.ideapractice.domain.News.class));
        return News;
    }
}
