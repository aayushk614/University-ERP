package DAO;


import DTO.EnrollmentsDTO;
import Database.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

public class EnrollmentsDAO {

    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public EnrollmentsDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void displaytableJoin(){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT s.*, e.* FROM student s JOIN Enrollments e ON s.studentID = e.StudentID");

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {


                // Iterate through the result set and print all column values of each row
                // Iterate through columns and print values
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    // Print column name and value
                    System.out.println(columnName + ": " + value);
                }
                System.out.println("-----"); // Separate rows for clarity
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
    }


    public void displayEnrollmentsTable(){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Enrollments");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {

                // Iterate through the result set and print all column values of each row
                // Iterate through columns and print values
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    // Print column name and value
                    System.out.println(columnName + ": " + value);
                }
                System.out.println("-----"); // Separate rows for clarity
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
    }


/*
    private int EnrollmentID;
    private String studentID;
    private String CourseID;
    private String Grade;
    private int NumericGrade;
*/
    // Methods to add new student
    public void addEnrollmentsDAO(EnrollmentsDTO enrollmentsDTO) {
        try {
            String query = "SELECT * FROM Enrollments WHERE EnrollmentID='"
                    +enrollmentsDTO.getEnrollmentID()
                    + "' AND StudentID='"
                    +enrollmentsDTO.getStudentID()
                    + "' AND SubjectID='"
                    +enrollmentsDTO.getCourseID()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Enrollment already exists.");
            else
                addFunction(enrollmentsDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFunction(EnrollmentsDTO enrollmentsDTO) {
        try {
            String query = "INSERT INTO Enrollments VALUES(?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, enrollmentsDTO.getEnrollmentID());
            prepStatement.setString(2, enrollmentsDTO.getStudentID());
            prepStatement.setString(3, enrollmentsDTO.getCourseID());
            prepStatement.setString(4, enrollmentsDTO.getGrade());
            prepStatement.setInt(5, enrollmentsDTO.getNumericGrade());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Enrollment has been added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to edit existing enrollment details
    public  void editEnrollmentDAO(EnrollmentsDTO  enrollmentsDTO) {
        try {
            String query = "UPDATE Enrollments SET SubjectID=?,Grade=?,NumericGrade=? WHERE StudentID=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, enrollmentsDTO.getCourseID());
            prepStatement.setString(2, enrollmentsDTO.getGrade());
            prepStatement.setInt(3, enrollmentsDTO.getNumericGrade());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Enrollment details have been updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public void deleteEnrollmentDAO(String enrollmentCode) {
        try {
            String query = "DELETE FROM Enrollments WHERE EnrollmentID='" +enrollmentCode+ "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Student Enrollment removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT EnrollmentID, StudentID, SubjectID, Grade, NumericGrade FROM Enrollments";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    //-------------------------------------------------
    //TODO
    // Method to retrieve search data
    
    public ResultSet getSearchResult(String text) {
        try {
            String query = "SELECT EnrollmentID, StudentID, SubjectID, Grade, NumericGrade FROM Enrollments "
                    + "WHERE EnrollmentID LIKE '%" + text + "%' OR StudentID LIKE '%" + text + "%' OR "
                    + "SubjectID LIKE '%" + text + "%' OR Grade LIKE '%" + text + "%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }



    public ResultSet getStudentEnrollment(String studentCode) {
        try {
            String query = "SELECT * FROM Enrollments WHERE StudentID='" +studentCode+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    // Method to display data set in tabular form
    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int colCount = metaData.getColumnCount();

        for (int col=1; col <= colCount; col++){
            columnNames.add(metaData.getColumnName(col).toUpperCase(Locale.ROOT));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int col=1; col<=colCount; col++) {
                vector.add(resultSet.getObject(col));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
}
