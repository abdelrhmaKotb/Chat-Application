package gov.iti.jets.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote{
    void helloBack() throws RemoteException;
}
