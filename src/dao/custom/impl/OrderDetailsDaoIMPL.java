package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailsDao;
import entity.OrderDetails;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDaoIMPL implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetails orderDetails) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public OrderDetails get(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.executeQuery("SELECT o.id, o.date, c.id, c.name FROM Customer c INNER JOIN Orders o ON c.id=o.customerId");
        ArrayList<OrderDetails> list = new ArrayList<>();

        while (set.next()){
            list.add(
                    new OrderDetails(
                            set.getString(1),
                            set.getString(2),
                            "",
                            0.00,
                            set.getString(3),
                            set.getString(4)
                    )
            );
        }
        return list;
    }
}
