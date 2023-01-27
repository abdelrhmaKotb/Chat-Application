package gov.iti.jets.business.mapper;

import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.persistence.entities.User;

public class UserMapper  implements Mapper<User,UserDto>{
    @Override
    public UserDto toDto(User entity) {
        
        return null;
    }
}
