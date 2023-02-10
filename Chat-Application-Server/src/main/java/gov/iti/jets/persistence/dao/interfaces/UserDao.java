package gov.iti.jets.persistence.dao.interfaces;


import gov.iti.jets.persistence.entities.User;

public interface UserDao {

    User getUser(String phoneNumber, String password);
    User createUser(User user);
    User seletcByPhoneNumber(String phoneNum);

}
