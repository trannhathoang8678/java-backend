package database.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class StudentService {
    private Connection connection;

    public StudentService(Connection connection) {
        this.connection = connection;
    }

    public StudentService() {
    }
    public void turnOffSafeMode()
    {
        String sql = "SET sql_safe_updates = 0";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateNoOfStudentsInDepartment() {
        String sql = "SELECT COUNT(*),deptID FROM students GROUP BY deptID;";

        try {
            Statement statement = connection.createStatement();
            ResultSet deptIDrs = statement.executeQuery(sql);
            while(deptIDrs.next())
            {
                sql = "UPDATE departments SET NoOfStudents = " + deptIDrs.getInt(1)
                        + " WHERE DeptID = '" + deptIDrs.getString(2) + "' ;";
                Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         sql = "UPDATE departments SET NoOfStudents = 0 WHERE NoOfStudents IS NULL";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
