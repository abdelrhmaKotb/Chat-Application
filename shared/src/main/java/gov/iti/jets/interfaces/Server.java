package gov.iti.jets.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.dto.UserDtoSignup;

public interface Server extends Remote {
    void sayHello() throws RemoteException;

    void register(Client client) throws RemoteException;

    void unregister(Client client) throws RemoteException;

    UserDto login(String phoneUmber, String password) throws RemoteException;

    public List<GroupDto> getGroups(String phoneNumber) throws RemoteException;

    void send(MessageDto message) throws RemoteException;

    UserDtoSignup Signup(UserDtoSignup signupDto) throws RemoteException;
}
