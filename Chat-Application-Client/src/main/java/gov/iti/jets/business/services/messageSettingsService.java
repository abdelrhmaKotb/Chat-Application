package gov.iti.jets.business.services;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.GroupsMembersDto;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.presentation.utils.ShowPopUp;

import java.rmi.RemoteException;

public class messageSettingsService {
    public void msgSettings(ContactDto contactDto) {
        Server ser = RMIConnection.getServerServive();
        try {
            ser.msgSettings(contactDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
    }
    public void msgSettings(GroupsMembersDto groupDto) {
        Server ser = RMIConnection.getServerServive();
        try {
            ser.msgSettings(groupDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
    }
}
