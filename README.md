どこつぶ (docoTsubu) 
#プロジェクト概要
Java Servlet/JSPを用いた、ユーザー参加型のつぶやき投稿アプリケーション。
「スッキリわかるサーブレット&JSP入門」の学習をベースに、独自のユーザー登録機能およびバリデーションを実装しました。

##ディレクトリ構造 (主要部分)
Plaintext
src/main/
├── java/
│   ├── servlet/             # Controller
│   │   ├── Login.java
│   │   ├── Logout.java
│   │   ├── Main.java
│   │   └── UserRegister.java
│   └── model/               # Model (JavaBeans/Logic)
│       ├── User.java
│       ├── Mutter.java
│       ├── LoginLogic.java
│       └── RegisterUserLogic.java
└── webapp/
    ├── index.jsp            # ログイン・新規登録入り口
    └── WEB-INF/jsp/         # View (隠蔽フォルダ)
        ├── main.jsp
        ├── loginResult.jsp
        ├── userRegister.jsp
        └── ... (他登録フロー用JSP)
###技術スタック
Java 21
Jakarta EE (Tomcat 10+)


####今後の課題 (Next Step)
データベース (MySQL) との連携によるデータの永続化
DAO (Data Access Object) パターンの導入
CSSによるUI/UXの改善
