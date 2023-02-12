package gov.iti.jets.business.models;

import java.util.List;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.GroupsMembersDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.print.JobSettings;

public class GroupsModel {
    ObservableList<GroupDto> groups;
    ObservableList<GroupsMembersDto> myGroupsStyle;
    public GroupsModel(){
        try {
            List<GroupDto> groupDto  =  RMIConnection.getServerServive().getGroups(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
            System.out.println(groupDto.size());
            groups = FXCollections.observableArrayList(groupDto);
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            List<GroupsMembersDto> groupDto  =  RMIConnection.getServerServive().getMyGroupsStyle(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
            System.out.println(groupDto.size());
            myGroupsStyle = FXCollections.observableArrayList(groupDto);
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

    public GroupsMembersDto getGroupByGroup_id(int group_id) {
        int size = groups.size();
        //System.out.println(myGroupsStyle);
        for (int i = 0; i < size; i++) {
            if (myGroupsStyle.get(i).getGroup_id()==group_id) {
                return myGroupsStyle.get(i);
            }
        }
        return null;
    }

    public void editGroupStyle(GroupsMembersDto groupsMembersDto) {
        int size = myGroupsStyle.size();
        for (int i = 0; i < size; i++) {
            if (myGroupsStyle.get(i).getGroup_id()==groupsMembersDto.getGroup_id()) {
                myGroupsStyle.get(i).setBold(groupsMembersDto.isBold());
                myGroupsStyle.get(i).setUnderlined(groupsMembersDto.isUnderlined());
                myGroupsStyle.get(i).setItalic(groupsMembersDto.isItalic());
                myGroupsStyle.get(i).setBackgroundColor(groupsMembersDto.getBackgroundColor());
                myGroupsStyle.get(i).setFontColor(groupsMembersDto.getFontColor());
                myGroupsStyle.get(i).setFontSize(groupsMembersDto.getFontSize());
                myGroupsStyle.get(i).setFontStyle(groupsMembersDto.getFontStyle());
            }
        }
    }
}
