package gov.iti.jets.business.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.GroupsMembersDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.presentation.utils.ShowPopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.print.JobSettings;

public class GroupsModel {
    ObservableList<GroupDto> groups;

    public void setMyGroupsStyle(ObservableList<GroupsMembersDto> myGroupsStyle) {
        this.myGroupsStyle = myGroupsStyle;
    }

    ObservableList<GroupsMembersDto> myGroupsStyle;
    ObservableMap<Integer,List<UserDto>> membersData;
    public GroupsModel(){
        try {
            List<GroupDto> groupDto  =  RMIConnection.getServerServive().getGroups(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
            System.out.println(groupDto.size());
            groups = FXCollections.observableArrayList(groupDto);
        } catch (Exception e) {
           e.printStackTrace();
           new ShowPopUp().notifyServerDown();
        }

        try {
            List<GroupsMembersDto> groupDto  =  RMIConnection.getServerServive().getMyGroupsStyle(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
            System.out.println(groupDto.size());
            myGroupsStyle = FXCollections.observableArrayList(groupDto);
        } catch (Exception e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
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
    public ObservableMap<Integer,List<UserDto>> getMembersData() {
        for (int i=0;i<myGroupsStyle.size();i++) {
            ContactsModel contactsModel=new ContactsModel();
            UserDto uDto= contactsModel.getContactDataByNumber(myGroupsStyle.get(i).getMemberPhoneNumber());
            if(membersData.containsKey(myGroupsStyle.get(i).getGroup_id())) {

                membersData.get(myGroupsStyle.get(i).getGroup_id()).add(uDto);
            }
            else {
                List<UserDto>members=new ArrayList<>();
                members.add(uDto);
                membersData.put(myGroupsStyle.get(i).getGroup_id(),members);
            }
        }
        return membersData;
    }
    public UserDto getMemberData(int group_id,String userNumber){
        for(int i=0;i<membersData.get(group_id).size();i++)
            if(membersData.get(group_id).get(i).equals(userNumber))
                return membersData.get(group_id).get(i);
        return null;
    }

}
