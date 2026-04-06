package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DatabaseConfig;
import model.Mutter;

public class MutterDAO {

    // 1. つぶやきを全件取得するメソッド
    public List<Mutter> findAll() {
        List<Mutter> mutterList = new ArrayList<>();

        // JDBCドライバのロード
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバをロードできませんでした");
        }
        
        // データベース接続とSQL実行
        // ORDER BY ID DESC により、新しい投稿が上に来るようにしています
        String sql = "SELECT ID, NAME, TEXT FROM MUTTERS ORDER BY ID DESC";

        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            // 結果表（ResultSet）から1行ずつ取り出してMutterインスタンスを生成
            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("NAME");
                String text = rs.getString("TEXT");
                Mutter mutter = new Mutter(id, userName, text);
                mutterList.add(mutter);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return mutterList;
        }
        return mutterList;
    }

    // 2. つぶやきを1件登録するメソッド
    public boolean create(Mutter mutter) {
        // JDBCドライバのロード
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "INSERT INTO MUTTERS(NAME, TEXT) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(
                DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setString(1, mutter.getUserName());
            pStmt.setString(2, mutter.getText());

            int result = pStmt.executeUpdate();
            return (result == 1);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}