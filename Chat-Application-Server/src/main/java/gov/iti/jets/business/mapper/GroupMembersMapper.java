package gov.iti.jets.business.mapper;

import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupsMembersDto;
import gov.iti.jets.persistence.entities.Contact;
import gov.iti.jets.persistence.entities.GroupMembers;

public class GroupMembersMapper implements Mapper<GroupMembers, GroupsMembersDto> {


    @Override
    public GroupsMembersDto toDto(GroupMembers contact) {
        return null;
    }

    @Override
    public GroupMembers toEntity(GroupsMembersDto e) {
        return new GroupMembers(e.getGroup_id(), e.getMemberPhoneNumber(), e.getFontSize(),
                e.getFontColor(), e.getFontStyle(), e.getBackgroundColor(), e.isBold(), e.isUnderlined(), e.isItalic());
    }
}