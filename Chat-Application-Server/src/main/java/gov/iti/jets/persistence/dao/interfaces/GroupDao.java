package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.persistence.entities.Group;

import java.util.List;

public interface GroupDao {
    public List<Group> getGroupById(List<Integer> groups_id);

    int createGroup(Group group);


    List<String> getGroupMember(int groupid) ;

}
