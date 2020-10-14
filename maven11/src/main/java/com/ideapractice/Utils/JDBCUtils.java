package com.ideapractice.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    //声明静态变量
    private static DataSource ds;
    static {
        try {
            Properties pro=new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   //1.获取连接方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //2.获取连接池方法
    public static DataSource getDatasource(){
        return ds;
    }
}
