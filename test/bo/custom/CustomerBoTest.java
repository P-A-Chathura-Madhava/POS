package bo.custom;

import bo.custom.impl.CustomerBoIMPL;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

class CustomerBoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CustomerBoTest().getAllCustomers();
    }

    void getAllCustomers() throws SQLException, ClassNotFoundException {
        CustomerBo bo = new CustomerBoIMPL();
        ArrayList<CustomerDTO> allCustomers = bo.getAllCustomers();
        System.out.println(allCustomers);
    }

}