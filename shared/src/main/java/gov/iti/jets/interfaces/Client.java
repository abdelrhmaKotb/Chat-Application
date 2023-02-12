package gov.iti.jets.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.MessageDto;

public interface Client extends Remote{
    void helloBack() throws RemoteException;
    String getPhoneNumber() throws RemoteException;
    void reciveMessage(MessageDto Message) throws RemoteException;
    void userOnlineNotify(ContactDto contact) throws RemoteException;
    void userOfflineNotify(ContactDto contact) throws RemoteException;
}
