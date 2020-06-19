<%@ page import="com.Modle.SessionProcessBean" %>
<%@ page errorPage="error.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: voghost
  Date: 2020/6/14
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
<h1 align="center">欢迎<%=request.getParameter("userName")%>
</h1>
<%
    SessionProcessBean sessionProcessBean = new SessionProcessBean(request.getParameter("userName"), request);
    if (!sessionProcessBean.isCorrectSessionUser()) {
%>
    <jsp:forward page="login.jsp"></jsp:forward>
<%
    }
%>
<form method="post" action="UploadServlet" enctype="multipart/form-data">
    <table align="center">
        <tr>
            <td>请选择要上传的文件</td>
            <td><input type="file" name="updateFile"></td>
            <td>
                <input type="submit" value="提交"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>
<h2 align="center"><a href="downloadServlet">下载文档</a></h2>
</body>
</html>
