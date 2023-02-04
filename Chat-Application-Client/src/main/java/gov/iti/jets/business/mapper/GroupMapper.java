package gov.iti.jets.business.mapper;

import gov.iti.jets.business.dto.GroupDto;
import gov.iti.jets.persistence.entities.Group;


public class GroupMapper implements Mapper<Group, GroupDto>{

    @Override
    public GroupDto getContactDto(Group entity) {
        return new GroupDto(entity.getId(),entity.getName(),entity.getDateOfCreation(), entity.getAdminPhoneNumber());
    }
}
