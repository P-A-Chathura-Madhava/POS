package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DaoFactory;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoIMPL;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoIMPL implements ItemBo {

    ItemDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws ClassNotFoundException, SQLException {
        return dao.save(
                new Item(
                        dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()
                )
        );
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws ClassNotFoundException, SQLException {
        return dao.update(
                new Item(
                        dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand()
                )
        );
    }

    @Override
    public boolean deleteItem(String code) throws ClassNotFoundException, SQLException {
        return dao.delete(code);
    }

    @Override
    public ItemDTO getItem(String code) throws ClassNotFoundException, SQLException {
        Item item = dao.get(code);
        if(item!=null){
            return new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
        }
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws ClassNotFoundException, SQLException {
        ArrayList<Item> all = dao.getAll();
        ArrayList<ItemDTO> dtoList = new ArrayList<>();

        for (Item item :
                all) {
            dtoList.add(
                    new ItemDTO(
                            item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()
                    )
            );
        }
        return dtoList;
    }

    @Override
    public ArrayList<ItemDTO> searchItems(String text) throws ClassNotFoundException, SQLException {
        ArrayList<Item> all = dao.searchItems(text);
        ArrayList<ItemDTO> dtoList = new ArrayList<>();

        for (Item item :
                all) {
            dtoList.add(
                    new ItemDTO(
                            item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()
                    )
            );
        }
        return dtoList;
    }

    @Override
    public String getItemId() throws ClassNotFoundException, SQLException {
        return dao.getItemId();
    }
}
