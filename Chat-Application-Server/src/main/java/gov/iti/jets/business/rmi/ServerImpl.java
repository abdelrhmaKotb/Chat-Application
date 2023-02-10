package gov.iti.jets.business.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.persistence.dao.ContactImpl;
import gov.iti.jets.persistence.dao.RequestImpl;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.dao.interfaces.UserDao;
import gov.iti.jets.persistence.entities.Request;
import gov.iti.jets.persistence.entities.User;

public class ServerImpl extends UnicastRemoteObject implements Server {
    List<Client> clients = new ArrayList<>();

    public ServerImpl() throws RemoteException {
        super();
    }

    public void sayHello() {
        System.out.println("hello from server");
    }

    public void register(Client client) throws RemoteException {
        System.out.println("register");
        clients.add(client);
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

}
