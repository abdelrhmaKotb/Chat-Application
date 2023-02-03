package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.persistence.entities.User;

public interface UserDao {
    User getUser(String phoneNumber, String password);

    boolean isPhoneNumberExist(String phoneNumber);

    int insertUser(User user);

    User seletcByPhoneNumber(String phoneNum);

    List<User> getUsersByNumbers(List<String> listOfNumbers);

}
