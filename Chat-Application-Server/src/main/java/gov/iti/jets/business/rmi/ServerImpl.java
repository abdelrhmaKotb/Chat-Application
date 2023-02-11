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
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.dto.RequestDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.dto.UserDtoSignup;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import gov.iti.jets.persistence.entities.Contact;
import gov.iti.jets.persistence.entities.Group;
import gov.iti.jets.persistence.entities.GroupMembers;
import gov.iti.jets.persistence.dao.ContactImpl;
import gov.iti.jets.persistence.dao.RequestImpl;
import gov.iti.jets.persistence.entities.Request;
import gov.iti.jets.persistence.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerImpl extends UnicastRemoteObject implements Server {
    List<Client> clients = new ArrayList<>();
    static Map<String, Client> clientsMap = new HashMap<>();

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
        System.out.println(clientsMap.keySet());
        System.out.println(client.getPhoneNumber() + " phone");
        System.out.println(clientsMap);
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
        System.out.println(reciverr);
        System.out.println(clientsMap.keySet());
        if (clientsMap.containsKey(reciverr)) {
            System.out.println("yes contains " + clientsMap.size() + " " + clientsMap.get(reciverr).getPhoneNumber());
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
    public void createGroup(String name, String currentUserNumber, List<String> listOfNumbers) throws RemoteException {
        GroupImpl groupIml = new GroupImpl();
        Group group = new Group(name, new Date(System.currentTimeMillis()), currentUserNumber);
        int groupId = groupIml.createGroup(group);
        GroupMembersImpl groupMembersImpl = new GroupMembersImpl();
        for (int i = 0; i < listOfNumbers.size(); i++) {
            GroupMembers groupMember = new GroupMembers(groupId, listOfNumbers.get(i),
                    new Date(System.currentTimeMillis()));
            groupMembersImpl.addMember(groupMember);
        }

    }

    @Override
    public List<String> getnameOfContacts(String currentUserNumber) throws RemoteException {
        ContactImpl contactImpl = new ContactImpl();
        List<ContactDto> contacts = contactImpl.getContactsForUser(currentUserNumber);
        List<String> listOfNumbers = new ArrayList<>();
        List<String> listOfNameContact = new ArrayList<>();
        if (contacts.size() > 0) {
            for (int i = 0; i < contacts.size(); i++) {
                listOfNumbers.add(contacts.get(i).getFriendPhoneNumber());
            }
            UserImpl userImp = new UserImpl();
            List<User> listOfUsers = userImp.getUsersByNumbers(listOfNumbers);
            for (int i = 0; i < listOfUsers.size(); i++) {
                listOfNameContact.add(listOfUsers.get(i).getName() + " : " + listOfUsers.get(i).getPhoneNumber());
            }
        }
        return listOfNameContact;
    }

    public List<ContactDto> getUserContacts(String phone) throws RemoteException {
        System.out.println(phone);
        ContactImpl contactImpl = new ContactImpl();
        return contactImpl.getContactsForUser(phone);
    }

    @Override
    public void notifyUsersOnline(Client client) throws RemoteException {
        ContactImpl contactImpl = new ContactImpl();
        var listOfContatcs = contactImpl.getContactsForUser(client.getPhoneNumber());
        listOfContatcs.forEach(e -> {
            String phone = e.getFriendPhoneNumber();
            if (clientsMap.containsKey(phone)) {
                try {
                    clientsMap.get(phone).userOnlineNotify(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public boolean editProfile(UserDto uDto) {
        UserImpl userDao = new UserImpl();
        UserMapper userMapper = new UserMapper();
        User userEntity = userMapper.toEntity(uDto);
        boolean isUpdated = userDao.updateUser(userEntity);
        return isUpdated;
    }

    @Override
    public List<String> getNamesOfRequestSenders(String phone) throws RemoteException {
        RequestImpl requestImpl = new RequestImpl();
        List<RequestDto> listOfRequestDto = requestImpl.getUserRequests(phone);
        List<String> listOfNumbersOfReqSenders = new ArrayList<>();
        List<String> listOfNamesOfReqSenders = new ArrayList<>();
        if (listOfRequestDto.size() > 0) {
            for (int i = 0; i < listOfRequestDto.size(); i++) {
                listOfNumbersOfReqSenders.add(listOfRequestDto.get(i).getSender());
            }
            UserImpl userImp = new UserImpl();
            List<User> listOfUsers = userImp.getUsersByNumbers(listOfNumbersOfReqSenders);
            for (int i = 0; i < listOfUsers.size(); i++) {
                listOfNamesOfReqSenders.add(listOfUsers.get(i).getName() + " : " + listOfUsers.get(i).getPhoneNumber());
            }
        }
        return listOfNamesOfReqSenders;
    }

    @Override
    public void acceptContact(String currentUser, String friendNumber) throws RemoteException {
        ContactImpl contactImpl = new ContactImpl();
        Contact contact = new Contact(currentUser, friendNumber);
        contactImpl.create(contact);
    }

    @Override
    public ArrayList<CountryDto> getCountriesNames() throws RemoteException {
        return new countryDaoImpl().getCountries();

    }

    @Override
    public void deleteRequest(String sender, String currentUser) throws RemoteException {
        RequestImpl requestImpl = new RequestImpl();
        Request request = new Request(sender, currentUser);
        requestImpl.deleteRequest(request);
    }

    public UserDtoSignup Signup(UserDtoSignup signupDto) throws RemoteException {
        System.out.println("inside function signup");
        UserImpl userDao = new UserImpl();
        User tempUser = new UserSignupMapperImpl().toEntity(signupDto);

        User user = userDao.createUser(new UserSignupMapperImpl().toEntity(signupDto));
        if (user == null) {
            System.out.println("this user already exist and not created");
            return null;
        }
        return new UserSignupMapperImpl().toDto(user);
    }
}
