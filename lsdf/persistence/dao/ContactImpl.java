package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.dao.interfaces.ContactDao;
import gov.iti.jets.persistence.entities.Contact;
import gov.iti.jets.persistence.utils.DBConnecttion;

/**
 *
 * @author Esraa
 */
public class ContactImpl implements ContactDao {

    /**
     * This method is used to get contacts get contact by phone number return
     * null if contact dose not exist
     */
    @Override
    public List<Contact> getContactsForUser(String user) {
        List<Contact> listOfContacts = new ArrayList<>();
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("select * from contacts where user =  ?");
            stmt.setString(1, user);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                listOfContacts.add(new Contact(result.getString("user"), result.getString("friend_phone_number"),
                        result.getString("category_id"), result.getBoolean("is_blocked")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfContacts;
    }

    @Override
    public boolean isContactExist(String currentUserNumber, String contactNumber) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con
                    .prepareStatement("select * from contacts where (user = ? and friend_phone_number = ?) "
                            + "or (user = ? and friend_phone_number = ?)");
            stmt.setString(1, currentUserNumber);
            stmt.setString(2, contactNumber);
            stmt.setString(3, contactNumber);
            stmt.setString(4, currentUserNumber);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int create(Contact contact) {
        return 0;
    }

}
