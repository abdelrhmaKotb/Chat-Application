package gov.iti.jets.business.mapper;

import gov.iti.jets.dto.UserDto;
import gov.iti.jets.persistence.entities.User;

public class UserMapper  implements Mapper<User, UserDto> {
    @Override
    public UserDto toDto(User entity) {
        return new UserDto(
            entity.getPhoneNumber(),
            entity.getName(),
            entity.getEmail(),
            entity.getGender(),
            entity.getCountry(),
            entity.getDateOfBirth(),
            entity.getBio(),
            entity.getStatus(),
            entity.isAdmin()
        );
    }
   
    @Override

    public UserDto toEntity(User e) {
        return null;
    }


}
