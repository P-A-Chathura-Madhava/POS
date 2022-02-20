package bo.custom.impl;

import bo.custom.CustomerBo;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

class CustomerBoIMPLTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CustomerBoIMPLTest().getAllCustomers();
    }

    void getAllCustomers() throws SQLException, ClassNotFoundException {
        CustomerBo bo = new CustomerBoIMPL();
        ArrayList<CustomerDTO> allCustomers = bo.getAllCustomers();
        System.out.println(allCustomers);
    }
}