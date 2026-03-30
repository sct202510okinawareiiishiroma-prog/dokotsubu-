<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>ユーザー登録</title></head>
<body>
    <h2>新規ユーザー登録</h2>
    <form action="UserRegister" method="post">
        ユーザー名（20文字以内）：<br>
        <input type="text" name="name" maxlength="20" required><br>
        パスワード（4文字以上の英数字）：<br>
        <input type="password" name="pass" minlength="4" pattern="^[a-zA-Z0-9]+$" required><br><br>
        <button type="submit">確認画面へ</button>
    </form>
    <p><a href="index.jsp">戻る</a></p>
</body>
</html>