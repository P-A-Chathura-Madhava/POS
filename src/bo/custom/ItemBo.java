package bo.custom;

import bo.SuperBo;
import dto.CustomerDTO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo {
    public boolean saveItem(ItemDTO dto) throws ClassNotFoundException, SQLException;
    public boolean updateItem(ItemDTO dto) throws ClassNotFoundException, SQLException;
    public boolean deleteItem(String code) throws ClassNotFoundException, SQLException;
    public ItemDTO getItem(String code) throws ClassNotFoundException, SQLException;
    public ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException, SQLException;
    public ArrayList<ItemDTO> searchItems(String text) throws ClassNotFoundException, SQLException;
    public String getItemId() throws ClassNotFoundException, SQLException;
}
