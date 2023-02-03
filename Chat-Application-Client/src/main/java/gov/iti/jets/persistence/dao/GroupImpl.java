package gov.iti.jets.persistence.dao;

import gov.iti.jets.persistence.dao.interfaces.GroupDao;
import gov.iti.jets.persistence.entities.Group;
import gov.iti.jets.persistence.utils.DBConnecttion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupImpl implements GroupDao {

    @Override
    public List<Group> getGroupById(List<Integer> groups_id) {
        List<Group> groups=new ArrayList<>();
        Connection con= DBConnecttion.getConnection();
        String query="select * from group where group_id=?";
        try {
            for(int i=0;i<groups_id.size();i++) {
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
}
