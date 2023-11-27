package DAO;

import DTO.SubjectDTO;
import Database.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

public class SubjectDAO {

    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public SubjectDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void displaytableJoin(){
//
//        try {
//            ResultSet resultSet = statement.executeQuery("SELECT s.*, e.* FROM student s JOIN Enrollments e ON s.studentID = e.StudentID");
//
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            while (resultSet.next()) {
//
//
//                // Iterate through the result set and print all column values of each row
//                // Iterate through columns and print values
//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = metaData.getColumnName(i);
//                    Object value = resultSet.getObject(i);
//                    // Print column name and value
//                    System.out.println(columnName + ": " + value);
//                }
//                System.out.println("-----"); // Separate rows for clarity
//            }
//
//            //System.out.println(resultSet.);
//            //String id = resultSet.getString("cid");
//            //String name = resultSet.getString("fullname");
//            //System.out.println("ID is : " +id + " Name = "+name);
//            // Process retrieved data
//        }
//        catch (SQLException ex) {
//            ex.printStackTrace();
//            //throw new RuntimeException(ex);
//        }
//    }

    public void displaySubjectTable(){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM subject");
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


    // Methods to add new faculty
    public void addSubjectDAO(SubjectDTO subjectDTO) {
        try {
            String query = "SELECT * FROM subject WHERE CourseCode='"
                    +subjectDTO.getCourseCode()
                    + "' AND CourseName='"
                    +subjectDTO.getCourseName()
                    + "' AND Abbreviation='"
                    +subjectDTO.getAbbreviation()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Subject already exists.");
            else 
            {
                // Check if the professor exists for the given course code
            String selectQuery = "SELECT * FROM faculty WHERE faculty_name=?";
            prepStatement = conn.prepareStatement(selectQuery);
            prepStatement.setString(1, subjectDTO.getInstructor());
            ResultSet resultSet = prepStatement.executeQuery();
            if (resultSet.next())
            {
                JOptionPane.showMessageDialog(null, "No such Prof");
            }
            else
            {
                addFunction(subjectDTO);
            }
                
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFunction(SubjectDTO subjectDTO) {
        try {
            String query = "INSERT INTO subject VALUES(?,?,?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, subjectDTO.getDepartmentCode());
            prepStatement.setString(2, subjectDTO.getCourseCode());
            prepStatement.setString(3, subjectDTO.getCourseName());
            prepStatement.setString(4, subjectDTO.getAbbreviation());
            prepStatement.setString(5, subjectDTO.getInstructor());
            prepStatement.setString(6, subjectDTO.getStudentLevel());
            prepStatement.setInt(7, subjectDTO.getCredits());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Subject has been added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Method to edit existing subject details
        /*
        private String DepartmentCode;
        private String CourseCode;
        private String CourseName;
        private String Abbreviation;
        private String Instructor;
        private String StudentLevel;
        private int Credits;
    */
    public  void editSubjectDAO(SubjectDTO subjectDTO) {
        try {
            // Check if the professor exists for the given course code
            String selectQuery = "SELECT * FROM faculty WHERE faculty_name=?";
            prepStatement = conn.prepareStatement(selectQuery);
            prepStatement.setString(1, subjectDTO.getInstructor());
            ResultSet resultSet = prepStatement.executeQuery();

            if (!resultSet.next()) {
                // Professor exists, proceed with the update
                String updateQuery = "UPDATE subject SET DepartmentCode=?,CourseName=?,Abbreviation=?,Instructor=?,StudentLevel=?,Credits=? WHERE CourseCode=?";
                prepStatement = conn.prepareStatement(updateQuery);
                prepStatement.setString(1, subjectDTO.getDepartmentCode());
                prepStatement.setString(2, subjectDTO.getCourseName());
                prepStatement.setString(3, subjectDTO.getAbbreviation());
                prepStatement.setString(4, subjectDTO.getInstructor());
                prepStatement.setString(5, subjectDTO.getStudentLevel());
                prepStatement.setInt(6, subjectDTO.getCredits());
                prepStatement.setString(7, subjectDTO.getCourseCode());
                prepStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Subject details have been updated.");
            } else {
                // Professor does not exist for the given course code
                JOptionPane.showMessageDialog(null, "Professor does not exist for the given course code.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete existing subject
   // Method to delete existing subject
    public void deleteSubjectDAO(String subjectCode) {
        try {
            
            String query = "DELETE FROM subject WHERE CourseCode='" +subjectCode+ "'";
            String query1 = "DELETE FROM Enrollments WHERE CourseID='" +subjectCode+ "'";

            statement.executeUpdate(query);
            statement.executeUpdate(query1);

            JOptionPane.showMessageDialog(null, "Subject removed.!!!");
            JOptionPane.showMessageDialog(null, "Corresponding Enrollment removed.!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT * FROM subject";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Method to retrieve search data
    public ResultSet getSubjectSearch(String text) {
        try {
            String query = "SELECT DepartmentCode, CourseCode, CourseName, Abbreviation, Instructor, StudentLevel, Credits FROM subject " +
                    "WHERE DepartmentCode LIKE '%"+text+"%' OR CourseCode LIKE '%"+text+"%' OR " +
                    "Abbreviation LIKE '%"+text+"%' OR Instructor LIKE '%"+text+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public ResultSet getSubjectName(String subjectCode) {
        try {
            String query = "SELECT * FROM subject WHERE CourseCode='" +subjectCode+ "'";
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
