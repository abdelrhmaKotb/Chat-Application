package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
    private ListView<?> contactList;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
    
}
