package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.business.services.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable{

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handelLogin()
    {
        String phoneNumber = txtUsername.getText();
        String password = txtPassword.getText();

        LoginService loginService = new LoginService();
        UserDto user =  loginService.login(phoneNumber, password);

        if(user == null)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("wrong username or password");
            alert.setHeaderText(null);
            alert.show();

        }else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("hello you must move to chat room later");
            alert.setHeaderText(null);
            alert.show();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
