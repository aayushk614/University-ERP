package DAO;

import Database.ConnectionFactory;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

public class UniversityGAV {
    Connection conn = null;
    PreparedStatement prepStatement= null;
    Statement statement = null;
    ResultSet resultSet = null;

    public UniversityGAV() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayGAV1(){
        try {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM universitygav");
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

    public void displayGAV(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT studentID," +
                    "Name,Email FROM universitygav");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                // Iterate through the result set and print all column values of each row
                // Iterate through columns and print values
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);

                    System.out.println(columnName + ": " + value);
                }
                System.out.println("-----"); // Separate rows for clarity
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public void displayCGPAWithGAV(){
        try {

            updateGrades();

            ResultSet resultSet = statement.executeQuery("SELECT s.*, (SELECT CGPA FROM Grades g WHERE g.StudentID = s.studentID) AS 'CGPA' FROM universitygav s " +
                    "WHERE s.studentID IN (SELECT Grades.StudentID FROM Grades)");

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

    public void updateGrades(){

        /*INSERT INTO Grades(StudentID, CGPA)
SELECT StudentID, (SUM(NumericGrade)*4)/(COUNT(*)*4) AS CGPA
FROM Enrollments
GROUP BY StudentID
ON DUPLICATE KEY UPDATE CGPA = VALUES(CGPA)*/

        try {

            String query = "INSERT INTO Grades(StudentID, CGPA) SELECT StudentID, SUM(NumericGrade)/COUNT(*) AS CGPA FROM Enrollments " +
                    "GROUP BY StudentID ON DUPLICATE KEY UPDATE CGPA = VALUES(CGPA)";

            int colsaffected = statement.executeUpdate(query);
            //System.out.println("No of queries updated = " + colsaffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void createGAV(){
        try{
            String query = "CREATE OR REPLACE VIEW universityGAV AS SELECT * FROM student UNION SELECT * FROM studentBtech UNION SELECT * FROM studentPhD";
            int cols = statement.executeUpdate(query);
            //System.out.println("University GAV Updated with cols : "+ cols);

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public ResultSet getQueryResult() {
        try {

            updateGrades();

            resultSet = statement.executeQuery("SELECT s.*, (SELECT CGPA FROM Grades g WHERE g.StudentID = s.studentID) AS 'CGPA' FROM universitygav s " +
                    "WHERE s.studentID IN (SELECT Grades.StudentID FROM Grades)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }



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



    public static void main(String[] args) {
        UniversityGAV obj = new UniversityGAV();
        //obj.displayGAV();
        obj.createGAV();

        //obj.displayCGPAWithGAV();
    }



}
