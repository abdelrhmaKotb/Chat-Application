package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.business.helper.ChatCoordinator;
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
    ImageView btnSettings;

    @FXML
    GridPane mainGrid;

    @FXML
    VBox chat;

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
        Tooltip.install(btnSettings, new Tooltip("Settings"));
        Tooltip.install(notificationsBtn, new Tooltip("Notifications"));
        Tooltip.install(invitationsBtn, new Tooltip("Invitations"));
        ChatCoordinator.getInstance().setGrid(chat);

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
    
    @FXML
    private void clkGroupBtn(MouseEvent event) {

        try {
            Parent f = (Parent) FXMLLoader.load(getClass().getResource("/views/groups.fxml"));
            mainGrid.add(f, 1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }  

    @FXML
    private void clkNotifications(MouseEvent event) {
        try {
            Parent f = (Parent) FXMLLoader.load(getClass().getResource("/views/notifications.fxml"));
            mainGrid.add(f, 1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void clkInvitations(MouseEvent event) {

        try {
            Parent f = (Parent) FXMLLoader.load(getClass().getResource("/views/showInvitations.fxml"));
            mainGrid.add(f, 1, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }  
    public void viewGroups(MouseEvent mouseEvent) {
        try {
            Parent f = (Parent) FXMLLoader.load(getClass().getResource("/views/groups.fxml"));
            mainGrid.add(f, 1, 0);
             f = (Parent) FXMLLoader.load(getClass().getResource("/views/message.fxml"));
            mainGrid.add(f, 2, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewProfile(MouseEvent mouseEvent) {
        try {
            Parent f = (Parent) FXMLLoader.load(getClass().getResource("/views/editProfile.fxml"));

            mainGrid.add(f, 1, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}