package dao;

import dao.custom.impl.CustomerDaoIMPL;

import java.sql.SQLException;
import java.util.ArrayList;

class CrudDAOTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new CrudDAOTest().getAll();
    }

    void getAll() throws SQLException, ClassNotFoundException {
        CrudDAO dao = new CustomerDaoIMPL();
        ArrayList all = dao.getAll();
        System.out.println(all);
    }
}