package database.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class StudentManageService {
    private Connection connection;

    public StudentManageService(Connection connection) {
        this.connection = connection;
    }

    public StudentManageService() {
    }

    public void turnOffSafeMode() {
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
            while (deptIDrs.next()) {
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

    public void updateAverageScore() {
        Map<String, Integer> noOfCreditsOfCoures = new HashMap<>();
        String getOfCreditsOfCoures = "SELECT CourseID,Credits FROM courses;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getOfCreditsOfCoures);
            while (rs.next()) {
                noOfCreditsOfCoures.put(rs.getString(1), rs.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String getStudentID = "SELECT distinct StudentID from students;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getStudentID);
            while (rs.next()) {
                String studentID = rs.getString(1);
                String getMarkOfCourses = "SELECT CourseID,Mark FROM results WHERE StudentID = '" + studentID + "';";
                Map<String, Double> markOfCourse = new HashMap<>();
                Statement statement1 = connection.createStatement();
                ResultSet markRS = statement1.executeQuery(getMarkOfCourses);
                while (markRS.next()) {
                    String courseID = markRS.getString(1);
                    Double mark = markRS.getDouble(2);
                    Double value = markOfCourse.get(courseID);
                    if (value == null) {
                        markOfCourse.put(courseID, mark);
                    } else {
                        if (value < mark) {
                            markOfCourse.put(courseID, mark);
                        }
                    }
                }
                Double totalScore = 0d;
                int totalCredit = 0;
                for (String courseID : noOfCreditsOfCoures.keySet()) {
                    Double mark = markOfCourse.get(courseID);
                    if(mark!= null)
                    {
                        totalScore += mark * noOfCreditsOfCoures.get(courseID);
                        totalCredit += noOfCreditsOfCoures.get(courseID);
                    }
                }
                Double averageScore;
                if(totalCredit == 0)
                {
                    averageScore = 0d;
                }
                else
                {
                    averageScore = totalScore / totalCredit;
                }

                String updateAverageScore = "UPDATE students SET AverageScore = " + averageScore
                        + " WHERE StudentID = '" + studentID + "';";
                Statement statement2 = connection.createStatement();
                statement2.executeUpdate(updateAverageScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
