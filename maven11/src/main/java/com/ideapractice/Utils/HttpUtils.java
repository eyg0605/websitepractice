package com.ideapractice.Utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class HttpUtils {
    //根据请求地址下载页面
    private  PoolingHttpClientConnectionManager cm;

    public HttpUtils(){
        this.cm=new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }
    public String doGetHtml(String url){
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        //设置httpGet请求对象，设置url地址
        HttpGet httpGet=new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response=null;
        try {
            //使用httpClient发起请求 ，获取响应
            response = httpClient.execute(httpGet);
            //判断响应体Entity是否不为空 如果不为空，则可以使用EntityUtils
            if (response.getEntity()!=null){
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                return content;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String doGetImage(String url){
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        //设置httpget请求对象，设置url地址
        HttpGet httpGet=new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response=null;
        try {
            //使用httpClient发起请求 ，获取响应
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode()==200){
                //判断响应体Entity是否不为空 如果不为空，则可以使用EntityUtils
                if (response.getEntity()!=null){
                    //下载图片
                    //获取图片的后缀
                    String extName=url.substring(url.lastIndexOf("."));
                    //创建图片名--重命名图片
                    String picName= UUID.randomUUID().toString()+extName;
                    String picNAME="E:\\new idea file\\maven11\\src\\main\\webapp\\imges\\"+picName;
                    //下载图片
                    //声明OutputStream
                    FileOutputStream fos=new FileOutputStream(new File(picNAME));
                    //完成图片下载
                    response.getEntity().writeTo(fos);
                    //返回图片的名称

                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //如果下载失败返回空
        return null;
    }




    private RequestConfig getConfig() {
        RequestConfig config=RequestConfig.custom()
                .setConnectTimeout(1000) //创建链接的最长时间
                .setConnectionRequestTimeout(500)//获取链接的最长时间
                .setSocketTimeout(10000)//数据传输的最长时间
                .build();

        return config;
    }


}
