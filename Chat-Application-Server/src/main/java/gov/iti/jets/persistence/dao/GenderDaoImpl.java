package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.iti.jets.persistence.dao.interfaces.GenderDao;
import gov.iti.jets.persistence.utils.DBConnecttion;

public class GenderDaoImpl implements GenderDao{

    @Override
   
    public int seletcGender(String str) {
        int x = 0;
        Connection con = DBConnecttion.getConnection();

        String query = new String("select gender from user where gender=?");

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, str);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rs.getString("gender");
                x++;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return x;

    }

}