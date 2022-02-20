package bo.custom.impl;

import dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

class OrderDetailsBoIMPLTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new OrderDetailsBoIMPLTest().getAllDetails();
    }

    void getAllDetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> allDetails = new OrderDetailsBoIMPL().getAllDetails();
        System.out.println(allDetails);
    }
}