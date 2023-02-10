package gov.iti.jets.business.models;

import java.util.List;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.GroupDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GroupsModel {
    ObservableList<GroupDto> groups;

    public GroupsModel(){
        try {
            List<GroupDto> groupDto  =  RMIConnection.getServerServive().getGroups(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
            System.out.println(groupDto.size());
            groups = FXCollections.observableArrayList(groupDto);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    public ObservableList<GroupDto> getGroups() {
        return groups;
    }
    public ObservableList<String> getGroupsNames() {
        ObservableList<String> list = FXCollections.observableArrayList();
        groups.forEach(e -> list.add(e.getName()));
        return list;
    }

    

  
}
