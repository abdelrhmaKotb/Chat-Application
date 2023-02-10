package gov.iti.jets.business.services;

import java.rmi.RemoteException;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Server;

public class EditProfileService
{
    public boolean editProfile(UserDto userDto) {
        Server ser = RMIConnection.getServerServive();
        System.out.println(userDto);
        boolean isUpdated = false;
        try {
            isUpdated = ser.editProfile(userDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
}
