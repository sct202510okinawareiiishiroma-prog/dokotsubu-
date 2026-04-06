package model;

import dao.AccountDAO;

public class LoginLogic {
    public User execute(User user) { // 戻り値をUserインスタンスに変更
        AccountDAO dao = new AccountDAO();
        // 存在すればUserオブジェクト、なければnullが返る
        return dao.findByLogin(user); 
    }
}