package gov.iti.jets.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.regex.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SignupController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void clickBtnSignin(ActionEvent event) {

    }

    @FXML
    void clickBtnSignup(ActionEvent event) {
        validatePassword();
        confirmPass();

    }

    @FXML
    private void clickImageviewProfileImage(MouseEvent event) {

    }

    public void validatePassword() {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        if (!isValidPass(txtPassword.getText(), regex)) {
            System.out.println("Try Again");
        } else {
            System.out.println(txtPassword.getText());
        }

    }

    public boolean isValidPass(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void confirmPass() {
        if (!txtPassword.getText().equals(txtConfirmPassword.getText())) {
            System.out.println("Wrong pass" + txtConfirmPassword.getText());
        } else {
            System.out.println(txtConfirmPassword.getText());
        }
    }

}
