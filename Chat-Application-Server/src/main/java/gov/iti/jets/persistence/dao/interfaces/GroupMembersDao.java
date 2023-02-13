package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.dto.GroupsMembersDto;
import gov.iti.jets.persistence.entities.GroupMembers;

public interface GroupMembersDao {
    public List<Integer> getGroupByUserPhoneNum(String phoneNumber);

    int addMember(GroupMembers member);
    void editGroupMemberStyle(GroupMembers groupMembers);
    List<GroupsMembersDto> getGroupMembersByUserPhoneNum(String phoneNumber);

}
