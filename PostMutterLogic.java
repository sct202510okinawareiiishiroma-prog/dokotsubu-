package model;

import dao.MutterDAO;

public class PostMutterLogic {
    public boolean execute(Mutter mutter) { // 戻り値をbooleanに変更
        MutterDAO dao = new MutterDAO();
        return dao.create(mutter); // 保存の成否を返す
    }
}