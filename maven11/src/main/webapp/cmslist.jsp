<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
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
    <title>News List</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.3.1.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="bootstrap/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function del(id){
            if (confirm("削除しますか")){
                location.href="${pageContext.request.contextPath}/delServlet?id="+id;
            }
        }
        //删除选中条目的细节 1.做一个全选与全不选，2.删除之前要确认 3.如果什么也不选 就删除 系统会报错 解决方法如下 提交之前要先判断有没有条目被选中了
        window.onload = function(){
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function(){
                if(confirm("削除しますか")){

                    var flag = false;
                    //判断是否有选中条目
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if(cbs[i].checked){
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }

                    if(flag){//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }

                }

            }
            //1.获取第一个cb
            document.getElementById("firstCb").onclick = function(){
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("uid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;

                }

            }


        }

    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">News List</h3>
    <div>
        <form class="form-inline"  action="${pageContext.request.contextPath}/findNewsServlet" style="float: left" method="post" >
            <div class="form-group">
                <label for="exampleInputName2"></label>
                <input type="text"  name="text" class="form-control" id="exampleInputName2">
            </div>

            <button type="submit" class="btn btn-default" placeholder="123">Search</button>
        </form>
    </div>

    <div style="float: right;margin: 5px" >
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">Add News</a>
        <a class="btn btn-primary" href="javascript:void(0)" id="delSelected">delete selected</a>
        <a class="btn btn-primary" href="findAllNewsServlet">return</a>
    </div>

    <form action="${pageContext.request.contextPath}/delSelectedServlet" method="post" id="form">

        <table border="1" class="table table-bordered table-hover">


            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>ID</th>
                <th>FromDate</th>
                <th>ToDate</th>
                <th>Text</th>
                <th>Display</th>
                <th>操作</th>

            </tr>
            <c:forEach items="${pb.list}" var="users" varStatus="s">
                <tr>
                    <th><input type="checkbox" name="uid" value="${users.id}"></th>
                    <td>${s.count}</td>
                    <td>${users.fromdate}</td>
                    <td>${users.todate}</td>
                    <td>${users.text}</td>
                    <td>${users.display}</td>

                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${users.id}">edit</a>
                        <a class="btn btn-default btn-sm" href="javascript:del(${users.id})">delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage==1}">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage!=1}">
                <li >
                    </c:if>


                    <a href="${pageContext.request.contextPath}/findNewsServlet?currentPage=${pb.currentPage-1}&rows=10" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">

                    <c:if test="${pb.currentPage==i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/findNewsServlet?currentPage=${i}&rows=10">${i}</a></li>
                    </c:if>

                    <c:if test="${pb.currentPage!=i}">
                        <li><a href="${pageContext.request.contextPath}/findNewsServlet?currentPage=${i}&rows=10">${i}</a></li>
                    </c:if>

                </c:forEach>
                <c:if test="${pb.currentPage==pb.totalPage}">
                <li class="disabled">
                    </c:if>
                    <c:if test="${pb.currentPage!=pb.totalPage}">
                <li >
                    </c:if>
                    <a href="${pageContext.request.contextPath}/findNewsServlet?currentPage=${pb.currentPage+1}&rows=10" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px">
                        共${pb.totalCount}記録 共${pb.totalPage}页
                    </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
