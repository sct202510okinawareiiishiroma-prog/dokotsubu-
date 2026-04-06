package model;
import java.io.Serializable;

public class Mutter implements Serializable {
  private int id;          
  private String userName; 
  private String text;     

  public Mutter() { }

  // 投稿時（DB保存前）に使うコンストラクタ
  public Mutter(String userName, String text) {
    this.userName = userName;
    this.text = text;
  }

  // DBから取得した時に使うコンストラクタ
  public Mutter(int id, String userName, String text) {
    this.id = id;
    this.userName = userName;
    this.text = text;
  }

  // Getter
  public int getId() { return id; } 
  public String getUserName() { return userName; }
  public String getText() { return text; }
}