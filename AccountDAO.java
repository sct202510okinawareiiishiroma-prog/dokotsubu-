package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DatabaseConfig;
import model.User;

public class AccountDAO {
	// 1. ログイン情報の照合
	public User findByLogin(User user) {
		User loginUser = null;
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER,
					DatabaseConfig.PASS)) {
				String sql = "SELECT NAME, PASS FROM USERS WHERE NAME = ? AND PASS = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user.getName());
				pStmt.setString(2, user.getPass());

				ResultSet rs = pStmt.executeQuery();
				if (rs.next()) {
					loginUser = new User(rs.getString("NAME"), rs.getString("PASS"));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return loginUser;
	}

	// 2. 新規ユーザーの追加
	public boolean create(User user) {
		try {
			// JDBCドライバのロード
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER,
					DatabaseConfig.PASS)) {
				String sql = "INSERT INTO USERS(NAME, PASS) VALUES(?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user.getName());
				pStmt.setString(2, user.getPass());

				int result = pStmt.executeUpdate();
				return (result == 1);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}