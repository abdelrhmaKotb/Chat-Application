package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.services.ContactService;
import gov.iti.jets.business.services.RequestService;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.UserDto;
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
import javafx.collections.ListChangeListener;

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

    ObservableList<String> observableList;
    private RequestService requestService = new RequestService();
    private CurrentUserModel currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
    private String currentUserNumber = currentUserModel.getPhoneNumber();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> listOfNamesOfReqSenders = new ArrayList<>();
        try {
            listOfNamesOfReqSenders = requestService.getNamesOfRequestSenders(currentUserNumber);
            ModelsFactory.getInstance().getInvitationsModel().setObservableInvitationsList(listOfNamesOfReqSenders);
            observableList = ModelsFactory.getInstance().getInvitationsModel().getObservableInvitationsList();

            observableList.addListener(new ListChangeListener<String>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends String> c) {
                    System.out.println("Changed on " + c);
                    listOfRequests.setItems(observableList);
                    listOfRequests.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                        @Override
                        public ListCell<String> call(ListView<String> param) {
                            return new XCell();
                        }
                    });
                    listOfRequests.refresh();

                }
            });
            if (observableList.size() > 0) {
                listOfRequests.setItems(observableList);
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

        private ContactService contactService = new ContactService();
        private RequestService requestService = new RequestService();
        private CurrentUserModel currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
        private String currentUserNumber = currentUserModel.getPhoneNumber();

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, acceptBtn, cancelBtn);
            HBox.setHgrow(pane, Priority.ALWAYS);
            acceptBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Accept " + label.getText() + " : " + label.getId());
                    contactService.acceptContact(currentUserNumber, label.getId());

                    var moel = ModelsFactory.getInstance().getContactsModel();
                    UserDto ud = moel.getContactDataByNumber(label.getId());

                    ContactDto cc = new ContactDto();
                    cc.setFriendName(ud.getName());
                    cc.setFriendPhoneNumber(ud.getPhoneNumber());
                    cc.setImage(ud.getImage());

                    moel.setContacts(cc);

                    requestService.deleteRequest(label.getId(), currentUserNumber);
                    getListView().getItems().remove(getItem());
                }
            });
            cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Cancel " + label.getText() + " : " + label.getId());
                    requestService.deleteRequest(label.getId(), currentUserNumber);
                    getListView().getItems().remove(getItem());
                }
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            acceptBtn.setStyle("-fx-background-color:  #7269ef; -fx-text-fill: #ffffff; ");
            acceptBtn.setCursor(Cursor.HAND);
            cancelBtn.setStyle("-fx-background-color:  #f4f4f4; -fx-text-fill: #7269ef;");
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
