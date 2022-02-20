package bo.custom.impl;

import bo.custom.CustomerBo;
import dao.DaoFactory;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoIMPL;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoIMPL implements CustomerBo {

    CustomerDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException {
        return dao.save(
                new Customer(
                        dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()
                )
        );
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws ClassNotFoundException, SQLException {
        return dao.update(
                new Customer(
                        dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()
                )
        );
    }

    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return dao.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws ClassNotFoundException, SQLException {
        Customer customer = dao.get(id);
        if(customer!=null){
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws ClassNotFoundException, SQLException {
        ArrayList<Customer> all = dao.getAll();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();

        for (Customer customer :
                all) {
            dtoList.add(
                    new CustomerDTO(
                            customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()
                    )
            );
        }
        return dtoList;
    }

    @Override
    public String getCustomerId() throws ClassNotFoundException, SQLException {
        return dao.getCustomerId();
    }

    @Override
    public ArrayList<CustomerDTO> searchCustomers(String text) throws ClassNotFoundException, SQLException {
        ArrayList<Customer> all = dao.searchCustomers(text);
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();

        for (Customer customer :
                all) {
            dtoList.add(
                    new CustomerDTO(
                            customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()
                    )
            );
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getCustomerIds() throws ClassNotFoundException, SQLException {
        return dao.getCustomerIds();
    }
}
