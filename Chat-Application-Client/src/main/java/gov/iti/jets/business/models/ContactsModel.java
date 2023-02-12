package gov.iti.jets.business.models;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ContactsModel {
    ObservableList<ContactDto> contacts;

    public ContactsModel() {
        try {
            List<ContactDto> userContacts = RMIConnection.getServerServive().getUserContacts(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
            System.out.println(userContacts.size());
            System.out.println(userContacts);
            contacts = FXCollections.observableArrayList(userContacts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<ContactDto> getContacts() {
        return contacts;
    }

    public ObservableList<String> getContactsPhoneNumber() {
        ObservableList<String> list = FXCollections.observableArrayList();
        contacts.forEach(e -> list.add(e.getFriendPhoneNumber()));
        return list;
    }

    public void setContacts(ContactDto contact) {
        contacts.add(contact);
    }

    public ContactDto getContactByPhoneNumber(String phone) {
        int size = contacts.size();
        for (int i = 0; i < size; i++) {
            if (contacts.get(i).getFriendPhoneNumber().equals(phone)) {
                return contacts.get(i);
            }
        }
        return null;
    }

    public void editContact(ContactDto contactDto) {
        int size = contacts.size();
        for (int i = 0; i < size; i++) {
            if (contacts.get(i).getFriendPhoneNumber().equals(contactDto.getFriendPhoneNumber())) {
                contacts.get(i).setBold(contactDto.isBold());
                contacts.get(i).setUnderlined(contactDto.isUnderlined());
                contacts.get(i).setItalic(contactDto.isItalic());
                contacts.get(i).setBackgroundColor(contactDto.getBackgroundColor());
                contacts.get(i).setFontColor(contactDto.getFontColor());
                contacts.get(i).setFontSize(contactDto.getFontSize());
                contacts.get(i).setFontStyle(contactDto.getFontStyle());
            }
        }
    }


}
