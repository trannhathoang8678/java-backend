package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LaptopApplication {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Drive Driver Not Found");
            return;
        }
        System.out.println("MySQL JDBC Registered");
        Connection connection = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc::mysql://127.0.0.1:3306/store_cms_plusplus","root","hoangnt");
            System.out.println("SQL connection to Database established!");
            connection.close();
        }
        catch (SQLException)
        {
            System.out.println("Connection Fail! Exception: " + e);
            return;
        }
    }
}
