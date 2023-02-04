package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.persistence.entities.User;

public interface UserDao {
    User getUser(String phoneNumber,String password);
    boolean isPhoneNumberExist(String phoneNumber);
    int insertUser(User user);
    User seletcByPhoneNumber(String phoneNum);
    void updateUser(User newData);

}
