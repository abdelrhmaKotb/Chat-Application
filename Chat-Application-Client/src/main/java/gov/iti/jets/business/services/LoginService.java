package gov.iti.jets.business.services;

import java.rmi.RemoteException;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Server;

public class LoginService {

    public UserDto login(String phoneNumber, String password) {
        Server ser = RMIConnection.getServerServive();

        UserDto user = null;
        try {
            user = ser.login(phoneNumber, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return user;
    }

}
