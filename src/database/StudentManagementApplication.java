package database;

import database.services.StudentManageService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentManagementApplication {
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
            StudentManageService studentManageService = new StudentManageService(connection);
//            studentManageService.setDefaultToNoOfStudentsInDepartment();
//            studentManageService.turnOffSafeMode();
//            studentManageService.updateNoOfStudentsInDepartment();
            studentManageService.updateAverageScore();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Connection Fail! Exception: " + e);
            return;
        }
    }
}
