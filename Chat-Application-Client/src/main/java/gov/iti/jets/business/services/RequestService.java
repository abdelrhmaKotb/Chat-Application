package gov.iti.jets.business.services;

import java.util.ArrayList;
import java.util.List;
import java.rmi.RemoteException;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.presentation.utils.ShowPopUp;

public class RequestService {

    public void sendRequests(String senderPhoneNumber, List<String> listOfContacts) {
        Server ser = RMIConnection.getServerServive();
        try {
            ser.sendRequests(senderPhoneNumber, listOfContacts);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
    }

    public String chkNumberInDB(String currentUserNumber, String contactNumber) {
        String errorMsg = "";
        Server ser = RMIConnection.getServerServive();
        try {
            errorMsg = ser.chkNumberInDB(currentUserNumber, contactNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
        return errorMsg;
    }

    public boolean isUserExistInDB(String contactNumber) {
        Server ser = RMIConnection.getServerServive();
        boolean isPhoneExist = false;
        try {
            isPhoneExist = ser.isUserExistInDB(contactNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
        return isPhoneExist;
    }

    public boolean isContactExistInDB(String currentUserNumber, String contactNumber) {
        Server ser = RMIConnection.getServerServive();
        boolean isContactExist = false;
        try {
            isContactExist = ser.isContactExistInDB(currentUserNumber, contactNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
        return isContactExist;
    }

    public boolean isRequestExistInDB(String currentUserNumber, String contactNumber) {
        Server ser = RMIConnection.getServerServive();
        boolean isRequestExist = false;
        try {
            isRequestExist = ser.isRequestExistInDB(currentUserNumber, contactNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
        return isRequestExist;
    }

    public List<String> getNamesOfRequestSenders(String currentUserNumber) {
        Server ser = RMIConnection.getServerServive();
        List<String> listOfNamesOfReqSenders = new ArrayList<>();
        try {
            listOfNamesOfReqSenders = ser.getNamesOfRequestSenders(currentUserNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
        return listOfNamesOfReqSenders;
    }

    public void deleteRequest(String sender, String currentUser) {
        Server ser = RMIConnection.getServerServive();
        try {
            ser.deleteRequest(sender, currentUser);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
    }
}
