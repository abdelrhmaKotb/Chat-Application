package gov.iti.jets.persistence.utils;

import java.sql.*;

public class DBConnecttion {

    
    // static {

    //     try {
    //         DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }

    // }

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_application", "root", "1234");
            //System.out.println("conected");
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
}
