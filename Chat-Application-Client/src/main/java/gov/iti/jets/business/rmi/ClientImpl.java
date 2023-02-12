package gov.iti.jets.business.rmi;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.helper.NavCoordinator;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.presentation.controllers.MessageController;
import gov.iti.jets.presentation.controllers.NotificationController;
import gov.iti.jets.presentation.utils.ShowPopUp;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client {
    static CurrentUserModel currentUser = ModelsFactory.getInstance().getCurrentUserModel();

    public ClientImpl() throws RemoteException {
    }

    @Override
    public void helloBack() throws RemoteException {
        System.out.println("hello back");
    }

    @Override
    public String getPhoneNumber() throws RemoteException {
        System.out.println(currentUser.getPhoneNumber());
        return currentUser.getPhoneNumber();
    }

    @Override
    public void reciveMessage(MessageDto Message) throws RemoteException {
        System.out.println(Message);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // MessageController.messageController.recive(Message);
                // String r = Message.getReciver();
                ChatCoordinator.getInstance().getCurrentChatController().recive(Message);
            }
        });
    }

    @Override
    public void userOnlineNotify(ContactDto contact) throws RemoteException {
        // System.out.println(contact.getUser() + " became online");
        ShowPopUp showPopUp = new ShowPopUp();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showPopUp.showNotifacation(contact.getUser() + " became online");
                NavCoordinator.getNotificationController().addInListOfNotifications(contact.getUser() + " became online");
                ContactsModel contactsModel = ModelsFactory.getInstance().getContactsModel();
                ContactDto c = new ContactDto();
                contactsModel.getContacts().add(c);
                contactsModel.getContacts().remove(c);

                // var contacts = contactsModel.getContacts();
                // contacts.
                // contacts.get(contacts.indexOf(contact)).setCategory("1");;
            }
        });
    }

    @Override
    public void userOfflineNotify(ContactDto contact) throws RemoteException {
        // System.out.println(contact.getUser() + " became online");
        ShowPopUp showPopUp = new ShowPopUp();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showPopUp.showNotifacation(contact.getUser() + " became offline");
                NavCoordinator.getNotificationController().addInListOfNotifications(contact.getUser() + " became offline");
                ContactsModel contactsModel = ModelsFactory.getInstance().getContactsModel();
                ContactDto c = new ContactDto();
                
                contactsModel.getContacts().add(c);
                contactsModel.getContacts().remove(c);

                

                // var contacts = contactsModel.getContacts();
                // contacts.
                // contacts.get(contacts.indexOf(contact)).setCategory("1");;
            }
        });
    }
}
