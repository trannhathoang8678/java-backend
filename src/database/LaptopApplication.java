package database;

import database.services.LaptopService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LaptopApplication {
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
             connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/store_cms_plusplus","root","hoangnt");
            System.out.println("SQL connection to Database established!");
            LaptopService laptopService = new LaptopService(connection);
          //  laptopService.printLaptopList(laptopService.searchLaptop(BigDecimal.valueOf(7290000.01),BigDecimal.valueOf(18890000),null,null,null,null,null,null));
          //  laptopService.printLaptopList(laptopService.sortBySoldLaptop("DESC"));
          //  laptopService.printLaptopList(laptopService.sortBySoldLaptop("ASC"));
          //  laptopService.getStatisticByMaker();
//            laptopService.insertLaptopToDB(1,"hello","hello","hello","hello","hello","hello","hello","hello",
//                    BigDecimal.valueOf(232.22),"hello","hello",23.12f,12);
//            laptopService.printLaptopList(laptopService.searchLaptop("hello",null,null,null,null,null,null,null
//                    ,null));
            laptopService.updateSoldForLaptop(6,13);
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Connection Fail! Exception: " + e);
            return;
        }
    }
}
