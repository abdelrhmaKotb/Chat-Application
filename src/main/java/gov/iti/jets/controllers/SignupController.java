package gov.iti.jets.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class SignupController {

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Button btnSignup;

    @FXML
    private Button btnSignin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private DatePicker datepickerDateOfBirth;

    @FXML
    private ChoiceBox<?> choiceboxGender;

    @FXML
    private ChoiceBox<?> choiceboxCountry;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtBio;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private ImageView imageviewProfileImage;

    @FXML
    void clickBtnSignin(ActionEvent event) {

    }

    @FXML
    void clickBtnSignup(ActionEvent event) {
        

    }

    @FXML
    private void clickImageviewProfileImage(MouseEvent event) {
        


    }

   

}
