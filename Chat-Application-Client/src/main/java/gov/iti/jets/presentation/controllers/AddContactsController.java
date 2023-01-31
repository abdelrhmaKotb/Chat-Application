package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import gov.iti.jets.persistence.dao.RequestImpl;
import gov.iti.jets.persistence.entities.Request;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class AddContactsController implements Initializable {

    @FXML
    private TextField contactTextField;
    @FXML
    private Label addContactLbl;
    @FXML
    private ListView<String> listOfContacts;
    @FXML
    private Label errorMsgLbl;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button addBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void saveContacts(ActionEvent event) {
        Request req = new Request("01110906004", contactTextField.getText(), new Date());
        RequestImpl reqIml = new RequestImpl();
        reqIml.createRequests(req);
    }

    @FXML
    private void cancelAction(ActionEvent event) {
       
    }

    @FXML
    private void addToList(ActionEvent event) {
        listOfContacts.getItems().add(contactTextField.getText());
        contactTextField.setText("");
    }
    
}