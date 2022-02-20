package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thogakade", "root", "mysql");
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        return dbConnection = (dbConnection == null) ? new DBConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
