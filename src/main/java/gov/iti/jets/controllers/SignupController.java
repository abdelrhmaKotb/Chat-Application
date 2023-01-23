package gov.iti.jets.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.net.URL;
import java.text.ParseException;
import java.util.regex.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;

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
    private ChoiceBox<String> choiceboxGender;

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
        choiceboxGender.getItems().add("Female");
        choiceboxGender.getItems().add("Male");
    }

    @FXML
    void clickBtnSignin(ActionEvent event) {

    }

    @FXML
    void clickBtnSignup(ActionEvent event) {
        validatePassword();
        confirmPass();
        validateDOB();
        if (choiceboxGender.getValue() ==null){
            System.out.println("Choose Gender");
        }
    }

    @FXML
    private void clickImageviewProfileImage(MouseEvent event) {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files ", "*.PNG", "*.JPG",
                "*.JPEG", "*.GIF", "*.SVG");
        fileChooser.getExtensionFilters().addAll(extFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image img = new Image(file.toURI().toString());
            imageviewProfileImage.setImage(img);
        }
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

    public void validateDOB() {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
        sdfrmt.setLenient(false);

        try {
            if (datepickerDateOfBirth.getValue() == null) {
                System.out.println("Write Date");
            } else {
                System.out.println(datepickerDateOfBirth.getValue().getMonthValue() + "/"
                        + datepickerDateOfBirth.getValue().getDayOfMonth() + "/"
                        + datepickerDateOfBirth.getValue().getYear());
                Date javaDate = sdfrmt.parse(datepickerDateOfBirth.getValue().getMonthValue() + "/"
                        + datepickerDateOfBirth.getValue().getDayOfMonth() + "/"
                        + datepickerDateOfBirth.getValue().getYear());
                System.out.println(datepickerDateOfBirth + " is valid date format");
            }

        } catch (ParseException e) {
            System.out.println(datepickerDateOfBirth + " is Invalid Date format");
        }
    }

}
