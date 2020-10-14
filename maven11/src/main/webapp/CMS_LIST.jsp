<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



    <table>
        <td><h4>NEWS</h4></td>
<c:forEach items="${list}" var="news" varStatus="s">

        <tr>
            <td><div style="display:${news.display}">${news.fromdate} ${news.text}</div></td>
        </tr>
</c:forEach>
        <tr>
            <td><a href="index.jsp">Return to HomePage</a></td>
        </tr>
    </table>
</body>
</html>
