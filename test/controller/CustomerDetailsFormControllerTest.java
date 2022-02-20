package controller;

import java.sql.SQLException;

class CustomerDetailsFormControllerTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CustomerDetailsFormControllerTest().initialize();
    }

    void initialize() throws SQLException, ClassNotFoundException {
        new CustomerDetailsFormController().initialize();
    }
}