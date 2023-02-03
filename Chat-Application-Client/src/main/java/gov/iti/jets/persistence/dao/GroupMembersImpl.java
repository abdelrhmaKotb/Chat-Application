package gov.iti.jets.persistence.dao;

import gov.iti.jets.persistence.dao.interfaces.GroupMembersDao;
import gov.iti.jets.persistence.entities.GroupMembers;
import gov.iti.jets.persistence.utils.DBConnecttion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMembersImpl implements GroupMembersDao {

    @Override
    public List<Integer> getGroupByUserPhoneNum(String phoneNumber) {
        List<Integer> groups_id = new ArrayList<>();
        Connection con = DBConnecttion.getConnection();
        String query = "select group_id from group_members where phone_number=?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
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

    @Override
    public int addMember(GroupMembers member) {
        try (Connection con = DBConnecttion.getConnection();) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO group_members(group_id,phone_number,join_date) "
                    + "VALUES(?, ?,?);");
            stmt.setInt(1, member.getGroup_id());
            stmt.setString(2, member.getMemberPhoneNumber());
            stmt.setTimestamp(3, new java.sql.Timestamp(member.getJoin_date().getTime()));
            int i = stmt.executeUpdate();
            if (i > 0) {
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
