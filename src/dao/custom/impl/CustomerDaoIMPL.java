package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import db.DBConnection;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoIMPL implements CustomerDao {
    @Override
    public boolean save(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Customer VALUES (?,?,?,?)",
                c.getId(), c.getName(), c.getAddress(), c.getSalary()
                );
    }

    @Override
    public boolean update(Customer c) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate(
                "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?",
                c.getName(), c.getAddress(), c.getSalary(), c.getId()
        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE id = ?", id);
    }

    @Override
    public Customer get(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE id = ?", id);
        if(rst.next()){
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
        }
        return list;
    }

    @Override
    public String getCustomerId() throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.executeQuery(
                "SELECT id FROM Customer ORDER BY id DESC LIMIT 1");
        if(set.next()){
            String id = set.getString(1);
            int tempId = Integer.parseInt(id.split("C")[1]);
            if(tempId < 9){
                return "C00" + (tempId + 1);
            }else if(tempId < 99){
                return "C0" + (tempId + 1);
            }else if (tempId < 999){
                return "C" + (tempId + 1);
            }
        }
        return "C001";
    }

    @Override
    public ArrayList<Customer> searchCustomers(String text) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE id LIKE '%"+text+"%' OR name LIKE '%"+text+"%' OR address LIKE '%"+text+"%'");
        ArrayList<Customer> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
        }
        return list;
    }

    @Override
    public ArrayList<String> getCustomerIds() throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.executeQuery("SELECT id FROM Customer");
        ArrayList<String> list = new ArrayList<>();
        while (set.next()){
            list.add(set.getString("id"));
        }
        return list;
    }
}
