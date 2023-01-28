package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import gov.iti.jets.persistence.dao.interfaces.ReguestDao;
import gov.iti.jets.persistence.entities.Request;
import gov.iti.jets.persistence.utils.DBConnecttion;

public class RequestImpl implements ReguestDao {

    @Override
    public int createRequests(Request request) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("INSERT INTO requests(sender,reciver,request_date) "
                    + "VALUES(?, ?,?);");
            stmt.setString(1, request.getSender());
            stmt.setString(2, request.getReceiver());
            stmt.setTimestamp(3, new java.sql.Timestamp(request.getRequestDate().getTime()));
            int i = stmt.executeUpdate();
            if (i > 0) {
                System.out.println("Row is inserted");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Request getRequests(String sender, String receiver) {

        return null;
    }
}
