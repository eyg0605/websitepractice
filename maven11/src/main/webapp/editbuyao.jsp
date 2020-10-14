<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>jQuery UI 日期选择器（Datepicker） - 默认功能</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    <script>
        $(function() {
            var date=new Date();
            date=date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
            $( "#datepicker" ).datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "yy-mm-dd "+date,
                showAnim: "clip",
            });


        });
    </script>
</head>
<body>


<p>日期：<input type="text" id="datepicker"></p>





<!--
<p>动画：<br>
    <select id="anim" disabled>

        <option value="clip" >Clip (UI 剪辑特效)</option>


    </select>
</p>
-->


</body>
</html>