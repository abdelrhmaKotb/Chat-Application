package gov.iti.jets.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server  extends Remote{
    void sayHello() throws RemoteException;
    void register(Client client) throws RemoteException;
    void unregister(Client client) throws RemoteException;
}
