<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
User registerUser = (User) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>登録内容確認</title></head>
<body>
<h1>以下の内容で登録しますか？</h1>
<p>ユーザー名：<%= registerUser.getName() %></p>
<p>パスワード：●●●●（安全のため非表示）</p>

<form action="UserRegister" method="post">
    <input type="hidden" name="action" value="done">
    <input type="submit" value="登録">
</form>
<p><a href="UserRegister">修正する</a></p>
</body>
</html>