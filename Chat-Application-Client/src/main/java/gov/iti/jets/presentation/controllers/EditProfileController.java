package gov.iti.jets.presentation.controllers;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.business.services.EditProfileService;
import gov.iti.jets.dto.UserDto;
//import gov.iti.jets.persistence.dao.countryDaoImpl;
//import gov.iti.jets.persistence.entities.Country;
import gov.iti.jets.enums.Mood;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.presentation.validation.SignUpValidation;
import javafx.animation.PauseTransition;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EditProfileController implements Initializable {
    @FXML
    ImageView editImgView;
    @FXML
    ImageView editImgIcon;
    @FXML
    ChoiceBox moodChoiceBox;
    @FXML
    TextField phoneTextField;
    @FXML
    TextField nameTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField bioTextField;
    @FXML
    ChoiceBox countryChoiceBox;
    @FXML
    DatePicker dateOfBirthPicker;
    @FXML
    Button saveBtn;
    //List<Country> country;
    @FXML
    private Label errorUerName, errorEmail, errorBio, errorDateOfBirth, errorCountry;
    @FXML
    Circle imgCircle;
    File file;
    CurrentUserModel currentUserModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editImgIcon.setVisible(false);
        saveBtn.setVisible(false);
        currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
        moodChoiceBox.getItems().add(Mood.AVAILABLE);
        moodChoiceBox.getItems().add(Mood.BUSY);
        moodChoiceBox.getItems().add(Mood.AWAY);
        //will be changed to users mood
        phoneTextField.setText(currentUserModel.getPhoneNumber());
        moodChoiceBox.getSelectionModel().select(currentUserModel.getStatus());
        nameTextField.setText(currentUserModel.getName());
        emailTextField.setText(currentUserModel.getEmail());
        bioTextField.setText(currentUserModel.getBio());
        /*country = new ArrayList<>();
        country = new countryDaoImpl().getCountries();
        dateOfBirthPicker.setDisable(true);

        for (Country c : country) {

            countryChoiceBox.getItems().add(c.getName());

        }*/
    }

    @FXML
    public void EnableEdit(MouseEvent mouseEvent) {
        editImgIcon.setVisible(true);
        saveBtn.setVisible(true);
        moodChoiceBox.setDisable(false);
        nameTextField.setEditable(true);
        emailTextField.setEditable(true);
        bioTextField.setEditable(true);
        countryChoiceBox.setDisable(false);
        dateOfBirthPicker.setDisable(false);

        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidUserName();

        });

        emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidEmail();

        });


        bioTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            isValidBio();

        });


        dateOfBirthPicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            isValidDateOfBirth();
        });
    }
    @FXML
    public void save(MouseEvent mouseEvent) {
        if( isValidUserName() && isValidEmail() && isValidBio() && isValidDateOfBirth()) {
            EditProfileService  editProf = new EditProfileService();
            UserDto userDto =new UserDto(currentUserModel.getPhoneNumber(), nameTextField.getText(), emailTextField.getText(), null, 1, Date.valueOf(dateOfBirthPicker.getValue()), bioTextField.getText(), Mood.AVAILABLE, false);
            boolean isUpdated = editProf.editProfile(userDto);
            System.out.println(isUpdated);

            if(isUpdated) {
                currentUserModel.setName( nameTextField.getText());
                currentUserModel.setEmail( emailTextField.getText());
                currentUserModel.setBio( bioTextField.getText());
                
       
            }
            Stage popup = new Stage();
// configure UI for popup etc...

// hide popup after 3 seconds:
PauseTransition delay = new PauseTransition(Duration.seconds(3));
delay.setOnFinished(e -> popup.hide());
try {
    Parent root=FXMLLoader.load(getClass().getResource("/views/edited.fxml"));
    popup.setScene(new Scene(root));
} catch (IOException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
}
popup.show();
delay.play();
saveBtn.setVisible(false);

        }
    }

    public boolean isValidUserName() {

        boolean flag = false;
        SignUpValidation validation = new SignUpValidation();
        if (!validation.validateUserName(nameTextField.getText()).trim().equals("valid userName")) {
            showErrorMessage(errorUerName, validation.validateUserName(nameTextField.getText().trim()));
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
        if (!validation.validateEmail(emailTextField.getText().trim()).equals("valid email")) {
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
        if (bioTextField.getText().trim().equals("")) {
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
        if (dateOfBirthPicker.getValue() == null) {
            showErrorMessage(errorDateOfBirth, "Required");
            flag = false;
        } else {
            errorDateOfBirth.setOpacity(0);
            dateOfBirthPicker.setStyle("-fx-border-color:derive(#2D75E8,80%)");
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

    @FXML


    public void editImage(MouseEvent mouseEvent) {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files ", "*.PNG", "*.JPG",
                "*.JPEG", "*.GIF", "*.SVG");
        fileChooser.getExtensionFilters().addAll(extFilter);
        file = fileChooser.showOpenDialog(null);
        if (file != null) {
            imgCircle.setStroke(null);
            Image img = new Image(file.toURI().toString());
            imgCircle.setFill(new ImagePattern(img));
        }
    }
}
