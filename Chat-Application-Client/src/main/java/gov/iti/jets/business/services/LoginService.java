package gov.iti.jets.business.services;

import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.entities.User;

public class LoginService {


    public UserDto login(String phoneNumber,String password)
    {
        UserImpl userImpl = new UserImpl();
        User  user = userImpl.getUser(phoneNumber, password);

        if(user == null) return null;

        return new UserMapper().getContactDto(user);
    }
    
}
