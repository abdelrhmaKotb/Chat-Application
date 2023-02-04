package gov.iti.jets.business.services;

import gov.iti.jets.business.dto.GroupDto;
import gov.iti.jets.persistence.dao.GroupImpl;
import gov.iti.jets.persistence.dao.GroupMembersImpl;
import gov.iti.jets.persistence.entities.Group;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupsService {
    public static List<Group> getGroups(String phoneNumber) {
        GroupImpl groupImpl = new GroupImpl();
        GroupMembersImpl groupMembers = new GroupMembersImpl();
        List<Integer> groups_id = groupMembers.getGroupByUserPhoneNum(phoneNumber);
        List<Group> groups = groupImpl.getGroupById(groups_id);

        return groups;
    }

    public void createGroup(String name,String currentUserNumber) {
        GroupImpl groupIml = new GroupImpl();
        Group group = new Group(name, new Date(), currentUserNumber);
        groupIml.createGroup(group);

    }
}
