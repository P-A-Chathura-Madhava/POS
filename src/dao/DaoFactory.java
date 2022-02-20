package dao;

import dao.custom.impl.CustomerDaoIMPL;
import dao.custom.impl.ItemDaoIMPL;
import dao.custom.impl.OrderDetailsDaoIMPL;
import dao.custom.impl.PlaceOrderDaoIMPL;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public enum DaoTypes{
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL
    }

    public static DaoFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public <T> T getDao(DaoTypes type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerDaoIMPL();
            case ITEM:
                return (T) new ItemDaoIMPL();
            case ORDER:
                return (T) new PlaceOrderDaoIMPL();
            case ORDER_DETAIL:
                return (T) new OrderDetailsDaoIMPL();
            default:
                return null;
        }
    }
}
