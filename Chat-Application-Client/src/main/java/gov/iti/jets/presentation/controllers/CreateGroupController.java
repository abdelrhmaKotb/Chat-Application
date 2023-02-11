// package gov.iti.jets.presentation.controllers;

// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.ResourceBundle;

// import gov.iti.jets.business.services.GroupsService;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.Button;
// import javafx.scene.control.CheckBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TextField;
// import javafx.stage.Stage;

// public class CreateGroupController implements Initializable {

//     @FXML
//     private Label createGroupLbl;
//     @FXML
//     private Label groupNameLbl;
//     @FXML
//     private TextField groupNameTextField;
//     @FXML
//     private Label groupMembersLbl;
//     @FXML
//     private ListView<CheckBox> listOfContacts;
//     @FXML
//     private Button closeBtn;
//     @FXML
//     private Button createGroupBtn;
//     @FXML
//     private Label errorLabel;

//     private Stage stage;
//     private GroupsService groupsService = new GroupsService();
//     private String currentUserNumber = "01110906004";

//     @Override
//     public void initialize(URL url, ResourceBundle rb) {
//         // List<String> listOfNameContact =
//         // groupsService.getnameOfContacts(currentUserNumber);
//         // for (int i = 0; i < listOfNameContact.size(); i++) {
//         // CheckBox chk = new CheckBox(listOfNameContact.get(i));
//         // listOfContacts.getItems().add(chk);
//         // }
//         List<String> listOfNameContact = new ArrayList<>();

//         try {
//             listOfNameContact = groupsService.getnameOfContacts(currentUserNumber);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         if (listOfNameContact.size() > 0) {
//             for (int i = 0; i < listOfNameContact.size(); i++) {
//                 CheckBox chk = new CheckBox(listOfNameContact.get(i));
//                 listOfContacts.getItems().add(chk);
//             }
//         }
//     }
//     @FXML
//     private void closeAction(ActionEvent event) {
//         stage.close();
//     }

//     @FXML
//     private void saveGroup(ActionEvent event) {
//         List<String> listOfNumbers = new ArrayList<>();
//         if (isValidName()) {
//             errorLabel.setOpacity(0);
//             groupNameTextField.setStyle("-fx-border-color:derive(#2D75E8,80%)");
//             for (int i = 0; i < listOfContacts.getItems().size(); i++) {
//                 if (listOfContacts.getItems().get(i).isSelected()) {
//                     String[] arrauOfNums = listOfContacts.getItems().get(i).getText().split(" : ");
//                     listOfNumbers.add(arrauOfNums[1]);
//                 }
//             }
//             if (listOfNumbers.size() < 2) {
//                 showErrorMessageLabel(errorLabel, null, "You must add atleast two members");
//                 return;
//             }
//             listOfNumbers.add(currentUserNumber);
//             groupsService.createGroup(groupNameTextField.getText(), currentUserNumber, listOfNumbers);
//             stage.close();
//         }
//     }

//     boolean isValidName() {
//         if (groupNameTextField.getText().isEmpty()) {
//             showErrorMessageLabel(errorLabel, groupNameTextField, "Invalid Name");
//             return false;
//         } else if (groupNameTextField.getText().length() > 100) {
//             showErrorMessageLabel(errorLabel, groupNameTextField, "Name length must less than 100 character");
//             return false;
//         }

//         return true;
//     }

//     public void showErrorMessageLabel(Label errorName, TextField field, String str) {
//         String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
//         errorName.setOpacity(1.0);
//         if (field != null) {
//             field.setStyle(errorStyle);
//         }
//         errorName.setText(str);

//     }

//     public void setStage(Stage popUp) {
//         stage = popUp;
//     }
// }
