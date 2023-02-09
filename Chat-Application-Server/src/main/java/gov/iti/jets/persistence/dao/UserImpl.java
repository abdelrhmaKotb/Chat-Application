package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.iti.jets.enums.EnumsUtil;
import gov.iti.jets.persistence.dao.interfaces.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.utils.DBConnecttion;
import gov.iti.jets.persistence.utils.ImageConvertor;
import gov.iti.jets.persistence.utils.PasswordHashing;

public class UserImpl implements UserDao {

    @Override
    public int createUser(User user) {

        Connection con = DBConnecttion.getConnection();
        User tempUser = seletcByPhoneNumber(user.getPhoneNumber());
        if (tempUser != null) {
            return 0;
        }

        int rowInserted = 0;
        String query = new String(
                "insert into user(phone_number,name,email,password,bio,status_id,is_deleted,is_admin,gender,date_of_birth,profile_image,country_id) values(?,?,?,?,?,?,?,?,?,?,?,?)");

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, user.getPhoneNumber());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, PasswordHashing.doHahing(user.getPassword()));
            stmt.setString(5, user.getBio());
            stmt.setInt(6, user.getStatus().ordinal());
            stmt.setString(6, "1");
            stmt.setBoolean(7, user.isDeleted());
            stmt.setBoolean(8, user.isAdmin());
            stmt.setInt(9, user.getGender().ordinal());
            stmt.setDate(10, user.getDateOfBirth());
            stmt.setInt(12, user.getCountry());

            rowInserted = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rowInserted;

    }

    public User seletcByPhoneNumber(String phoneNum) {

        Connection con = DBConnecttion.getConnection();
        User user = null;
        if (con == null)
            return null;

        String query = new String("select * from user where phone_number=?");

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, phoneNum);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(EnumsUtil.fromOrdinalToGender(rs.getInt("gender")));
                user.setCountry(rs.getInt("country_id"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setBio(rs.getString("bio"));
                user.setAdmin(rs.getBoolean("is_admin"));
                user.setDeleted(rs.getBoolean("is_deleted"));
                user.setStatus(EnumsUtil.fromOrdinalToStatus(rs.getInt("status_id")));
                user.setImage(ImageConvertor.BlobToBytes(rs.getBlob("profile_image")));

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
        return user;

    }

    @Override
    public User getUser(String phoneNumber, String password) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stm = con.prepareStatement(
                    "SELECT user.* , countries.name country_name FROM user WHERE phone_number = ? and password = ?");
            stm.setString(1, phoneNumber);
            stm.setString(2, password); // rember to use hash this password after registration fineshed
            ResultSet result = stm.executeQuery();

            if (result.next()) {
                return new User(
                        result.getString("phone_number"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        EnumsUtil.fromOrdinalToGender(result.getInt("gender")),
                        result.getInt("country_id"),
                        result.getDate("date_of_birth"),
                        result.getString("bio"),
                        result.getBoolean("is_admin"),
                        result.getBoolean("is_deleted"),
                        EnumsUtil.fromOrdinalToStatus(result.getInt("status_id")),
                        ImageConvertor.BlobToBytes(result.getBlob("profile_image")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
