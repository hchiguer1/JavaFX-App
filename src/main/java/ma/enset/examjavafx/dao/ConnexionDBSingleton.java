package ma.enset.examjavafx.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionDBSingleton {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/DB-STOCK1","root","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
        return connection;
    }

}
