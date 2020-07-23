<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<div align="center">
    <h1>登陆页面</h1>
    <form action="${path}/user/login" method="post">
        用户名：<input type="text" name="username"><br><br>
        密&emsp;码：<input type="text" name="password"><br><br>
        <input type="checkbox" name="rememberme" value="1"/>记住密码<br><br>
        <input type="submit" value="点击登陆"/>
    </form>
</div>
</body>
</html>