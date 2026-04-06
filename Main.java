package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.GetMutterListLogic;
import model.Mutter;
import model.PostMutterLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * つぶやき画面の表示処理
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 最新のつぶやきリストをDBから取得してリクエストスコープにセット
        GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
        List<Mutter> mutterList = getMutterListLogic.execute();
        request.setAttribute("mutterList", mutterList);

        // 2. ログインチェック
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            // 未ログインならトップ画面へ
            response.sendRedirect("index.jsp");
        } else {
            // ログイン済みならメイン画面へフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * つぶやきの投稿処理
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");

        // 2. 入力値のバリデーション（空チェック ＆ 255文字制限）
        if (text != null && text.length() != 0 && text.length() <= 255) {
            
            // セッションスコープからログインユーザー情報を取得
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");

            // つぶやきをDBに保存
            Mutter mutter = new Mutter(loginUser.getName(), text);
            PostMutterLogic postMutterLogic = new PostMutterLogic();
            postMutterLogic.execute(mutter);

            // 投稿成功後は、二重投稿防止のためリダイレクトで表示処理(doGet)を呼び出す
            response.sendRedirect("Main");
            
        } else {
            // 3. バリデーションエラー時の処理
            if (text == null || text.length() == 0) {
                request.setAttribute("errorMsg", "つぶやきが入力されていません");
            } else {
                // 255文字を超えた場合
                request.setAttribute("errorMsg", "つぶやきは255文字以内で入力してください（現在: " + text.length() + "文字）");
            }
            
            // エラー表示のため、現在のリストを再取得してリクエストスコープにセット
            GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
            List<Mutter> mutterList = getMutterListLogic.execute();
            request.setAttribute("mutterList", mutterList);
            
            // 入力中だったテキストを保持したい場合はここに追加（今回は省略）
            
            // メイン画面へフォワード（エラーメッセージを含めて表示）
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }
}