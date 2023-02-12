package gov.iti.jets.interfaces;

import gov.iti.jets.dto.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface Server extends Remote {
    void sayHello() throws RemoteException;

    void register(Client client) throws RemoteException;

    void unregister(Client client) throws RemoteException;

    UserDto login(String phoneUmber, String password) throws RemoteException;

    public List<GroupDto> getGroups(String phoneNumber) throws RemoteException;

    void createGroup(String name, String currentUserNumber, List<String> listOfNumbers) throws RemoteException;

    List<String> getnameOfContacts(String currentUserNumber) throws RemoteException;

    void send(MessageDto message) throws RemoteException;

    void sendRequests(String senderPhoneNumber, List<String> listOfContacts) throws RemoteException;

    String chkNumberInDB(String currentUserNumber, String contactNumber) throws RemoteException;

    boolean isUserExistInDB(String contactNumber) throws RemoteException;

    boolean isContactExistInDB(String currentUserNumber, String contactNumber) throws RemoteException;

    boolean isRequestExistInDB(String currentUserNumber, String contactNumber) throws RemoteException;

    List<ContactDto> getUserContacts(String phone) throws RemoteException;

    void notifyUsersOnline(Client client) throws RemoteException;

    boolean editProfile(UserDto uDto) throws RemoteException;

    List<String> getNamesOfRequestSenders(String phone) throws RemoteException;

    void acceptContact(String currentUser, String friendNumber) throws RemoteException;

    void deleteRequest(String sender, String currentUser) throws RemoteException;

    public ArrayList<CountryDto> getCountriesNames() throws RemoteException;

   
    public  UserDtoSignup Signup(UserDtoSignup signupDto) throws RemoteException;

    boolean isUserOnline(ContactDto user) throws RemoteException;

    void msgSettings(ContactDto cDto) throws RemoteException;

}
