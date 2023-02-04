package gov.iti.jets.persistence.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.business.services.PasswordHashing;
import gov.iti.jets.persistence.dao.interfaces.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.utils.DBConnecttion;

public class UserImpl implements UserDao {

    @Override
    public int insertUser(User user) {

        Connection con = DBConnecttion.getConnection();
        User tempUser = seletcByPhoneNumber(user.getPhoneNumber());
        if (tempUser != null) {
            return 0;
        }
        System.out.println("after check");
        System.out.println(user.getDateOfBirth());
        int rowInserted = 0;
        String query = new String(
                "insert into user(phone_number,name,email,password,bio,status_id,is_deleted,is_admin,gender,date_of_birth,profile_image,country_id) values(?,?,?,?,?,?,?,?,?,?,?,?)");

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, user.getPhoneNumber());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, PasswordHashing.doHahing(user.getPassword()));
            stmt.setString(5, user.getBio());
            // stmt.setString(6, user.getStatus());
            stmt.setString(6, "1");
            stmt.setBoolean(7, user.isDeleted());
            stmt.setBoolean(8, user.isAdmin());
            stmt.setString(9, user.getGender());
            stmt.setDate(10, user.getDateOfBirth());
            stmt.setInt(12, user.getCountry());
            try {
                FileInputStream fin = new FileInputStream(user.getFile());
                stmt.setBinaryStream(11, fin, (int) user.getFile().length());

            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
                user.setGender(rs.getString("gender"));
                user.setCountry(rs.getInt("country_id"));
                user.setDateOfBirth(rs.getDate("date_of_birth"));
                user.setBio(rs.getString("bio"));
                user.setAdmin(rs.getBoolean("is_admin"));
                user.setDeleted(rs.getBoolean("is_deleted"));
                user.setStatus(rs.getString("status_id"));

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

    /**
     * method to use in login
     * get uer by phone number and password
     * return null if user dose not exist or user that match
     */
    @Override
    public User getUser(String phoneNumber, String password) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE phone_number = ? and password = ?");
            stm.setString(1, phoneNumber);
            stm.setString(2, PasswordHashing.doHahing(password)); // rember to use hash this password after registration
            // fineshed
            ResultSet result = stm.executeQuery();
            File file = new File("profile_image");
            if (result.next()) {

                return new User(
                        result.getString("phone_number"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("gender"),
                        result.getInt("country_id"),
                        result.getDate("date_of_birth"),
                        result.getString("bio"),
                        result.getBoolean("is_admin"),
                        result.getBoolean("is_deleted"),
                        result.getString("status_id"),
                        file

                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isPhoneNumberExist(String phoneNumber) {
        try (Connection con = DBConnecttion.getConnection();) {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE phone_number = ?");
            stm.setString(1, phoneNumber);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void updateUser(User newData) {
        try (Connection con = DBConnecttion.getConnection()) {
            String query = "update user set name=?, email=?,  country_id=?, date_of_birth=?,bio=?,  where phone_number=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, newData.getName());
            statement.setString(2, newData.getEmail());
            statement.setInt(3, newData.getCountry());
            statement.setDate(4, newData.getDateOfBirth());
            statement.setString(5, newData.getBio());
            statement.setString(6, newData.getStatus());
            statement.setString(7, newData.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getUsersByNumbers(List<String> listOfNumbers) {
        List<User> listOfUsers = new ArrayList<>();
        try (Connection con = DBConnecttion.getConnection();) {
            String numbers = "";
            for (String num : listOfNumbers) {
                numbers += num + ",";
            }
            numbers = numbers.substring(0, numbers.length() - 1);
            PreparedStatement stm = con.prepareStatement("SELECT * FROM user WHERE phone_number in (" + numbers + ")");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                listOfUsers.add(new User(
                        result.getString("phone_number"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("gender"),
                        result.getInt("country_id"),
                        result.getDate("date_of_birth"),
                        result.getString("bio"),
                        result.getBoolean("is_admin"),
                        result.getBoolean("is_deleted"),
                        result.getString("status_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

}
