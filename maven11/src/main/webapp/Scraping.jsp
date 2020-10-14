<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>


<html>
<head>
    <title>Scraping</title>
</head>
    <body>
    <center><h3>Scraping List</h3></center>
        <c:forEach  items="${pb}" var="items">
            <hr>
            <div id="main" style="width:800px;  height:200px;" >
                <div id="left" style="float:left ;  width:30%;  height:100%;">
                    <img src="${items.pic}" width="100%" height="100%">
                </div>
                <div id="right" style="float:left ;  width:70%; height:100%;">
                    <table width="100%" border="1px">
                        <tr>
                            <td align="center">${items.title}</td>
                            <td align="center">${items.date}</td>
                        </tr>
                    </table>
                    <span>オフィシャルサイト</span>
                    <span><a href="${items.shopurl}">${items.shopurl}</a></span><br><br><br>
                    <div style="width: 100%;height: 100%;font-size: 14px" >${items.text}</div>
                </div>
                <hr width="800px" >
            </div>




        </c:forEach>
</body>
</html>
