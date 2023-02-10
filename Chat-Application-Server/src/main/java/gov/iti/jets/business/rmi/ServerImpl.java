package gov.iti.jets.business.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.business.mapper.UserSignupMapperImpl;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.dto.UserDtoSignup;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.persistence.dao.UserImpl;
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
        if(user == null)
        {
            return null;
        }
        return userMapper.toDto(user);
    }

   
    @Override
    public UserDtoSignup Signup(UserDtoSignup signupDto) throws RemoteException {
       
        UserImpl userDao = new UserImpl();
        UserMapper userMapper = new UserMapper();
        User user = userDao.createUser(new UserSignupMapperImpl().toEntity(signupDto));
        if(user == null)
        {
            return null;
        }
        return new UserSignupMapperImpl().toDto(user);
    }

}
