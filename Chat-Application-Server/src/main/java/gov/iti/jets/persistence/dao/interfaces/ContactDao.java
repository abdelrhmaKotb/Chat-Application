package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.persistence.entities.Contact;

import java.util.List;

/**
 * @author Esraa
 */
public interface ContactDao {
    int create(Contact contact);

    List<ContactDto> getContactsForUser(String user);

    boolean isContactExist(String currentUserNumber, String contactNumber);

    void updateMsgSettings(Contact contact);
}
