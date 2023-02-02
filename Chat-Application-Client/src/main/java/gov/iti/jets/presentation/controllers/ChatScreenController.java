package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ChatScreenController implements Initializable {

    @FXML
    ImageView btnUser;

    @FXML
    ImageView btnMessage;

    @FXML
    ImageView btnGroups;

    @FXML
    ImageView btnContacts;

    @FXML
    ImageView btnSettings;

    @FXML
    GridPane mainGrid;

  


    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip.install(btnUser, new Tooltip("Profile"));
        Tooltip.install(btnMessage, new Tooltip("Messages"));
        Tooltip.install(btnGroups, new Tooltip("Groups"));
        Tooltip.install(btnContacts, new Tooltip("Contacts"));
        Tooltip.install(btnSettings, new Tooltip("Settings"));

        openContacts();

    }


    @FXML
    public void openContacts() {
        try {
            Parent f = (Parent) FXMLLoader.load(getClass().getResource("/views/contacts.fxml"));
            mainGrid.add(f, 1, 0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}