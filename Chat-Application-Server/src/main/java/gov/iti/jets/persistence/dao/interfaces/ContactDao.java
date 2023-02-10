package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.persistence.entities.Contact;
/**
 *
 * @author Esraa
 */
public interface ContactDao {
    int create(Contact contact);
    List<Contact> getContactsForUser(String user);
    boolean isContactExist(String currentUserNumber,String contactNumber);
}
