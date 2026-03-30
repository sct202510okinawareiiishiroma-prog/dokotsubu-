package model;

import java.util.List;

public class LoginLogic {
	// 引数にユーザーリストを追加
	public boolean execute(User user, List<User> userList) {
		if (userList != null) {
			for (User u : userList) {
				if (u.getName().equals(user.getName()) && u.getPass().equals(user.getPass())) {
					return true;
				}
			}
		}
		return false;
	}
}