package bo.custom;

import bo.SuperBo;
import dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBo extends SuperBo {
    public ArrayList<OrderDetailsDTO> getAllDetails() throws ClassNotFoundException, SQLException;
}
