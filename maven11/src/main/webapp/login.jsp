<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    span{
        color: red;
        font-size: 30px;
    }
</style>
<%--<center>
    <h1 align="center">CMS LOGIN</h1>
    <form action="${pageContext.request.contextPath}/loginServlet" >
        ID/Mail address<input type="text" name="username" /><br><br>
            Password <input type="password" name="password" /><br><br>
            <input type="submit" value="login">
    </form>
    <span id="errospan">${login_msg}</span>
</center>--%>
<h1 align="center">CMS LOGIN</h1>
<center><form class="form-horizontal" action="${pageContext.request.contextPath}/loginServlet">
    <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label">ID/Mail address</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="inputEmail3" name="username"placeholder="username">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="inputPassword3"  name="password" laceholder="Password">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">login</button>
        </div>
    </div>

</form>
    <span id="errospan">${login_msg}</span>
</center>

</body>
</html>
