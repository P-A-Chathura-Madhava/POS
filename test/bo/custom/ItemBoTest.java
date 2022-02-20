package bo.custom;

import bo.custom.impl.CustomerBoIMPL;
import bo.custom.impl.ItemBoIMPL;
import dto.CustomerDTO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

class ItemBoTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new ItemBoTest().getAllItems();
    }

    void getAllItems() throws SQLException, ClassNotFoundException {
        ItemBo bo = new ItemBoIMPL();
        ArrayList<ItemDTO> allItems = bo.getAllItems();
        System.out.println(allItems);
    }
}