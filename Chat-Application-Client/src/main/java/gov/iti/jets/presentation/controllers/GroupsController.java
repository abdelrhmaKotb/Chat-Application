package gov.iti.jets.presentation.controllers;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;

public class GroupsController implements Initializable{

    private Parent root;

    @FXML
    private ImageView addgroupBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip.install(addgroupBtn, new Tooltip("Create Group"));
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

    
}
