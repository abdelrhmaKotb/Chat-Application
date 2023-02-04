package gov.iti.jets.presentation.controllers;

import gov.iti.jets.persistence.entities.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import gov.iti.jets.business.services.GroupsService;
public class GroupsController implements Initializable {
    List<Group> groups = new ArrayList<>();

    @FXML
    ListView<String> groupsListView;

    @FXML
    TextField searchTextField;

    ObservableList<String> groupList;


    List<String> groupsNames;
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        createList();
    }

    private void createList() {
        groups=GroupsService.getGroups("01110906004");
        groupsNames=new ArrayList<>();
        for(int i=0;i<groups.size();i++) {
            groupsNames.add(groups.get(i).getName());
        }
        groupList = FXCollections.observableArrayList(groupsNames);

        groupsListView.setItems(groupList);




    }



}
