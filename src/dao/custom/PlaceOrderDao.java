package dao.custom;

import dao.CrudDAO;
import entity.Order;

import java.sql.SQLException;

public interface PlaceOrderDao extends CrudDAO<Order, String> {
    public String getOrderId() throws ClassNotFoundException, SQLException;
}
