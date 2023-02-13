package gov.iti.jets.business.mapper;


/**
 * @author Esraa
 */


import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.persistence.entities.Contact;

public class ContactMapper implements Mapper<Contact, ContactDto> {


    @Override
    public ContactDto toDto(Contact contact) {
        return null;
    }

    @Override
    public Contact toEntity(ContactDto e) {
        return new Contact(e.getUser(), e.getFriendPhoneNumber(), e.getCategory(), e.getIsBlocked(), e.getFontSize(),
                e.getFontColor(), e.getFontStyle(), e.getBackgroundColor(), e.isBold(), e.isUnderlined(), e.isItalic());
    }
}
