// package gov.iti.jets.presentation.controllers;

// import java.io.IOException;
// import java.net.URL;
// import java.util.ResourceBundle;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.ListView;
// import javafx.scene.control.Tooltip;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.stage.Modality;
// import javafx.stage.Stage;

// public class HomeController implements Initializable {

//     @FXML
//     private ImageView contactBtn;
//     @FXML
//     private ImageView addContactBtn;
//     @FXML
//     private ImageView groupBtn;
//     @FXML
//     private ImageView notificationBtn;
//     @FXML
//     private ImageView editProfileBtn;
//     @FXML
//     private ImageView profileImg;
//     @FXML
//     private ImageView signOutBtn;
//     @FXML
//     private ImageView AddgroupBtn;
//     @FXML
//     private ListView<?> contactList;

//     private Parent root;

//     @FXML
//     @Override
//     public void initialize(URL url, ResourceBundle rb) {
//         Tooltip.install(contactBtn, new Tooltip("Contacts"));
//         Tooltip.install(addContactBtn, new Tooltip("Add Contacts"));
//         Tooltip.install(groupBtn, new Tooltip("Groups"));
//         Tooltip.install(notificationBtn, new Tooltip("Notifications"));
//         Tooltip.install(editProfileBtn, new Tooltip("Edit Profile"));
//         Tooltip.install(signOutBtn, new Tooltip("Sign out"));
//         Tooltip.install(AddgroupBtn, new Tooltip("Add Group"));
//     }    

//     @FXML
//     private void clickContactBtn(MouseEvent event) {
//     }

//     @FXML
//     private void clickAddContactBtn(MouseEvent event) {
//         InviteContactController inviteCont = null;
//         try {
//             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/inviteContact.fxml"));
//             root = fxmlLoader.load();
//             inviteCont = fxmlLoader.getController();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         Stage stage=new Stage();
//         stage.initModality(Modality.APPLICATION_MODAL);
//         stage.setTitle("Invite Contacts");
//         inviteCont.setStage(stage);
//         //button1.setOnAction(e -> stage.close());

//         Scene scene1 = new Scene(root, 501, 345);

//         stage.setScene(scene1);
//         stage.setResizable(false);
//         stage.showAndWait();
//     }

//     @FXML
//     private void clickGroupBtn(MouseEvent event) {
//     }

//     @FXML
//     private void clickNotificationBtn(MouseEvent event) {
//     }

//     @FXML
//     private void clickEditProfileBtn(MouseEvent event) {
//     }

//     @FXML
//     private void clickSignOutBtn(MouseEvent event) {
//     }

//     @FXML
//     private void clickAddGroupBtn(MouseEvent event) {
//         CreateGroupController createGroupCont = null;
//         try {
//             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/createGroup.fxml"));
//             root = fxmlLoader.load();
//             createGroupCont = fxmlLoader.getController();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         Stage stage=new Stage();
//         stage.initModality(Modality.APPLICATION_MODAL);
//         stage.setTitle("Create Group");
//         createGroupCont.setStage(stage);
//         Scene scene1 = new Scene(root, 501, 400);
//         stage.setScene(scene1);
//         stage.setResizable(false);
//         stage.showAndWait();
//     }

    
// }