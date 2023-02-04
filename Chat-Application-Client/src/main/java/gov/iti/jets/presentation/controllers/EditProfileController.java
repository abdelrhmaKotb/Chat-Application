package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
