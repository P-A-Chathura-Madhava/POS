package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDao{
    public boolean save(T t) throws ClassNotFoundException, SQLException;
    public boolean update(T t) throws ClassNotFoundException, SQLException;
    public boolean delete(ID id) throws ClassNotFoundException, SQLException;
    public T get(ID id) throws ClassNotFoundException, SQLException;
    public ArrayList<T> getAll() throws ClassNotFoundException, SQLException;
}
