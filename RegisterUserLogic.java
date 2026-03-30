package model;

import java.util.List;

public class RegisterUserLogic {
    public boolean execute(User user, List<User> userList) {
        // ユーザー名が既に使用されているかチェック [cite: 24]
        for (User u : userList) {
            if (u.getName().equals(user.getName())) {
                return false; // 重複あり [cite: 24]
            }
        }
        // 重複がなければリストに追加 [cite: 23]
        userList.add(user);
        return true;
    }
}