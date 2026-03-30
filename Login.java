package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// Userインスタンスの生成
		User user = new User(name, pass);

		// アプリケーションスコープから登録済みユーザーリストを取得
		ServletContext application = this.getServletContext();
		List<User> userList = (List<User>) application.getAttribute("userList");

		// ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user, userList);

		if (isLogin) {
			// 【成功時】ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		} else {
			// 【失敗時】エラーメッセージをリクエストスコープに保存
			// これにより、JSP側で「ログインに失敗しました」と表示できます
			request.setAttribute("errorMsg", "ユーザー名またはパスワードが正しくありません。");
		}

		// ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}
}