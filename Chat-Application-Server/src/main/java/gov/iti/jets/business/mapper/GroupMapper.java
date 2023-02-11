package gov.iti.jets.business.mapper;

import gov.iti.jets.dto.GroupDto;

import gov.iti.jets.persistence.entities.Group;


public class GroupMapper implements Mapper<Group, GroupDto>{

    @Override
    public GroupDto toDto(Group entity) {
        return new GroupDto(entity.getId(),entity.getName(),entity.getDateOfCreation(), entity.getAdminPhoneNumber());
    }

    @Override
    public  Group toEntity(GroupDto e) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
