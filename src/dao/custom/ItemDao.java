package dao.custom;

import dao.CrudDAO;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDAO<Item, String> {
    public ArrayList<Item> searchItems(String text) throws ClassNotFoundException, SQLException;
    public String getItemId() throws ClassNotFoundException, SQLException;
}
