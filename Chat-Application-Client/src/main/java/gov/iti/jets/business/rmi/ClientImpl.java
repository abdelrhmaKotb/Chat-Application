package gov.iti.jets.business.rmi;

import gov.iti.jets.interfaces.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client {
    public ClientImpl() throws RemoteException {
    }

    @Override
    public void helloBack() throws RemoteException {
        System.out.println("hello back");
    }
}
