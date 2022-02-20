package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.PlaceOrderDao;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderDaoIMPL implements PlaceOrderDao {

    @Override
    public boolean save(Order order) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Order order) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Order get(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public String getOrderId() throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.executeQuery(
                "SELECT id FROM Orders ORDER BY id DESC LIMIT 1");
        if(set.next()){
            String id = set.getString(1);
            int tempId = Integer.parseInt(id.split("D")[1]);
            if(tempId < 9){
                return "D00" + (tempId + 1);
            }else if(tempId < 99){
                return "D0" + (tempId + 1);
            }else if (tempId < 999){
                return "D" + (tempId + 1);
            }
        }
        return "D001";
    }
}
