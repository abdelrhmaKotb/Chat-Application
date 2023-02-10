package gov.iti.jets.business.services;

// import gov.iti.jets.persistence.dao.ContactImpl;
// import gov.iti.jets.persistence.dao.GroupImpl;
// import gov.iti.jets.persistence.dao.GroupMembersImpl;
// import gov.iti.jets.persistence.dao.UserImpl;
// import gov.iti.jets.persistence.entities.Contact;
// import gov.iti.jets.persistence.entities.Group;
// import gov.iti.jets.persistence.entities.GroupMembers;
// import gov.iti.jets.persistence.entities.User;

import java.rmi.RemoteException;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.interfaces.Server;

import java.util.ArrayList;

import java.util.List;

public class GroupsService {

    public static List<GroupDto> getGroups(String phoneNumber) {

        Server ser = RMIConnection.getServerServive();

        List<GroupDto> groupDto = new ArrayList<>();
        try {
            groupDto = ser.getGroups(phoneNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for (GroupDto groupDto2 : groupDto) {
            System.out.println(groupDto2.getName());
        }
        return groupDto;
    }

    public void createGroup(String name, String currentUserNumber, List<String> listOfNumbers) {
        Server ser = RMIConnection.getServerServive();
        try {
            ser.createGroup(name, currentUserNumber, listOfNumbers);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<String> getnameOfContacts(String currentUserNumber) {
        Server ser = RMIConnection.getServerServive();
        List<String> listOfNumbers = new ArrayList<>();
        try {
            listOfNumbers = ser.getnameOfContacts(currentUserNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return listOfNumbers;
    }
}
