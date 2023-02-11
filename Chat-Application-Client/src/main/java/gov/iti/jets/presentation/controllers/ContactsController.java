package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.dto.ContactDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ContactsController implements Initializable {

    private Parent root;

    @FXML
    ListView<ContactDto> listContacts;

    @FXML
    TextField txtSearch;

    ObservableList<ContactDto> contacts;

    @FXML
    private ImageView addContactBtn;

    ObservableList<String> names;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createConatctsList();
        System.out.println("init");

        Tooltip.install(addContactBtn, new Tooltip("Invite Contact"));
    }

    private void createConatctsList() {

        ModelsFactory modelsFactory = ModelsFactory.getInstance();
        ContactsModel contactsModel = modelsFactory.getContactsModel();
        contacts = contactsModel.getContacts();

        listContacts.setItems(contacts);

        listContacts.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            if (newVal == null)
                return;

            System.out.println(newVal);

            ChatCoordinator.getInstance().openChat(newVal.getFriendPhoneNumber());
        });

        listContacts.setCellFactory(new Callback<ListView<ContactDto>, ListCell<ContactDto>>() {
            @Override
            public ListCell<ContactDto> call(ListView<ContactDto> param) {
                return new ListCell<ContactDto>() {

                    @Override
                    protected void updateItem(ContactDto item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);

                        } else {
                            ImageView imageView = new ImageView(getClass().getResource("/images/user.png").toString());
                            imageView.setFitHeight(10);
                            imageView.setFitWidth(10);
                            setGraphic(imageView);
                            setText(item.getFriendName());
                        }
                    }
                };
            }
        });

    }

    @FXML
    public void handelShearch() {
        listContacts.setItems(FXCollections.observableArrayList(
                contacts.stream().filter(e -> e.getFriendName().contains(txtSearch.getText()))
                        .collect(Collectors.toList())));

    }

    @FXML
    private void clickAddContactBtn(MouseEvent event) {
        InviteContactController inviteCont = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/inviteContact.fxml"));
            root = fxmlLoader.load();
            inviteCont = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Invite Contacts");

        inviteCont.setStage(stage);

        Scene scene1 = new Scene(root, 501, 345);

        stage.setScene(scene1);
        stage.setResizable(false);
        stage.showAndWait();
    }

}