package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB {
    
    private static Connection conn;
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:db-jdbc-javafx.db");
                System.out.println("Conectado com sucesso!");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }
    
}
