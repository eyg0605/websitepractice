<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<center><h1>idealump Test Page</h1></center>

<table width="600px">
    <tr align="center">
        <td><a href="weatherMain.jsp" target="iframe_a">Weather</a></td>
        <td><a href="map.jsp" target="iframe_a">Map</a></td>
        <td><a href="doGetServlet" >Scraping</a></td>
        <td><a href="findAllNewsServlet">CMS news</a></td>
        <td><a href="login.jsp">CMS 登録</a></td>
        <td><a href="CmsAdmini.jsp">CMS 管理画面</a></td>
    </tr>
</table>
<hr>


<iframe height="800px" width="70%"  name="iframe_a"  style="border:none;"></iframe>


</body>
</html>