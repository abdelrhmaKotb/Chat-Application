package gov.iti.jets.business.mapper;

/**
 *
 * @author Esraa
 */

import gov.iti.jets.business.dto.ContactsDto;
import gov.iti.jets.persistence.entities.Contacts;

public class ContactsMapper implements Mapper<Contacts, ContactsDto> {

    @Override
    public ContactsDto toDto(Contacts entity) {

        return new ContactsDto(entity.getUser(), entity.getFriendPhoneNumber(), entity.getCategory(),
                entity.getIsBlocked());
    }
}
