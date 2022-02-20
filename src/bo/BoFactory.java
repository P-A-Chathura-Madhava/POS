package bo;

import bo.custom.impl.CustomerBoIMPL;
import bo.custom.impl.ItemBoIMPL;
import bo.custom.impl.OrderDetailsBoIMPL;
import bo.custom.impl.PlaceOrderBoIMPL;
import dao.DaoFactory;
import dao.SuperDao;
import dao.custom.impl.CustomerDaoIMPL;
import dao.custom.impl.ItemDaoIMPL;
import dao.custom.impl.PlaceOrderDaoIMPL;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){}

    public enum BoTypes{
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL
    }

    public static BoFactory getInstance(){
        if(boFactory == null){
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public <T> T getBo(BoTypes type){
        switch (type){
            case CUSTOMER:
                return (T) new CustomerBoIMPL();
            case ITEM:
                return (T) new ItemBoIMPL();
            case ORDER:
                return (T) new PlaceOrderBoIMPL();
            case ORDER_DETAIL:
                return (T) new OrderDetailsBoIMPL();
            default:
                return null;
        }
    }
}
