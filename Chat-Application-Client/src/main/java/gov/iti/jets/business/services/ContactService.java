package gov.iti.jets.business.services;

import java.rmi.RemoteException;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.interfaces.Server;

public class ContactService {

    public void acceptContact(String currentUser, String friendNumber) {
        Server ser = RMIConnection.getServerServive();
        try {
            ser.acceptContact(currentUser, friendNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
