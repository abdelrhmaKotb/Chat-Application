package gov.iti.jets.business.rmi;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.interfaces.Client;

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
    }
}
