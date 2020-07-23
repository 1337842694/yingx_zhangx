<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1 align="center">这是主页面</h1>
<%--<shiro:authenticated>
    <div align="right">
        你好<span style="color: aqua"><shiro:principal/></span>，欢迎来到主页面，<a href="${path}/logout">退出</a>
    </div>
    <div align="left">
        <button>用户管理</button><br><br>
        <button>类别管理</button><br><br>
        <button>视频管理</button><br><br>
        <button>反馈管理</button><br><br>
        <button>日志管理</button><br><br>
        <button>管理员管理</button><br><br>

    </div>
</shiro:authenticated>
<shiro:notAuthenticated>
    <div align="right">
        你好,想要浏览信息请先<a href="${path}/user/login.jsp">登录</a>
    </div>
</shiro:notAuthenticated>--%>

<shiro:user>
    <div align="right">
        你好<span style="color: aqua"><shiro:principal/></span>，欢迎来到主页面，<a href="${path}/logout">退出</a>
    </div>
    <div align="left">
        <shiro:hasAnyRoles name="admin,sAdmin">
            <button>用户管理</button>
            <br><br>
            <%--当前主体是否有该权限--%>
            <shiro:hasPermission name="user:select">
                用户查询<br><br>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:update">
                用户修改<br><br>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:delete">
                用户删除<br><br>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:insert">
                用户添加<br><br>
            </shiro:hasPermission>
            <button>类别管理</button>
            <br><br>
            <button>视频管理</button>
            <br><br>
        </shiro:hasAnyRoles>

        <shiro:lacksRole name="sAdmin">
            <button>反馈管理</button>
            <br><br>
        </shiro:lacksRole>

        <shiro:hasRole name="sAdmin">
            <button>日志管理</button>
            <br><br>
            <button>管理员管理</button>
            <br><br>
            <shiro:hasPermission name="admin:select">
                管理员查询<br><br>
            </shiro:hasPermission>
            <shiro:hasPermission name="admin:update">
                管理员修改<br><br>
            </shiro:hasPermission>
            <shiro:hasPermission name="admin:delete">
                管理员删除<br><br>
            </shiro:hasPermission>
        </shiro:hasRole>


    </div>
</shiro:user>
<shiro:guest>
    <div align="right">
        你好,想要浏览信息请先<a href="${path}/user/login.jsp">登录</a>
    </div>
</shiro:guest>


<%--<shiro:hasPermission name="user:select"></shiro:hasPermission>
<shiro:lacksPermission name="user:delete"></shiro:lacksPermission>--%>


</body>
</html>