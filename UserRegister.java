package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.RegisterUserLogic;
import model.User;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 登録画面を表示 [cite: 35]
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegister.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            // 1. 入力画面から確認画面へ [cite: 8]
            String name = request.getParameter("name");
            String pass = request.getParameter("pass");
            User registerUser = new User(name, pass);
            
            HttpSession session = request.getSession();
            session.setAttribute("registerUser", registerUser);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegisterConfirm.jsp");
            dispatcher.forward(request, response);

        } else if (action.equals("done")) {
            // 2. 確認画面から登録処理実行 [cite: 9, 38]
            HttpSession session = request.getSession();
            User registerUser = (User) session.getAttribute("registerUser");

            ServletContext application = this.getServletContext();
            List<User> userList = (List<User>) application.getAttribute("userList");
            if (userList == null) {
                userList = new ArrayList<>();
                application.setAttribute("userList", userList);
            }

            RegisterUserLogic logic = new RegisterUserLogic();
            boolean result = logic.execute(registerUser, userList);

            if (result) {
                session.removeAttribute("registerUser");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegisterDone.jsp");
                dispatcher.forward(request, response);
            } else {
                // 重複エラー時 
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegisterError.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}