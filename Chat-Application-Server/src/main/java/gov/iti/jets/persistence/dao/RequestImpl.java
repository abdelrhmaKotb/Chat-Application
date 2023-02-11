package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.dto.RequestDto;
import gov.iti.jets.persistence.dao.interfaces.ReguestDao;
import gov.iti.jets.persistence.entities.Request;
import gov.iti.jets.persistence.utils.DBConnecttion;

public class RequestImpl implements ReguestDao {

    @Override
    public int createRequests(Request request) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("INSERT INTO requests(sender,receiver,request_date) "
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
    public boolean isRequestExist(String currentUserNumber, String contactNumber) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con
                    .prepareStatement("select * from requests where (sender = ? and receiver = ?) "
                            + "or (sender = ? and receiver = ?)");
            stmt.setString(1, currentUserNumber);
            stmt.setString(2, contactNumber);
            stmt.setString(3, contactNumber);
            stmt.setString(4, currentUserNumber);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<RequestDto> getUserRequests(String receiver) {

        List<RequestDto> listOfContacts = new ArrayList<>();
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("select * from requests where receiver =  ?");
            stmt.setString(1, receiver);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                listOfContacts.add(new RequestDto(result.getString("sender"), result.getString("receiver"),
                        result.getDate("request_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfContacts;
    }

    @Override
    public int deleteRequest(Request request) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("DELETE from requests where sender = ? and receiver = ? ");
            stmt.setString(1, request.getSender());
            stmt.setString(2, request.getReceiver());
            int i = stmt.executeUpdate();
            if (i > 0) {
                System.out.println("Row is deleted");
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
