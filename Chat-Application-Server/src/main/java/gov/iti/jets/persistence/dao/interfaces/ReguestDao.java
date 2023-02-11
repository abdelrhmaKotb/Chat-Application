package gov.iti.jets.persistence.dao.interfaces;

import java.util.List;

import gov.iti.jets.dto.RequestDto;
import gov.iti.jets.persistence.entities.Request;

public interface ReguestDao {
    int createRequests(Request request);

    //Request getRequests(String sender, String receiver);

    boolean isRequestExist(String currentUserNumber, String contactNumber);

    List<RequestDto> getUserRequests(String reciever);
}
