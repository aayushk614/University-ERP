/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.StudentDTO;
import Database.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

// Data Access Object for Student
public class StudentDAO {

    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public StudentDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTable(String tableName){
        try {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+tableName);
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){

                String id = resultSet.getString("studentID");
                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                System.out.println("ID : "+ id + "|| Name : "+ name + "|| Email : "+ email);

            }

        } catch (SQLException e){
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

            //System.out.println(resultSet.);
            //String id = resultSet.getString("cid");
            //String name = resultSet.getString("fullname");
            //System.out.println("ID is : " +id + " Name = "+name);
            // Process retrieved data
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            //throw new RuntimeException(ex);
        }
    }


    public void displayStudentTable(){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
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



    // Methods to add new student
    public void addStudentDAO(StudentDTO studentDTO) {
        try {
            String query = "SELECT * FROM student WHERE studentID='"
                    +studentDTO.getStudentID()
                    + "' AND Name='"
                    +studentDTO.getName()
                    + "' AND Email='"
                    +studentDTO.getEmail()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Student already exists.");
            else
                addFunction(studentDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFunction(StudentDTO studentDTO) {
        try {
            String query = "INSERT INTO student VALUES(?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, studentDTO.getStudentID());
            prepStatement.setString(2, studentDTO.getName());
            prepStatement.setString(3, studentDTO.getEmail());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Student has been added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to edit existing student details
    public  void editStudentDAO(StudentDTO studentDTO) {
        try {
            String query = "UPDATE student SET Name=?,Email=? WHERE studentID=?";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, studentDTO.getName());
            prepStatement.setString(2, studentDTO.getEmail());
            prepStatement.setString(3, studentDTO.getStudentID());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student details have been updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete existing student
    public void deleteStudentDAO(String studentCode) {
        try {
            String query = "DELETE FROM student WHERE studentID='" +studentCode+ "'";

            String query1 = "DELETE FROM Enrollments WHERE StudentID='" +studentCode+ "'";


            statement.executeUpdate(query1);
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Student removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT * FROM student";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
//-------------------------------------------------
    //TODO
    // Method to retrieve search data
    public ResultSet getStudentSearch(String text) {
        try {
            String query = "SELECT * FROM student " +
                    "WHERE StudentID LIKE '%"+text+"%' OR Name LIKE '%"+text+"%' OR " +
                    "Email LIKE '%"+text+"%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }



    public ResultSet getStudName(String studCode) {
        try {
            String query = "SELECT * FROM student WHERE studentID='" +studCode+ "'";
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
