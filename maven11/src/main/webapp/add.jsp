<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<!-- HTML5文档-->
<!doctype html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.3.1.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <%--<script src="js/jquery-3.3.1.js"></script>--%>
    <script src="js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">

    <script>
        $(function() {
            var date=new Date();
            date=date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
            $( "#fromdate" ).datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "yy-mm-dd "+date,
                showAnim: "clip",
            });
            $( "#todate" ).datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "yy-mm-dd "+date,
                showAnim: "clip",
            });


        });
    </script>
</head>
<body>
<div class="container">
    <center><h3>CMS News Edit</h3></center>
    <form action="${pageContext.request.contextPath}/addServlet" method="post">
        <div class="form-group">
            <input type="hidden" class="form-control"  name="id" value="null" />
        </div>



        <div class="form-group">
            <label for="fromdate">FROM DATE</label>
            <input type="text" class="form-control" id="fromdate" name="fromdate"  >
        </div>

        <div class="form-group">
            <label for="todate">TO DATE</label>
            <input type="text" class="form-control" id="todate" name="todate" >
        </div>



        <div class="form-group">
            <label for="News">News</label>

            <textarea rows="5" cols="155" id="News" name="text"  ></textarea>
        </div>
        <%--<textarea rows="20" cols="20"  ></textarea>--%>

        <div class="form-group">
            <label>STATUS</label>
                <input type="radio" name="display" value="block" checked="checked"/>Enable
                <input type="radio" name="display" value="none"/>Disable
        </div>



        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="Edit" />
         <%--   <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />--%>
        </div>
    </form>
</div>
</body>
</html>