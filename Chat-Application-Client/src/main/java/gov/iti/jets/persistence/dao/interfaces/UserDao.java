package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.persistence.entities.User;

public interface UserDao {
    int insertUser(User user);

    User seletcByPhoneNumber(String phoneNum);

    User getUser(String phoneNumber, String password);
}
