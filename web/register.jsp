<%--
  Created by IntelliJ IDEA.
  User: voghost
  Date: 2020/6/3
  Time: 下午4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp"%>
<html>
<head>
    <title>注册</title>
</head>
<body>
<h1 align="center">欢迎注册</h1>
<hr/>
<form action="registerHandlerServlet" method="post">
    <br/>
    <table align="center" border="0" height="200">
        <tr>
            <td align="right"><b>用户名:</b></td>
            <td><input type="text" name="userName"/></td>
        </tr>
        <tr>
            <td align="right"><b>密码:</b></td>
            <td><input type="password" name="password1"/></td>
        </tr>
        <tr>
            <td align="right"><b>确认密码: </b></td>
            <td><input type="password" name="password2"/></td>
        </tr>
        <tr>
            <td align="right" colspan="2">
                <input type="reset" value="重置"/>
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
