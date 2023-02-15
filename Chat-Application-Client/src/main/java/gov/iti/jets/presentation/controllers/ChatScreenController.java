package gov.iti.jets.presentation.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.helper.NavCoordinator;
import gov.iti.jets.business.helper.StageCoordinator;
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
        ChatCoordinator.getInstance().openHome("home");
        

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

        File f = new File("keypassword.txt");
        if (f.delete()) {
            System.out.println("File deleted successfully");
            var currentt = ModelsFactory.getInstance().getCurrentUserModel();
            currentt.setBio(null);
            currentt.setCountry(0);
            currentt.setEmail(null);
            currentt.setGender(null);
            currentt.setImage(null);
            currentt.setPhoneNumber(null);
            currentt.setName(null);

            System.out.println(ModelsFactory.getInstance().getCurrentUserModel().getName());
        } else {
            System.out.println("Failed to delete the file");
        }
        StageCoordinator.getInstance().moveToLogin();

    }

}