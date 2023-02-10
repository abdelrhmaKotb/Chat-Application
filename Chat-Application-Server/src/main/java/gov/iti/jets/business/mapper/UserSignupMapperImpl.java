package gov.iti.jets.business.mapper;

import gov.iti.jets.dto.UserDtoSignup;
import gov.iti.jets.persistence.entities.User;

public class UserSignupMapperImpl implements signupMapper<User, UserDtoSignup> {
    
  
    @Override
    public UserDtoSignup toDto(User entity) {
        return new UserDtoSignup(
            entity.getPhoneNumber(),
            entity.getName(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getGender(),
            entity.getCountry(),
            entity.getDateOfBirth(),
            entity.getBio(),
            entity.getStatus(),
            entity.getImage()
        );
    }

    @Override
    public User toEntity(UserDtoSignup e) {
    return new User(e.getPhoneNumber(), e.getName(), e.getEmail(), e.getPassword(), e.getGender(), e.getCountry(), e.getDateOfBirth(), e.getBio(), false, false, e.getStatus(), e.getImage());
    }
 


}
