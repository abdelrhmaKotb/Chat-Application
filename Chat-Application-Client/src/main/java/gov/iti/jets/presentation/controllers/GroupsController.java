// package gov.iti.jets.presentation.controllers;

// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.stage.Modality;
// import javafx.stage.Stage;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.ListView;
// import javafx.scene.control.TextField;
// import javafx.scene.control.Tooltip;
// import java.io.IOException;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.ResourceBundle;
// import gov.iti.jets.business.services.GroupsService;
// import gov.iti.jets.dto.GroupDto;

// public class GroupsController implements Initializable {
//     List<GroupDto> groups = new ArrayList<>();

//     @FXML
//     ListView<String> groupsListView;

//     @FXML
//     TextField searchTextField;

//     ObservableList<String> groupList;

//     List<String> groupsNames;

//     private Parent root;

//     @FXML
//     private ImageView addgroupBtn;

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         Tooltip.install(addgroupBtn, new Tooltip("Create Group"));
//         createList();
//     }

//     private void createList() {
//         groups = GroupsService.getGroups("01110906004");
//         groupsNames = new ArrayList<>();
//         for (int i = 0; i < groups.size(); i++) {
//             groupsNames.add(groups.get(i).getName());
//         }
//         groupList = FXCollections.observableArrayList(groupsNames);

//         groupsListView.setItems(groupList);

//     }

//     @FXML
//     private void clickAddGroupBtn(MouseEvent event) {
//         // CreateGroupController createGroupCont = null;
//         // try {
//         //     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/createGroup.fxml"));
//         //     root = fxmlLoader.load();
//         //     createGroupCont = fxmlLoader.getController();
//         // } catch (IOException e) {
//         //     e.printStackTrace();
//         // }
//         // Stage stage = new Stage();
//         // stage.initModality(Modality.APPLICATION_MODAL);
//         // stage.setTitle("Create Group");
//         // createGroupCont.setStage(stage);
//         // Scene scene1 = new Scene(root, 501, 400);
//         // stage.setScene(scene1);
//         // stage.setResizable(false);
//         // stage.showAndWait();
//     }

// }
