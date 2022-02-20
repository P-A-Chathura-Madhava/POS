package bo.custom.impl;

import java.sql.SQLException;

class ItemBoIMPLTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new ItemBoIMPLTest().searchItems();
    }

    void searchItems() throws SQLException, ClassNotFoundException {
        System.out.println(new ItemBoIMPL().searchItems(""));
    }
}