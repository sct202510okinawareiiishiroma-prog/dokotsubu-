# どこつぶ (docoTsubu)

## プロジェクト概要
Java Servlet/JSPを用いた、ユーザー参加型のつぶやき投稿アプリケーション。
「スッキリわかるサーブレット&JSP入門」の学習をベースに、独自のユーザー登録機能およびバリデーションを実装しました。

## ディレクトリ構造 (主要部分)
```text
dokotsubu
├── src/main/java
│   ├── dao
│   │   ├── AccountDAO.java         (ユーザーDB操作)
│   │   └── MutterDAO.java          (つぶやきDB操作)
│   ├── model
│   │   ├── DatabaseConfig.java      (DB接続設定)
│   │   ├── User.java               (ユーザー情報)
│   │   ├── Mutter.java             (つぶやき情報)
│   │   ├── LoginLogic.java         (ログインロジック)
│   │   ├── PostMutterLogic.java    (投稿ロジック)
│   │   ├── GetMutterListLogic.java (取得ロジック)
│   │   └── RegisterUserLogic.java  (登録ロジック)
│   └── servlet
│       ├── Login.java              (ログイン制御)
│       ├── Logout.java             (ログアウト制御)
│       ├── Main.java               (メイン・バリデーション制御)
│       └── UserRegister.java       (新規登録フロー制御)
└── src/main/webapp
    ├── index.jsp                  (トップ画面)
    └── WEB-INF
        └── jsp
            ├── main.jsp            (つぶやき画面)
            ├── loginResult.jsp     (ログイン結果)
            ├── logout.jsp          (ログアウト完了)
            ├── userRegister.jsp    (登録入力)
            ├── userRegisterConfirm.jsp (登録確認)
            ├── userRegisterDone.jsp (登録完了)
            └── userRegisterError.jsp (登録エラー)
###技術スタック
-Java 21
-Jakarta EE (Tomcat 10+)


####今後の課題 (Next Step)
-ユーザーによる削除機能の追加
-CSSによるUI/UXの改善
