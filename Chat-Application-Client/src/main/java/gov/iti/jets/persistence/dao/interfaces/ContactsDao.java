package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.persistence.entities.Contacts;
/**
 *
 * @author Esraa
 */
public interface ContactsDao {
    int create(Contacts contact);
    Contacts getContact(String user);
}
