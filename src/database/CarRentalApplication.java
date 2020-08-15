package database;

import database.services.CarRentalService;
import database.services.LaptopService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CarRentalApplication {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Drive Driver Not Found");
            return;
        }
        System.out.println("MySQL JDBC Registered");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/thue_xe_cms","root","hoangnt");
            System.out.println("SQL connection to Database established!");
            CarRentalService carRentalService = new CarRentalService(connection);
//            carRentalService.carLineOver5seats();
//            carRentalService.getProvidersInfoInCondition();
//            carRentalService.getProvidersInfoInSort();
//            carRentalService.countProviderStartAtTime("2015-11-20");
            carRentalService.printCaRManufacters();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Connection Fail! Exception: " + e);
            return;
        }
    }
}
