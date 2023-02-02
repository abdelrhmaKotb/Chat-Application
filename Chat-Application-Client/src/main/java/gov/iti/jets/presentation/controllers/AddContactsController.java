package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import gov.iti.jets.business.services.RequestService;
import gov.iti.jets.persistence.dao.RequestImpl;
import gov.iti.jets.persistence.entities.Request;
import gov.iti.jets.presentation.validation.SignUpValidation;
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
    @FXML
    private Label errorLabel;

    private SignUpValidation validationObj = new SignUpValidation();
    private RequestService requestService = new RequestService();
    private String currentUserNumber = "01110906004";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void saveRequest(ActionEvent event) {
        if(!contactTextField.getText().isEmpty() && isValidPhone()){
            listOfContacts.getItems().add(contactTextField.getText());
            requestService.sendRequests(currentUserNumber, listOfContacts.getItems());
        } else if(!listOfContacts.getItems().isEmpty()) {
            requestService.sendRequests(currentUserNumber, listOfContacts.getItems());
        } else {
            showErrorMessageLabel(errorLabel,contactTextField,"Add at least one contact");
        }

    }

    @FXML
    private void cancelAction(ActionEvent event) {
       
    }

    @FXML
    private void addToList(ActionEvent event) {
        if(isValidPhone()){
        
            errorLabel.setOpacity(0);
            contactTextField.setStyle("-fx-border-color:derive(#2D75E8,80%)");
            if (listOfContacts.getOpacity()==0){
                listOfContacts.setOpacity(1);
            }
            listOfContacts.getItems().add(contactTextField.getText());
            contactTextField.setText("");
        }
        
    }

    public boolean isValidPhone(){
        boolean isValidPhone = true;
        if(contactTextField.getText().isEmpty()){
            showErrorMessageLabel(errorLabel,contactTextField,"Phone Number is required");
            isValidPhone = false;
        } else if(validationObj.validatePhoneNumber(contactTextField.getText()).equals("invalid phone")) {
            showErrorMessageLabel(errorLabel,contactTextField,"Enter valid phone number");
            isValidPhone = false; 
        } else if (listOfContacts.getItems().contains(contactTextField.getText())){
            showErrorMessageLabel(errorLabel,contactTextField,"Phone number already exists in list");
            isValidPhone = false; 
        } else if(currentUserNumber.equals(contactTextField.getText())) {
            showErrorMessageLabel(errorLabel,contactTextField,"Can't add your number");
            isValidPhone = false;
        } else if(isValidPhone){
            String validationInDB= requestService.chkNumberInDB(currentUserNumber,contactTextField.getText()); 
            if(!validationInDB.equals("")){
                showErrorMessageLabel(errorLabel,contactTextField,validationInDB);
                isValidPhone = false;
            }
        }
        return isValidPhone;
    }
    
    public void showErrorMessageLabel(Label errorName, TextField field, String str) {
        String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
        errorName.setOpacity(1.0);
        field.setStyle(errorStyle);
        errorName.setText(str);

    }

}