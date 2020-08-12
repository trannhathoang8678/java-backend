package database;

import database.services.LaptopService;
import database.services.StudentService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentManagement {
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
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/manage_student","root","hoangnt");
            System.out.println("SQL connection to Database established!");
            StudentService studentService = new StudentService(connection);
//            studentService.setDefaultToNoOfStudentsInDepartment();
            studentService.turnOffSafeMode();
            studentService.updateNoOfStudentsInDepartment();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Connection Fail! Exception: " + e);
            return;
        }
    }
}
