package dao.custom.impl;

import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

class OrderDetailsDaoIMPLTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new OrderDetailsDaoIMPLTest().getAll();
    }

    void getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> all = new OrderDetailsDaoIMPL().getAll();
        System.out.println(all);
    }
}