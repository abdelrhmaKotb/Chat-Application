package gov.iti.jets.presentation.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import gov.iti.jets.business.models.GroupsModel;
import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.services.GroupsService;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.enums.Mood;

public class GroupsController implements Initializable {
    List<GroupDto> groups = new ArrayList<>();

    @FXML
    ListView<GroupDto> groupsListView;

    @FXML
    TextField searchTextField;

    ObservableList<GroupDto> groupList;

    List<String> groupsNames;

    private Parent root;

    @FXML
    private ImageView addgroupBtn;
    ObservableList<GroupDto> groupObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip.install(addgroupBtn, new Tooltip("Create Group"));
        groupList = ModelsFactory.getInstance().getGroups().getGroups();
        createList();
        groupsListView.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            if (newVal == null)
                return;

            System.out.println(newVal);

            ChatCoordinator.getInstance().openGroupChat(newVal);
        });
    }

    private void createList() {

        ModelsFactory modelsFactory = ModelsFactory.getInstance();
        GroupsModel groupsModel = modelsFactory.getGroups();
        groupObservableList = groupsModel.getGroups();
        groupsListView.setItems(groupList);

        groupsListView.setCellFactory(lv -> {
            ListCell<GroupDto> cell = new ListCell<GroupDto>() {

                @Override
                protected void updateItem(GroupDto item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox rootHbox = new HBox();
                        Circle imgCircle = new Circle(22);
                        Label namelabel = new Label();
                        Image userImage = new Image(getClass().getResource("/images/gg.png").toString());

                        {
                            
                            imgCircle.setFill(new ImagePattern(userImage));
                            imgCircle.setStroke(Color.BLACK);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            rootHbox.getChildren().addAll(imgCircle, namelabel);
                        }
                        namelabel.setText(item.getName());
                        namelabel.setWrapText(true);
                        namelabel.setTextAlignment(TextAlignment.JUSTIFY);
                        Font font = Font.font("SansSerif", FontWeight.BOLD, 11);
                        namelabel.setFont(font);
                        rootHbox.setMaxWidth(150);
                        setGraphic(rootHbox);
                    }
                }
            };
            return cell;
        });

        /*
         * groupsListView.setCellFactory(new Callback<ListView<GroupDto>,
         * ListCell<GroupDto>>() {
         * 
         * @Override
         * public ListCell<GroupDto> call(ListView<GroupDto> param) {
         * return new ListCell<GroupDto>() {
         * 
         * @Override
         * protected void updateItem(GroupDto item, boolean empty) {
         * super.updateItem(item, empty);
         * 
         * if (item == null || empty) {
         * setText(null);
         * setGraphic(null);
         * 
         * } else { 
         * setGraphic(null);
         * setText(item.getName());
         * }
         * }
         * };
         * }
         * });
         */
        /*
         * groups = GroupsService.getGroups(currentUserModel.getPhoneNumber());
         * groupsNames = new ArrayList<>();
         * for (int i = 0; i < groups.size(); i++) {
         * groupsNames.add(groups.get(i).getName());
         * }
         * groupList = FXCollections.observableArrayList(groupsNames);
         * 
         * groupsListView.setItems(groupList);
         */

    }

    @FXML
    private void clickAddGroupBtn(MouseEvent event) {
        CreateGroupController createGroupCont = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/createGroup.fxml"));
            root = fxmlLoader.load();
            createGroupCont = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Create Group");
        createGroupCont.setStage(stage);
        Scene scene1 = new Scene(root, 501, 400);
        stage.setScene(scene1);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    public void handelShearch() {
        groupsListView.setItems(FXCollections.observableArrayList(
            groupList.stream().filter(e -> e.getName().contains(searchTextField.getText()))
                        .collect(Collectors.toList())));

    }


}
