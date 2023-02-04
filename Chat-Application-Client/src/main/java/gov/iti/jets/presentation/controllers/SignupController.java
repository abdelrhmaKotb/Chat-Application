package gov.iti.jets.presentation.controllers;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.Date;
import java.util.regex.*;

import gov.iti.jets.business.services.SignupService;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import gov.iti.jets.persistence.entities.Country;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.presentation.helper.StageCoordinator;
import gov.iti.jets.presentation.validation.SignUpValidation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
    private ChoiceBox<String> choiceboxCountry;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtBio;

    @FXML
    private TextField txtEmail;

    @FXML
    private ImageView imageviewProfileImage;

    @FXML
    private Label errorPhoneNumber,
            errorUerName, errorEmail, errorPassword,
            errofConfirmPassword, errorBio, errorDateOfBirth, errorGender, errorCountry, errorProfileImage,
            successMessage, errorSignup;
    @FXML
    private Circle mycircle;

    File file;
    ArrayList<Country> countriesNames = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceboxGender.getItems().add("Female");
        choiceboxGender.getItems().add("Male");
        /*
         * choiceboxCountry.getItems().add("Egypt");
         * choiceboxCountry.getItems().add("Saudi Arabi");
         * choiceboxCountry.getItems().add("Emrates");
         */
        countryDaoImpl countryDao = new countryDaoImpl();
        countriesNames = countryDao.getCountries();
        for (Country c : countriesNames) {

            choiceboxCountry.getItems().add(c.getName());

        }

        Image img = new Image("/images/person.png", false);
        mycircle.setFill(new ImagePattern(img));

        txtPhoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidPhoneNumber();
            errorSignup.setOpacity(0);

        });

        txtUserName.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidUserName();

        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidEmail();

        });

        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> {

            validatePassword();

        });

        txtConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> {

            confirmPass();

        });

        txtBio.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidBio();

        });

        choiceboxGender.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> isValidGeneder());

        choiceboxCountry.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> isValidCountry());
        datepickerDateOfBirth.valueProperty().addListener((ov, oldValue, newValue) -> {
            isValidDateOfBirth();
        });

    }

    @FXML
    void clickBtnSignin(ActionEvent event) throws IOException {
        StageCoordinator coordinator = StageCoordinator.getInstance();
        coordinator.moveToLogin();
        // Stage stage = (Stage) btnSignup.getScene().getWindow();
        // stage.close();
        // Stage primarystage = new Stage();
        // primarystage.setTitle("Login");
        // Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        // primarystage.setScene(new Scene(root, 850, 600));
        // primarystage.show();

    }

    @FXML
    void clickBtnSignup(ActionEvent event) {
     
        if (validatePassword() && confirmPass()
        && isValidGeneder() && isValidCountry()
        && isValidPhoneNumber() && isValidUserName()
        && isValidImage() && isValidDateOfBirth() && isValidBio() && isValidEmail()) {
            User user = new User();
            user.setPhoneNumber(txtPhoneNumber.getText().trim());
            user.setName(txtUserName.getText().trim());
            user.setEmail(txtEmail.getText().trim());
            user.setPassword(txtPassword.getText().trim());
            user.setGender(choiceboxGender.getValue());
            user.setFile(file);
            user.setCountry(getIndex(choiceboxCountry.getValue()));
            user.setBio(txtBio.getText().trim());
            user.setDateOfBirth(Date.valueOf(datepickerDateOfBirth.getValue()));
            //user.setStatus("1");
            SignupService signup = new SignupService();
            int result = signup.signupUser(user);
            if (result == 1) {
                successMessage.setOpacity(1);
                StageCoordinator coordinator = StageCoordinator.getInstance();
                coordinator.moveToChat();

            } else {

                errorSignup.setOpacity(1);
            }

        } else {
            validatePassword();
            confirmPass();
            isValidGeneder();
            isValidCountry();
            isValidPhoneNumber();
            isValidUserName();
            isValidImage();
            isValidDateOfBirth();
            isValidBio();
            isValidEmail();
        }
    }

    @FXML
    private void clickImageviewProfileImage(MouseEvent event) {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files ", "*.PNG", "*.JPG",
                "*.JPEG", "*.GIF", "*.SVG");
        fileChooser.getExtensionFilters().addAll(extFilter);
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            mycircle.setStroke(null);
            Image img = new Image(file.toURI().toString());
            mycircle.setFill(new ImagePattern(img));
        }
    }

    public boolean validatePassword() {
        boolean flag = false;
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        if (!SignUpValidation.validPassword(txtPassword.getText().trim()).equals("valid password")) {
            showErrorMessage(errorPassword, SignUpValidation.validPassword(txtPassword.getText().trim()));
            flag = false;
        } else {
            resetFields(errorPassword);
            flag = true;
        }
        return flag;
    }

   

    public boolean confirmPass() {
        boolean flag = false;
        if (!txtPassword.getText().trim().equals(txtConfirmPassword.getText().trim())) {
            showErrorMessage(errofConfirmPassword, "Not Matched");
            flag = false;
        } else {
            resetFields(errofConfirmPassword);
            if (!txtPassword.getText().trim().equals("") && !txtConfirmPassword.getText().trim().equals("")) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public boolean isValidPhoneNumber() {
        boolean flag = false;
        SignUpValidation validation = new SignUpValidation();
        if (validation.validatePhoneNumber(txtPhoneNumber.getText().trim()).equals("invalid phone")) {
            showErrorMessage(errorPhoneNumber, "invalid phone");
            flag = false;
        } else {
            resetFields(errorPhoneNumber);
            flag = true;
        }
        return flag;
    }

    public boolean isValidUserName() {

        boolean flag = false;
        SignUpValidation validation = new SignUpValidation();
        if (!validation.validateUserName(txtUserName.getText()).trim().equals("valid userName")) {
            showErrorMessage(errorUerName, validation.validateUserName(txtUserName.getText().trim()));
            flag = false;
        } else {
            resetFields(errorUerName);
            flag = true;
        }

        return flag;

    }

    public boolean isValidEmail() {

        boolean flag = false;
        SignUpValidation validation = new SignUpValidation();
        if (!validation.validateEmail(txtEmail.getText().trim()).equals("valid email")) {
            showErrorMessage(errorEmail, "invalid email");
            flag = false;
        } else {
            resetFields(errorEmail);
            flag = true;

        }

        return flag;
    }

    public boolean isValidBio() {

        boolean flag = false;
        if (txtBio.getText().trim().equals("")) {
            showErrorMessage(errorBio, "Required");
            flag = false;
        } else {
            resetFields(errorBio);
            flag = true;
        }

        return flag;
    }

    public boolean isValidDateOfBirth() {

        boolean flag = false;
        if (datepickerDateOfBirth.getValue() == null) {
            showErrorMessage(errorDateOfBirth, "Required");
            flag = false;
        } else {
            errorDateOfBirth.setOpacity(0);
            datepickerDateOfBirth.setStyle("-fx-border-color:derive(#2D75E8,80%)");
            flag = true;
        }

        return flag;
    }

    public boolean isValidImage() {

        boolean flag = false;
        if (file == null) {
            flag = false;
            errorProfileImage.setOpacity(1);
        } else {
            flag = true;
            errorProfileImage.setOpacity(0);
        }

        return flag;

    }

    public boolean isValidGeneder() {

        boolean flag = false;

        if (choiceboxGender.getValue() == null) {
            showErrorMessage(errorGender, "Required");
            flag = false;
        } else {
            errorGender.setOpacity(0);
            choiceboxGender.setStyle("-fx-border-color:derive(#2D75E8,80%)");
            flag = true;
        }
        return flag;

    }

    public boolean isValidCountry() {
        boolean flag = false;

        if (choiceboxCountry.getValue() == null) {
            showErrorMessage(errorCountry, "Required");
            flag = false;
        } else {
            errorCountry.setOpacity(0);
            choiceboxCountry.setStyle("-fx-border-color:derive(#2D75E8,80%)");
            flag = true;
        }

        return flag;

    }

    public void showErrorMessage(Label errorName, String str) {
        errorName.setOpacity(1.0);
        errorName.setText(str);

    }

    public void resetFields(Label errorName) {
        errorName.setOpacity(0);
    }

    public int getIndex(String name) {
        int index = 0;
        for (int i = 0; i < countriesNames.size(); i++) {

            if (countriesNames.get(i).getName() == name)
                index = countriesNames.get(i).getId();
        }
        return index;
    }
}
