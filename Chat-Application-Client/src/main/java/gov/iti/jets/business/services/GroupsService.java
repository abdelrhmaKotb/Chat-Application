package gov.iti.jets.business.services;

import gov.iti.jets.business.dto.GroupDto;
import gov.iti.jets.persistence.dao.GroupImpl;
import gov.iti.jets.persistence.dao.GroupMembersImpl;
import gov.iti.jets.persistence.entities.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsService {
    public List<Group> getGroups() {
        GroupImpl groupImpl = new GroupImpl();
        GroupMembersImpl groupMembers = new GroupMembersImpl();
        List<Integer> groups_id = groupMembers.getGroupByUserPhoneNum("01110906004");
        List<Group> groups = groupImpl.getGroupById(groups_id);

        return groups;
    }
}
