package bo.custom;

import bo.SuperBo;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {
    public boolean saveCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException;
    public boolean updateCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException;
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException;
    public CustomerDTO getCustomer(String id) throws ClassNotFoundException, SQLException;
    public ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException;
    public String getCustomerId() throws ClassNotFoundException, SQLException;
    public ArrayList<CustomerDTO> searchCustomers(String text) throws ClassNotFoundException, SQLException;
    public ArrayList<String> getCustomerIds() throws ClassNotFoundException, SQLException;
}
