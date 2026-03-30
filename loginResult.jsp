<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
// セッションスコープからユーザー情報を取得（ログイン成功時のみ格納されている）
User loginUser = (User) session.getAttribute("loginUser");

// リクエストスコープからエラーメッセージを取得（ログイン失敗時にサーブレットで設定したもの）
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶログイン</h1>

<% if(loginUser != null) { %>
  <%-- ログイン成功時の表示 --%>
  <p>ログインに成功しました</p>
  <p>ようこそ <%= loginUser.getName() %> さん</p>
  <a href="Main">つぶやき投稿・閲覧へ</a>

<% } else { %>
  <%-- ログイン失敗時の表示 --%>
  <p style="color:red;">ログインに失敗しました</p>
  
  <% if(errorMsg != null) { %>
    <p style="color:red;"><%= errorMsg %></p>
  <% } %>
  
  <a href="index.jsp">トップへ戻る</a>
<% } %>

</body>
</html>