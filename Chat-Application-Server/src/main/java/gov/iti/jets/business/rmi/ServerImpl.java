package gov.iti.jets.business.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gov.iti.jets.persistence.dao.GroupImpl;
import gov.iti.jets.persistence.dao.GroupMembersImpl;
import gov.iti.jets.business.mapper.GroupMapper;
import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.business.mapper.UserSignupMapperImpl;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.dto.UserDtoSignup;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import gov.iti.jets.persistence.entities.Group;
import gov.iti.jets.persistence.dao.ContactImpl;
import gov.iti.jets.persistence.dao.RequestImpl;
import gov.iti.jets.persistence.entities.Request;
import gov.iti.jets.persistence.entities.User;

public class ServerImpl extends UnicastRemoteObject implements Server {
    List<Client> clients = new ArrayList<>();
    Map<String, Client> clientsMap = new HashMap<>();

    public ServerImpl() throws RemoteException {
        super();
    }

    public void sayHello() {
        System.out.println("hello from server");
    }

    public void register(Client client) throws RemoteException {
        System.out.println("register");
        clients.add(client);
        clientsMap.put(client.getPhoneNumber(), client);
        System.out.println(clients);
        client.helloBack();
    }

    public void unregister(Client client) throws RemoteException {
        System.out.println("unregister");
        clients.remove(client);
        System.out.println(clients);
    }

    @Override
    public UserDto login(String phoneUmber, String password) throws RemoteException {
        UserImpl userDao = new UserImpl();
        UserMapper userMapper = new UserMapper();
        System.out.println(userMapper);
        User user = userDao.getUser(phoneUmber, password);
        if (user == null) {
            return null;
        }
        return userMapper.toDto(user);
    }

    public List<GroupDto> getGroups(String phoneNumber) {
        GroupImpl groupImpl = new GroupImpl();
        GroupMembersImpl groupMembers = new GroupMembersImpl();
        List<Integer> groups_id = groupMembers.getGroupByUserPhoneNum(phoneNumber);
        List<Group> groups = groupImpl.getGroupById(groups_id);
        int groupsSize = groups.size();
        List<GroupDto> groupDto = new ArrayList<>();
        GroupMapper groupMapper = new GroupMapper();
        for (int i = 0; i < groupsSize; i++) {
            groupDto.add(groupMapper.toDto(groups.get(i)));
        }
        return groupDto;
    }

    @Override
    public void send(MessageDto message) throws RemoteException {
        System.out.println(message);
        String reciverr = message.getReciver();
        if (clientsMap.containsKey(reciverr)) {
            Client reciver = clientsMap.get(reciverr);
            reciver.reciveMessage(message);
        }
    }

    @Override
    public void sendRequests(String senderPhoneNumber, List<String> listOfContacts) throws RemoteException {
        RequestImpl reqIml = new RequestImpl();
        for (int i = 0; i < listOfContacts.size(); i++) {
            Request req = new Request(senderPhoneNumber, listOfContacts.get(i), new Date(System.currentTimeMillis()));
            reqIml.createRequests(req);
        }
    }

    @Override
    public String chkNumberInDB(String currentUserNumber, String contactNumber) throws RemoteException {
        String errorMsg = "";
        if (!isUserExistInDB(contactNumber)) {
            errorMsg = "This contacts does not exist";
        } else if (isContactExistInDB(currentUserNumber, contactNumber)) {
            errorMsg = "This contacts already in your contacts";
        } else if (isRequestExistInDB(currentUserNumber, contactNumber)) {
            errorMsg = "You have already sent a request before";
        }
        return errorMsg;
    }

    @Override
    public boolean isUserExistInDB(String contactNumber) {
        UserImpl userDao = new UserImpl();
        return userDao.isPhoneNumberExist(contactNumber);
    }

    @Override
    public boolean isContactExistInDB(String currentUserNumber, String contactNumber) {
        ContactImpl contactDao = new ContactImpl();
        return contactDao.isContactExist(currentUserNumber, contactNumber);
    }

    @Override
    public boolean isRequestExistInDB(String currentUserNumber, String contactNumber) {
        RequestImpl RequestDao = new RequestImpl();
        return RequestDao.isRequestExist(currentUserNumber, contactNumber);
    }

    @Override 
    public ArrayList<CountryDto> getCountriesNames() throws RemoteException{
             return new countryDaoImpl().getCountries();

    }
    @Override
    public UserDtoSignup Signup(UserDtoSignup signupDto) throws RemoteException {
       System.out.println("inside function signup");
        UserImpl userDao = new UserImpl();
        User tempUser=new UserSignupMapperImpl().toEntity(signupDto);
       

        User user =userDao.createUser(new UserSignupMapperImpl().toEntity(signupDto));
        if (user == null) {
            System.out.println("this user already exist and not created");
            return null;
        }
        return new UserSignupMapperImpl().toDto(user);
    }
}
