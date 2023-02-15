package gov.iti.jets.persistence.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class DBConnecttion {

    static Properties prop = new Properties();
    

    public static Connection getConnection() {
        Connection con = null;
        // DBConnecttion dbConnecttion = new DBConnecttion();
        // dbConnecttion.loadProp();
        try {
            // con = DriverManager.getConnection(prop.getProperty("MYSQL_DB_URL"), prop.getProperty("MYSQL_DB_USERNAME"),
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application", "root","root");
                    System.out.println("connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProp() {
        URL resource = getClass().getClassLoader().getResource("database/db.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(resource.toURI()));
            prop.load(fis);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
