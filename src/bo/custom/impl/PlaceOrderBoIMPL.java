package bo.custom.impl;

import bo.custom.PlaceOrderBo;
import dao.DaoFactory;
import dao.custom.PlaceOrderDao;

import java.sql.SQLException;

public class PlaceOrderBoIMPL implements PlaceOrderBo {

    PlaceOrderDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.ORDER);

    @Override
    public String getOrderId() throws ClassNotFoundException, SQLException {
        return dao.getOrderId();
    }
}
