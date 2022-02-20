package bo.custom.impl;

import bo.custom.OrderDetailsBo;
import dao.DaoFactory;
import dao.custom.OrderDetailsDao;
import dto.OrderDetailsDTO;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBoIMPL implements OrderDetailsBo {

    OrderDetailsDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ORDER_DETAIL);

    @Override
    public ArrayList<OrderDetailsDTO> getAllDetails() throws ClassNotFoundException, SQLException {
        ArrayList<OrderDetails> all = dao.getAll();
        ArrayList<OrderDetailsDTO> dtoList = new ArrayList<>();
        for (OrderDetails d :
                all) {
            dtoList.add(
                    new OrderDetailsDTO(
                            d.getId(), d.getDate(), d.getTime(), d.getCost(), d.getCustomerId(), d.getCustomerName()
                    )
            );
        }
        return dtoList;
    }
}
