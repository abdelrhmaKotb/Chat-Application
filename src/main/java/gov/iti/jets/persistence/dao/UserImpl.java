package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.iti.jets.persistence.dao.interfaces.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.utils.DBConnecttion;

public class UserImpl implements UserDao {
    @Override
    public int create(User user) {
        return 0;
    }

   /**
    * method to use in login
    * get uer by phone number and password 
    * return null if user dose not exist or user that match
    */
    @Override
    public User getUser(String phoneNumber, String password) {
        try (Connection con = DBConnecttion.getConnection();){
            
            PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE phone_number = ? and password = ?");
            stm.setString(1, phoneNumber);
            stm.setString(2, password); // rember to use hash this password after registration fineshed 
            ResultSet result = stm.executeQuery();

            if(result.next())
            {
                return new User(
                result.getString("phone_number"),
                result.getString("name"),
                result.getString("email"),
                result.getString("password"),
                result.getString("gender"),
                result.getString("country_id"),
                result.getDate("date_of_birth"),
                result.getString("bio"),
                result.getBoolean("is_admin"),
                result.getBoolean("is_deleted"),
                result.getString("status_id")
                );
            }

            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
