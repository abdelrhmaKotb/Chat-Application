package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.persistence.entities.User;

public interface UserDao {

    User getUser(String phoneNumber, String password);

    User createUser(User user);

    User seletcByPhoneNumber(String phoneNum);

    // int create(User user);

    boolean isPhoneNumberExist(String phoneNumber);

    List<User> getUsersByNumbers(List<String> listOfNumbers);
    
    boolean updateUser(User newData);
}

