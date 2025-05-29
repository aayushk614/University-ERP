package Database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {

    static final String driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/iiitddatabase";
    static String username;
    static String password;

    Properties prop;
    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public ConnectionFactory(){
        try {
            prop = new Properties();
            // Use relative path for better portability
            String configPath = System.getProperty("user.dir") + "/lib/DBCredentials.xml";
            prop.loadFromXML(new FileInputStream(configPath));
            //System.out.println("XML file loaded");
        } catch (IOException e) {
            System.err.println("Could not load database credentials. Please check: " + 
                             System.getProperty("user.dir") + "/lib/DBCredentials.xml");
            e.printStackTrace();
        }
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        //System.out.println("Username and passw loaded");

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
        } catch (ClassNotFoundException e){
            System.err.println("JDBC driver not found");
            e.printStackTrace();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected successfully.!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }



    //Login verification method - SECURE VERSION
    public boolean checkLogin(String username, String password, String userType){
        String query = "SELECT * FROM users WHERE username=? AND password=? AND usertype=? LIMIT 1";

        try {
            PreparedStatement prepStatement = conn.prepareStatement(query);
            prepStatement.setString(1, username);
            prepStatement.setString(2, password);
            prepStatement.setString(3, userType);
            ResultSet resultSet = prepStatement.executeQuery();
            if(resultSet.next()) return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
