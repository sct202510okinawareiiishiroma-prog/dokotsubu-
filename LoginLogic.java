package model;

import dao.AccountDAO;

public class LoginLogic {
    public boolean execute(User user) {
        AccountDAO dao = new AccountDAO();
        User result = dao.findByLogin(user);
        // ユーザーが見つかれば(nullでなければ)ログイン成功
        return result != null;
    }
}