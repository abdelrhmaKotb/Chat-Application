package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gov.iti.jets.business.mapper.ContactMapper;
import gov.iti.jets.business.services.GroupsService;
import gov.iti.jets.persistence.dao.ContactImpl;
import gov.iti.jets.persistence.entities.Contact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CreateGroupController implements Initializable {

    @FXML
    private Label createGroupLbl;
    @FXML
    private Label groupNameLbl;
    @FXML
    private TextField groupNameTextField;
    @FXML
    private Label groupMembersLbl;
    @FXML
    private ListView<CheckBox> listOfContacts;
    @FXML
    private Button closeBtn;
    @FXML
    private Button createGroupBtn;

    private Stage stage;
    private GroupsService groupsService = new GroupsService();
    private String currentUserNumber = "01110906004";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContactImpl userImpl = new ContactImpl();
        List<Contact>  contacts = userImpl.getContactsForUser(currentUserNumber);
        ContactMapper contactMapper= new ContactMapper();

        for(int i=0;i<contacts.size();i++){
            CheckBox chk = new CheckBox(contactMapper.getContactDto(contacts.get(i)).getFriendPhoneNumber());
            listOfContacts.getItems().add(chk);
        }
    }    

    @FXML
    private void closeAction(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void saveGroup(ActionEvent event) {
        groupsService.createGroup(groupNameTextField.getText(), currentUserNumber);
    }
    
    public void setStage(Stage popUp) {
        stage = popUp;
    }
}
