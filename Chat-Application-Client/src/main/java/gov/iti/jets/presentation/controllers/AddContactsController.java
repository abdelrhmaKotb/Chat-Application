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


public class AddContactsController implements Initializable {

    @FXML
    private TextField contactTextField;
    @FXML
    private Label addContactLbl;
    @FXML
    private ListView<?> listOfContacts;
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
        // TODO
    }    

    @FXML
    private void saveContacts(ActionEvent event) {
    }

    @FXML
    private void cancelAction(ActionEvent event) {
    }

    @FXML
    private void addToList(ActionEvent event) {
    }
    
}