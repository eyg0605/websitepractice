<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    window.onload=function () {
        var element = document.getElementById("button");
            element.onclick=function () {
                if (confirm("ログアウトしますか")){
                    location.href="${pageContext.request.contextPath}/logOutServlet"
                }
            }


    }
</script>
<body>
    <table  width="200" height="200">
        <tr>
            <td><h3>CMS</h3></td>

            <th colspan="2" ><h3>${pageContext.session.getAttribute("loginUser").username}</h3></th>
        </tr>
        <tr>
            <td><a href="findNewsServlet">News List</a></td>
        </tr>
        <tr>
            <td><a href="add.jsp">News add</a></td>


        </tr>
        <tr>
            <td><a href="javascript:void (0)" id="button">Logout</a></td>


        </tr>

    </table>
</body>
</html>
