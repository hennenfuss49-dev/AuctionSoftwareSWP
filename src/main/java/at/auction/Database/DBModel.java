package at.auction.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBModel {
    private final Connection connection;

    static DBModel dbModel;

    public DBModel(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBModel getInstance(){
        if(dbModel == null)
            dbModel = new DBModel();
        return dbModel;
    }

}
