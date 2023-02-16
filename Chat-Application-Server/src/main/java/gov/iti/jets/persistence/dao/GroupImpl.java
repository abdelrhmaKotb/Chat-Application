package gov.iti.jets.persistence.dao;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.persistence.dao.interfaces.GroupDao;
import gov.iti.jets.persistence.entities.Group;
import gov.iti.jets.persistence.entities.GroupMembers;
import gov.iti.jets.persistence.utils.DBConnecttion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupImpl implements GroupDao {

    @Override
    public List<Group> getGroupById(List<Integer> groups_id) {
        List<Group> groups = new ArrayList<>();

        try (Connection con = DBConnecttion.getConnection()) {
            String query = "select * from group where group_id = ?";
            for (int i = 0; i < groups_id.size(); i++) {
                System.out.println(groups_id.get(i));
                System.out.println(groups_id.size());
                PreparedStatement statement = con
                        .prepareStatement("select * from chat_application.group where group_id = ?");
                //
                statement.setInt(1, groups_id.get(i));
                System.out.println(groups_id.get(i));
                System.out.println(groups_id.size());
                ResultSet rs = statement.executeQuery();
                rs.next();
                System.out.println(rs.getString(1));

                groups.add(new Group(groups_id.get(i), rs.getString(2), rs.getDate(3), rs.getString(4)));

                // rs.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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


    @Override
    public List<String> getGroupMember(int groupid) {
        try (Connection con = DBConnecttion.getConnection();) {
            System.out.println("group id " + groupid);
            PreparedStatement stmt = con.prepareStatement("""
                        select * from group_members
                    where  group_id  = ? """);
            stmt.setInt(1, groupid);

            ResultSet result = stmt.executeQuery();
            List<String> list = new ArrayList<>();

            while(result.next()) {
                list.add(result.getString("phone_number"));
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public int getNextGroupId() {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("select max(group_id) from chat_application.group");
            ResultSet result = stmt.executeQuery();
            result.next();
            System.out.println(result.getInt(1));
            return  result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
