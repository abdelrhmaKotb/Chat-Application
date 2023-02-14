package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.NavCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
    ImageView btnSignOut;

    @FXML
    GridPane mainGrid;

    @FXML
    VBox chat;

    @FXML
    VBox nav;

    @FXML
    private ImageView notificationsBtn;

    @FXML
    private ImageView invitationsBtn;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip.install(btnUser, new Tooltip("Profile"));
        Tooltip.install(btnMessage, new Tooltip("Messages"));
        Tooltip.install(btnGroups, new Tooltip("Groups"));
        Tooltip.install(btnContacts, new Tooltip("Contacts"));
        Tooltip.install(btnSignOut, new Tooltip("Sign Out"));
        Tooltip.install(notificationsBtn, new Tooltip("Notifications"));
        Tooltip.install(invitationsBtn, new Tooltip("Invitations"));
        ChatCoordinator.getInstance().setGrid(chat);
        NavCoordinator.getInstance().setGrid(nav);
        
        clkNotifications();
        viewProfile();

    }

    @FXML
    public void openContacts() {
        NavCoordinator.getInstance().goToContacts();
    }

    @FXML
    private void clkNotifications() {
        NavCoordinator.getInstance().goToNotifications();
    }

    @FXML
    private void clkInvitations(MouseEvent event) {
        NavCoordinator.getInstance().goToInvitations();
    }

    @FXML
    public void viewGroups(MouseEvent mouseEvent) {
        NavCoordinator.getInstance().goToGroups();
    }

    @FXML
    public void viewProfile() {
        NavCoordinator.getInstance().goToEditProfile();
    }
    
    @FXML
    void logoutBtnAction(MouseEvent event) {
        System.exit(0);

    }

}