
package DAO;


import DTO.RegistrationDTO;
import DTO.StudentDTO;
import Database.ConnectionFactory;

import javax.swing.*;
import java.sql.*;

public class RegistrationDAO extends DisplayTableDAO {

    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public RegistrationDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void displayRegistrationTable(){

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Registration");
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
        private int RegistrationID;
        private String studentID;
        private String CourseID;
    */
    // Methods to add new student
    public void addRegistrationsDAO(RegistrationDTO registrationDTO) {
        try {
            String query = "SELECT * FROM Registration WHERE StudentID='"
                    +registrationDTO.getStudentID()
                    + "' AND SubjectID='"
                    +registrationDTO.getCourseID()
                    + "'";
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                JOptionPane.showMessageDialog(null, "Registration already exists.");
            else
                addFunction(registrationDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addFunction(RegistrationDTO registrationDTO) {
        try {
            String query = "INSERT INTO Registration VALUES(?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, registrationDTO.getRegistrationID());
            prepStatement.setString(2, registrationDTO.getStudentID());
            prepStatement.setString(3, registrationDTO.getCourseID());
            prepStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Registration has been added.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void editRegistrationDAO(RegistrationDTO registrationDTO) {
        try {
                String query = "UPDATE Registration SET RegistrationID = ?, SubjectID=? WHERE StudentID=?";
                prepStatement = conn.prepareStatement(query);
                prepStatement.setInt(1, registrationDTO.getRegistrationID());
                prepStatement.setString(3, registrationDTO.getStudentID());
                prepStatement.setString(2, registrationDTO.getCourseID());
                prepStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student Registration details have been updated.");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }


    // Method to delete existing student Registration
    public void deleteRegistrationDAO(String registrationCode) {
        try {
            String query = "DELETE FROM Registration WHERE RegistrationID='" +registrationCode+ "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Student Registration removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve data set to be displayed
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT RegistrationID,StudentID,SubjectID FROM Registration";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    //-------------------------------------------------
    //TODO
    // Method to retrieve search data


    public ResultSet getStudentRegistration(String studentCode) {
        try {
            String query = "SELECT * FROM Registration WHERE StudentID='" +studentCode+ "'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
