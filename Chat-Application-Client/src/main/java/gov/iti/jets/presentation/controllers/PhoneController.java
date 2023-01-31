package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PhoneController implements Initializable{

    @FXML
    private TextField txtPhonnumber;

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
