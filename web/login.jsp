<%--
  Created by IntelliJ IDEA.
  User: voghost
  Date: 2020/6/2
  Time: 下午3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"%>
<html>
<head>
    <title>login</title>

</head>
<body>
<h1 align="center">欢迎登录系统</h1>
<hr/>
<form action="loginHandler" method="post">
    <br/>
    <table align="center" border="0" height="150">
        <tr>
            <td align="right"><b>用户名:</b></td>
            <td><input type="text" name="userName"/></td>
        </tr>
        <tr>
            <td align="right"><b>密码:</b></td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td align="right" colspan="2">
                <input type="reset" value="重置"/>
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
<h3 align="center">
    没有账号?   <a href="register.jsp" >注册一个</a>
</h3>

</body>
</html>
