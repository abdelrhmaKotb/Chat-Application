package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.persistence.entities.User;

public interface UserDao {
    User getUser(String phoneNumber, String password);

    boolean isPhoneNumberExist(String phoneNumber);


    void updateUser(User newData);

    List<User> getUsersByNumbers(List<String> listOfNumbers);
    int insertUser(User user);
    User seletcByPhoneNumber(String phoneNum);

}
