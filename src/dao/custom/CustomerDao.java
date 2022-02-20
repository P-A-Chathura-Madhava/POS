package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDAO<Customer, String> {
    public String getCustomerId() throws ClassNotFoundException, SQLException;
    public ArrayList<Customer> searchCustomers(String text) throws ClassNotFoundException, SQLException;
    public ArrayList<String> getCustomerIds() throws ClassNotFoundException, SQLException;
}
