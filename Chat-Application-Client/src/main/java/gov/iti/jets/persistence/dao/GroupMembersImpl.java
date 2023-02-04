package gov.iti.jets.persistence.dao;

import gov.iti.jets.persistence.dao.interfaces.GroupMembersDao;
import gov.iti.jets.persistence.utils.DBConnecttion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMembersImpl implements GroupMembersDao{

    @Override
    public List<Integer> getGroupByUserPhoneNum(String phoneNumber) {
        List<Integer> groups_id=new ArrayList<>();
        Connection con= DBConnecttion.getConnection();
        String query="select group_id from group_members where phone_number=?;";
        try {
            PreparedStatement statement= con.prepareStatement(query);
            statement.setString(1, phoneNumber);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                groups_id.add(rs.getInt(1));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DBConnecttion.closeConnection(con);
        return groups_id;
    }
}
