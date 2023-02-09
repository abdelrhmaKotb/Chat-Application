package gov.iti.jets.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gov.iti.jets.dto.UserDto;

public interface Server  extends Remote{
    void sayHello() throws RemoteException;
    void register(Client client) throws RemoteException;
    void unregister(Client client) throws RemoteException;
    UserDto login(String phoneUmber, String password) throws RemoteException;
    UserDto Signup() throws RemoteException;
}
