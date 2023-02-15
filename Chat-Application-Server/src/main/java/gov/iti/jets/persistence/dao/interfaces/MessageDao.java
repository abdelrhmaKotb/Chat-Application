package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.dto.MessageDto;

public interface MessageDao {
    void createMessage(MessageDto messageDto);
    List<MessageDto> getChatMessages(String ssender ,String reciver);
}
