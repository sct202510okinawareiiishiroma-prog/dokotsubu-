package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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

		// 1. リクエストパラメータの取得と文字エンコーディング設定
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// 2. バリデーション（空文字チェック）
		// 入力がない場合は、DBへ問い合わせずにエラーメッセージを返す
		if (name == null || name.isEmpty() || pass == null || pass.isEmpty()) {
			request.setAttribute("errorMsg", "ユーザー名とパスワードを入力してください。");
			forwardToResult(request, response);
			return; // 処理を終了
		}

		// 3. ログイン処理の実行
		User user = new User(name, pass);
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);

		// 4. 判定結果に応じた処理
		if (isLogin) {
			// 【成功時】ユーザー情報をセッションに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			
			// 発展：成功後すぐにメイン画面へ飛ばす場合は以下のように書けます
			// response.sendRedirect("Main"); 
			// return;

		} else {
			// 【失敗時】エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "ユーザー名またはパスワードが正しくありません。");
		}

		// 5. ログイン結果画面にフォワード
		forwardToResult(request, response);
	}

	// 結果画面（loginResult.jsp）へのフォワード処理を共通化
	private void forwardToResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}
}