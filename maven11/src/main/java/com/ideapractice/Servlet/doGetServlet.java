package com.ideapractice.Servlet;

import com.ideapractice.Utils.HttpUtils;
import com.ideapractice.domain.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name="myde",urlPatterns="/doGetServlet")
public class doGetServlet extends HttpServlet  {

    //创建HttPUtils对象
    HttpUtils httpUtils=new HttpUtils();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            //声明需要解析的初始地址
            String url = "http://www.lumine.ne.jp/ikebukuro/restaurant/";
            //创建HttPUtils对象
            HttpUtils httpUtils = new HttpUtils();
            //抓取数据
            String html = httpUtils.doGetHtml(url);
            //解析页面获取商品数据并请求转发给jsp
            List<Item> pb = this.parse(html);
            //一天抓取一次 将集合保存到Session中 并指定session存活时间
            //1.获取session
            HttpSession scrapingCon = request.getSession();
            scrapingCon.setAttribute("scraping", pb);
            //2.期望客户端关闭后，session也能相同
            Cookie c = new Cookie("JSESSIONID", scrapingCon.getId());
            //3.1设置cookie生命周期 一天
            c.setMaxAge(60 * 60 * 24);
            //3.2发送cookie
            response.addCookie(c);

            request.setAttribute("pb", pb);
            request.getRequestDispatcher("/Scraping.jsp").forward(request, response);
            System.out.println("抓取完成");
        }


    private List<Item> parse(String html) {
        //解析html获取Docmutent
        Document doc = Jsoup.parse(html);
        //使用选择器获取想要的数据 restElt is <a>
        Elements restElts = doc.select("div#panel-2>div.shopBuild>div.shopFloor>ul>li>a");

        List<Item> pb=new ArrayList<>();
        for (Element restElt : restElts) {
            //创建item对象
            Item item=new Item();
            //1.设置店铺的详情链接url
            String href = restElt.attr("href");
            System.out.println(href);
            //获取店铺的url
            String url="http://www.lumine.ne.jp"+href;
            //设置店铺的url
            item.setUrl(url);

            //2.item.setTitle(); 设置店铺名
            String title = restElt.select("p.shopName").text();
            item.setTitle(title);
            //3.设置TEXT详情
            String ShopDetailsHtml = this.httpUtils.doGetHtml(item.getUrl());
            //shopdoc是每个店铺的网址对象
            Document shopdoc = Jsoup.parse(ShopDetailsHtml);
            String text = shopdoc.select("p.shopDetailText").text();
            item.setText(text);
            //设置详情1
            Element dl = shopdoc.select("dl.shopDetailUrl").first();
            String shopUrl = dl.select("dd>a").attr("href");
            item.setShopurl(shopUrl);

            //4.设置Data
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
            item.setDate(sdf.format(new Date()));
            //5.设置Pic
            String picsrc = restElt.select("div.shopImg>img").attr("src");
            String picURL="http://www.lumine.ne.jp"+picsrc;
            item.setPic(picURL);
         /*   String imgsrc = restElt.select("div.shopImg>img").attr("src");
            String picURL="http://www.lumine.ne.jp"+imgsrc;
            //5.1下载图片
            String imgName = this.httpUtils.doGetImage(picURL);
            item.setPic(imgName);
            pb.add(item);*/

         pb.add(item);

        }

        return pb;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
