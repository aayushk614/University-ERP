package DAO;


import DTO.FacultyDTO;
import Database.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

public class FacultyDAO {

    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public FacultyDAO() {
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

    public void displayFacultyTable(){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM faculty");
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
    public void addFacultyDAO(FacultyDTO facultyDTO) {
        try {
            String query = "SELECT * FROM faculty WHERE faculty_id='"
                    +facultyDTO.getFaculty_id()
                    + "' AND faculty_name='"
                    +facultyDTO.getFaculty_name()
                    + "' AND email='"
                    +facultyDTO.getEmail()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Faculty already exists.");
            else
                addFunction(facultyDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFunction(FacultyDTO facultyDTO) {
        try {
            String query = "INSERT INTO faculty VALUES(?,?,?,?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, facultyDTO.getFaculty_id());
            prepStatement.setString(2, facultyDTO.getFaculty_name());
            prepStatement.setString(3, facultyDTO.getEmail());
            prepStatement.setInt(4, facultyDTO.getAge());
            prepStatement.setString(5, facultyDTO.getPosition());
            prepStatement.setString(6, facultyDTO.getGender());
            prepStatement.setString(7, facultyDTO.getEmployment_type());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Faculty has been added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Method to edit existing faculty details
    public  void editFacultyDAO(FacultyDTO facultyDTO) {
        try {
            String query = "UPDATE faculty SET faculty_name=?,email=?,age=?,position=?,gender=?,employment_type=? WHERE faculty_id=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, facultyDTO.getFaculty_name());
            prepStatement.setString(2, facultyDTO.getEmail());
            prepStatement.setInt(3, facultyDTO.getAge());
            prepStatement.setString(4, facultyDTO.getPosition());
            prepStatement.setString(5, facultyDTO.getGender());
            prepStatement.setString(6, facultyDTO.getEmployment_type());
            prepStatement.setInt(7, facultyDTO.getFaculty_id());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Faculty details have been updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete existing faculty
    public void deleteFacultyDAO(int facultyCode) {
        try {
            String query = "DELETE FROM faculty WHERE faculty_id='" +facultyCode+ "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Faculty removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT * FROM faculty";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Method to retrieve search data
    public ResultSet getSearchResult(String text) {
        try {
            String query = "SELECT faculty_id, faculty_name, email, age, position, gender, employment_type FROM faculty " +
                    "WHERE faculty_id LIKE '%"+text+"%' OR faculty.faculty_name LIKE '%"+text+"%' OR " +
                    "faculty.email LIKE '%"+text+"%' OR faculty.position LIKE '%"+text+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }



    public ResultSet getFacultyName(String facultyName) {
        try {
            String query = "SELECT * FROM faculty WHERE faculty_name='" +facultyName+ "'";
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


