package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gov.iti.jets.persistence.dao.interfaces.ContactsDao;
import gov.iti.jets.persistence.entities.Contacts;
import gov.iti.jets.persistence.utils.DBConnecttion;

/**
 *
 * @author Esraa
 */
public class ContactsImpl implements ContactsDao {

    /**
     * This method is used to get contacts get contact by phone number return
     * null if contact dose not exist
     */
    @Override
    public Contacts getContact(String user) {
        try ( Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("select * from contacts where user =  ?");
            stmt.setString(1, user);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new Contacts(result.getString("user"), result.getString("friend_phone_number"),
                        result.getString("category_id"), result.getBoolean("is_blocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int create(Contacts contact) {
        try ( Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("INSERT INTO contacts (user,friend_phone_number,category_id,is_blocked"
                    + "VALUES(?,?,?,?)");
            stmt.setString(1, contact.getUser());
            stmt.setString(2, contact.getFriendPhoneNumber());
            stmt.setInt(3, 1);
            stmt.setBoolean(4, contact.getIsBlocked());
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
}
