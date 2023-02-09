package gov.iti.jets.business.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import gov.iti.jets.persistence.dao.GroupImpl;
import gov.iti.jets.persistence.dao.GroupMembersImpl;
import gov.iti.jets.business.mapper.*;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.persistence.dao.UserImpl;

import gov.iti.jets.persistence.entities.Group;
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
    public  List<GroupDto> getGroups(String phoneNumber) {
        GroupImpl groupImpl = new GroupImpl();
        GroupMembersImpl groupMembers = new GroupMembersImpl();
        List<Integer> groups_id = groupMembers.getGroupByUserPhoneNum(phoneNumber);
        List<Group> groups = groupImpl.getGroupById(groups_id);
        int groupsSize=groups.size();
        List<GroupDto> groupDto=new ArrayList<>();
        GroupMapper groupMapper=new GroupMapper();
        for(int i=0;i<groupsSize;i++) {
            groupDto.add(groupMapper.toDto(groups.get(i)));
        }
        return groupDto;
    }


    @Override
    public UserDto Signup() throws RemoteException {
     


        
        return null;
    }

}
