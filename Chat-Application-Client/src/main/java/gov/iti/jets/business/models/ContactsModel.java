package gov.iti.jets.business.models;

import java.util.List;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactsModel {
    ObservableList<ContactDto> contacts;

    public ContactsModel()
    {
        try {
            List<ContactDto> userContacts =  RMIConnection.getServerServive().getUserContacts(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
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


}
