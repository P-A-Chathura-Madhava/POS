package bo.custom.impl;

import java.sql.SQLException;

class PlaceOrderBoIMPLTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new PlaceOrderBoIMPLTest().getOrderId();
    }


    void getOrderId() throws SQLException, ClassNotFoundException {
        System.out.println(new PlaceOrderBoIMPL().getOrderId());
    }
}