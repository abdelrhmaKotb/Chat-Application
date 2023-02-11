package gov.iti.jets.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.dto.RequestDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.dto.UserDtoSignup;

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

    public ArrayList<CountryDto> getCountriesNames() throws RemoteException;
   
    public  UserDtoSignup Signup(UserDtoSignup signupDto) throws RemoteException;
}
