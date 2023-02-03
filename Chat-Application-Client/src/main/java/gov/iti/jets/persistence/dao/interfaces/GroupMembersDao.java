package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

public interface GroupMembersDao {
    public List<Integer> getGroupByUserPhoneNum(String phoneNumber);



}
