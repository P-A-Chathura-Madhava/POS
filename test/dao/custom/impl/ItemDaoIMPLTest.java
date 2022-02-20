package dao.custom.impl;

import java.sql.SQLException;

class ItemDaoIMPLTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new ItemDaoIMPLTest().getItemId();
    }

    void searchItems() throws SQLException, ClassNotFoundException {
        System.out.println(new ItemDaoIMPL().searchItems(""));
    }

    void getItemId() throws SQLException, ClassNotFoundException {
        System.out.println(new ItemDaoIMPL().getItemId());
    }
}