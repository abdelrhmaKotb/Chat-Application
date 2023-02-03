package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private ListView<String> listOfContacts;
    @FXML
    private Button closeBtn;
    @FXML
    private Button createGroupBtn;

    private Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void closeAction(ActionEvent event) {
        stage.close();
    }

    @FXML
    private void saveGroup(ActionEvent event) {
    }
    
    public void setStage(Stage popUp) {
        stage = popUp;
    }
}
