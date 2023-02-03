package gov.iti.jets.business.services;

import java.util.Date;
import java.util.List;

import gov.iti.jets.persistence.dao.ContactImpl;
import gov.iti.jets.persistence.dao.RequestImpl;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.entities.Request;

public class RequestService {

    public void sendRequests(String senderPhoneNumber,List<String> listOfContacts) {
        RequestImpl reqIml = new RequestImpl();
        for (int i = 0; i < listOfContacts.size(); i++) {
            Request req = new Request(senderPhoneNumber, listOfContacts.get(i), new Date());
            reqIml.createRequests(req);
        }

    }

    public String chkNumberInDB(String currentUserNumber, String contactNumber) {
        String errorMsg = "";
        if (!isUserExistInDB(contactNumber)) {
            errorMsg = "This contacts does not exist";
        } else if (isContactExistInDB(currentUserNumber, contactNumber)) {
            errorMsg = "This contacts already in your contacts";
        } else if (isRequestExistInDB(currentUserNumber, contactNumber)) {
            errorMsg = "You have already sent a request before";
        }
        return errorMsg;
    }

    public boolean isUserExistInDB(String contactNumber) {
        UserImpl userDao = new UserImpl();
        return userDao.isPhoneNumberExist(contactNumber);
    }

    public boolean isContactExistInDB(String currentUserNumber, String contactNumber) {
        ContactImpl contactDao = new ContactImpl();
        return contactDao.isContactExist(currentUserNumber, contactNumber);
    }

    public boolean isRequestExistInDB(String currentUserNumber, String contactNumber) {
        RequestImpl RequestDao = new RequestImpl();
        return RequestDao.isRequestExist(currentUserNumber, contactNumber);
    }
}
