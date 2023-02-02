package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.persistence.entities.User;

public interface UserDao {
    int create(User user);
    User getUser(String phoneNumber,String password);
    boolean isPhoneNumberExist(String phoneNumber);
}
