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

  /*   public static List<GroupDto> getGroups(String phoneNumber) {

        Server ser = RMIConnection.getServerServive();

        List<GroupDto> groupDto  = new ArrayList<>();
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
*/

        
        
    

    // public void createGroup(String name, String currentUserNumber, List<String> listOfNumbers) {
    //     GroupImpl groupIml = new GroupImpl();
    //     Group group = new Group(name, new Date(), currentUserNumber);
    //     int groupId = groupIml.createGroup(group);
    //     GroupMembersImpl groupMembersImpl = new GroupMembersImpl();
    //     for (int i = 0; i < listOfNumbers.size(); i++) {
    //         GroupMembers groupMember = new GroupMembers(groupId, listOfNumbers.get(i), new Date());
    //         groupMembersImpl.addMember(groupMember);
    //     }
    // }

    // public List<String> getnameOfContacts(String currentUserNumber) {
    //     ContactImpl contactImpl = new ContactImpl();
    //     List<Contact> contacts = contactImpl.getContactsForUser(currentUserNumber);
    //     List<String> listOfNumbers = new ArrayList<>();
    //     if (contacts.size() > 0) {
    //         for (int i = 0; i < contacts.size(); i++) {
    //             listOfNumbers.add(contacts.get(i).getFriendPhoneNumber());
    //         }
    //         List<String> listOfNameContact = new ArrayList<>();
    //         UserImpl userImp = new UserImpl();
    //         List<User> listOfUsers = userImp.getUsersByNumbers(listOfNumbers);
    //         for (int i = 0; i < listOfUsers.size(); i++) {
    //             listOfNameContact.add(listOfUsers.get(i).getName() + " : " + listOfUsers.get(i).getPhoneNumber());
    //         }
    //     }
    //     return listOfNumbers;
    // }
}
