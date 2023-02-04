package gov.iti.jets.presentation.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactsModel {
    // i think shoulde be list of contacts not only string later 
    ObservableList<String> contacts;

    public ContactsModel()
    {
        contacts = FXCollections.observableArrayList("ahned mohamed","yarek fawaz","koko momo");
    }


    public ObservableList<String> getContacts() {
        return contacts;
    }

    public void setContacts(String contact) {
        contacts.add(contact);
    }


}
