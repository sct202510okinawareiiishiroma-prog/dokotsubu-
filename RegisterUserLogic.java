package model;

import dao.AccountDAO;

public class RegisterUserLogic {
    public boolean execute(User user) {
        AccountDAO dao = new AccountDAO();
        // データベースにユーザーを保存し、成功(true)か失敗(false)を返す
        return dao.create(user);
    }
}