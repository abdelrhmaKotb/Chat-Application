package gov.iti.jets.persistence.dao;

import gov.iti.jets.persistence.dao.interfaces.GroupDao;
import gov.iti.jets.persistence.entities.Group;
import gov.iti.jets.persistence.utils.DBConnecttion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupImpl implements GroupDao {

    @Override
    public List<Group> getGroupById(List<Integer> groups_id) {
        List<Group> groups = new ArrayList<>();
        Connection con = DBConnecttion.getConnection();
        String query = "select * from group where group_id=?";
        try {
            for (int i = 0; i < groups_id.size(); i++) {
                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, groups_id.get(i));

                ResultSet rs = statement.executeQuery();
                groups.add(new Group(groups_id.get(i), rs.getString(2), rs.getDate(3), rs.getString(4)));

                rs.close();
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DBConnecttion.closeConnection(con);
        return groups;

    }

    @Override
    public int createGroup(Group group) {
        int generatedKey = 0;
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("INSERT INTO chat_application.group(name,created_at,admin) "
                    + "VALUES(?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, group.getName());
            stmt.setTimestamp(2, new java.sql.Timestamp(group.getDateOfCreation().getTime()));
            stmt.setString(3, group.getAdminPhoneNumber());
            int i = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            System.out.println("Inserted record's ID: " + generatedKey);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedKey;
    }
}