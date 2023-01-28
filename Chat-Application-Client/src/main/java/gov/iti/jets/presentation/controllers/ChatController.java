package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChatController implements Initializable {

    @FXML
    private ImageView contactBtn;
    @FXML
    private ImageView addContactBtn;
    @FXML
    private ImageView groupBtn;
    @FXML
    private ImageView notificationBtn;
    @FXML
    private ImageView editProfileBtn;
    @FXML
    private ImageView profileImg;
    @FXML
    private ImageView signOutBtn;
    @FXML
    private ImageView AddgroupBtn;
    @FXML
    private ListView<?> contactList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip.install(contactBtn, new Tooltip("Contacts"));
        Tooltip.install(addContactBtn, new Tooltip("Add Contacts"));
        Tooltip.install(groupBtn, new Tooltip("Groups"));
        Tooltip.install(notificationBtn, new Tooltip("Notifications"));
        Tooltip.install(editProfileBtn, new Tooltip("Edit Profile"));
        Tooltip.install(signOutBtn, new Tooltip("Sign out"));
        Tooltip.install(AddgroupBtn, new Tooltip("Add Group"));
    }    

    @FXML
    private void clickContactBtn(MouseEvent event) {
    }

    @FXML
    private void clickAddContactBtn(MouseEvent event) {
    }

    @FXML
    private void clickGroupBtn(MouseEvent event) {
    }

    @FXML
    private void clickNotificationBtn(MouseEvent event) {
    }

    @FXML
    private void clickEditProfileBtn(MouseEvent event) {
    }

    @FXML
    private void clickSignOutBtn(MouseEvent event) {
    }

    @FXML
    private void clickAddGroupBtn(MouseEvent event) {
    }
}