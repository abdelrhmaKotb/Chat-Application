package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.services.RequestService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.util.Callback;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ShowInvitationsController implements Initializable {

    @FXML
    private VBox rootVBox;
    @FXML
    private Text invitationsLbl;
    @FXML
    private ListView<String> listOfRequests;

    private RequestService requestService = new RequestService();
    private CurrentUserModel currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
    private String currentUserNumber = currentUserModel.getPhoneNumber();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> listOfNamesOfReqSenders = new ArrayList<>();

        try {
            listOfNamesOfReqSenders = requestService.getNamesOfRequestSenders(currentUserNumber);
            if (listOfNamesOfReqSenders.size() > 0) {
                ObservableList<String> list = FXCollections.observableArrayList(listOfNamesOfReqSenders);
                listOfRequests.setItems(list);
                listOfRequests.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        return new XCell();
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class XCell extends ListCell<String> {

        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        Button acceptBtn = new Button("Accept");
        Button cancelBtn = new Button("Cancel");
        String lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, acceptBtn, cancelBtn);
            HBox.setHgrow(pane, Priority.ALWAYS);
            acceptBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Accept " + label.getText() + " : " + label.getId());
                    acceptBtn.setDisable(true);
                    cancelBtn.setDisable(true);
                }
            });
            cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Cancel " + label.getText() + " : " + label.getId());
                    acceptBtn.setDisable(true);
                    cancelBtn.setDisable(true);
                }

            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            acceptBtn.setStyle("-fx-background-color:  #7269ef;  ");
            acceptBtn.setCursor(Cursor.HAND);
            cancelBtn.setStyle("-fx-background-color:  #f4f4f4;");
            cancelBtn.setCursor(Cursor.HAND);
            setText(null);
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                String[] arrauOfNums = item.split(" : ");
                label.setId(arrauOfNums[1]);
                label.setText(item != null ? arrauOfNums[0] : "<null>");
                setGraphic(hbox);
            }
        }
    }

}
