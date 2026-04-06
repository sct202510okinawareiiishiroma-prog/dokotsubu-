package model;

import java.util.List;

import dao.MutterDAO;

public class GetMutterListLogic {
    public List<Mutter> execute() {
        MutterDAO dao = new MutterDAO();
        // DBから全件取得して返す
        return dao.findAll();
    }
}