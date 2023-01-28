package gov.iti.jets.business.mapper;

import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.persistence.entities.User;

public class UserMapper  implements Mapper<User,UserDto>{
    @Override
    public UserDto getContactDto(User entity) {
        
        return new UserDto(
            entity.getPhoneNumber(),
            entity.getName(),
            entity.getName(),
            entity.getGender(),
            entity.getCountry(),
            entity.getDateOfBirth(),
            entity.getBio(),
            entity.getStatus(),
            entity.isAdmin()
        );
    }
}
