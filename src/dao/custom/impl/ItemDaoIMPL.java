package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDao;
import db.DBConnection;
import entity.Customer;
import entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoIMPL implements ItemDao {
    @Override
    public boolean save(Item i) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate(
                "INSERT INTO Item VALUES (?,?,?,?)",
                i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand()
        );
    }

    @Override
    public boolean update(Item i) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate(
                "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?",
                i.getDescription(), i.getUnitPrice(), i.getQtyOnHand(), i.getCode()
        );
    }

    @Override
    public boolean delete(String code) throws ClassNotFoundException, SQLException {
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE code = ?", code);
    }

    @Override
    public Item get(String code) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE code = ?", code);
        if(rst.next()){
            return new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
        }
        return list;
    }

    @Override
    public ArrayList<Item> searchItems(String text) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE code LIKE '%"+text+"%' OR description LIKE '%"+text+"%'");
        ArrayList<Item> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
        }
        return list;
    }

    @Override
    public String getItemId() throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.executeQuery(
                "SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if(set.next()){
            String id = set.getString(1);
            int tempId = Integer.parseInt(id.split("P")[1]);
            if(tempId < 9){
                return "P00" + (tempId + 1);
            }else if(tempId < 99){
                return "P0" + (tempId + 1);
            }else if (tempId < 999){
                return "P" + (tempId + 1);
            }
        }
        return "P001";
    }
}
