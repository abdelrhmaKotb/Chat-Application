package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.enums.EnumsUtil;
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
    public List<ContactDto> getContactsForUser(String user) {
        List<ContactDto> listOfContacts = new ArrayList<>();
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement(
                    """
                            select c.`user` , c.friend_phone_number , u.name , u.email ,u.gender , u.status_id ,c.is_blocked,c.category_id,
                            c.FontSize, c.FontStyle, c.FontColor, c.BackgroundColor, c.isBold,
                            c.IsUnderlined, c.IsItalic 
                            from contacts c
                            join `user` u on u.phone_number  = c.friend_phone_number
                            where user =  ?
                                    """);
            stmt.setString(1, user);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                listOfContacts.add(new ContactDto(
                        result.getString("user"),
                        result.getString("friend_phone_number"),
                        result.getString("name"),
                        result.getString("email"),
                        EnumsUtil.fromOrdinalToGender(result.getInt("gender")),
                        EnumsUtil.fromOrdinalToStatus(result.getInt("status_id")),
                        result.getString("category_id"),
                        result.getBoolean("is_blocked"),result.getInt("fontSize"),
                        result.getString("fontStyle"), result.getString("fontColor"),
                        result.getString("backgroundColor"),result.getBoolean("isBold"),
                        result.getBoolean("isUnderlined"),result.getBoolean("isItalic")));
                System.out.println("contajh");
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
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("INSERT INTO contacts(user,friend_phone_number) "
                    + "VALUES(?, ?);");
            stmt.setString(1, contact.getUser());
            stmt.setString(2, contact.getFriendPhoneNumber());
            int i = stmt.executeUpdate();
            stmt.setString(1, contact.getFriendPhoneNumber());
            stmt.setString(2, contact.getUser());
            int j = stmt.executeUpdate();
            if (i > 0 && j > 0) {
                System.out.println("2 Rows is inserted");
                return i + j;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
