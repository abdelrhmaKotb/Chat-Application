package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ContactController implements Initializable {

    @FXML
    public ImageView status;

    @FXML
    public ImageView mood;

    @FXML
    public Label contactName;
    
    @FXML
    public Label statusTxt;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
