package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import gov.iti.jets.presentation.helper.ModelsFactory;
import gov.iti.jets.presentation.models.ContactsModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class ContactsController implements Initializable {

    @FXML
    ListView<String> listContacts;

    @FXML
    TextField txtSearch;

    ObservableList<String> contacts;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createConatctsList();
    }

    private void createConatctsList() {

        ModelsFactory modelsFactory = ModelsFactory.getInstance();
        ContactsModel contactsModel = modelsFactory.getContactsModel();
        contacts = contactsModel.getContacts();

        listContacts.setItems(contacts);

       

        listContacts.getSelectionModel().selectedItemProperty().addListener((obs,old,newVal) -> {
            if (newVal == null)
                    return;

                    System.out.println(newVal);
        });

        listContacts.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);

                        } else {
                            ImageView imageView = new ImageView(getClass().getResource("/images/user.png").toString());
                            imageView.setFitHeight(10);
                            imageView.setFitWidth(10);
                            setGraphic(imageView);
                            setText(item);
                        }
                    }
                };
            }
        });

    }

    @FXML
    public void handelShearch() {
        listContacts.setItems(FXCollections.observableArrayList(
                contacts.stream().filter(e -> e.contains(txtSearch.getText())).collect(Collectors.toList())));

    }
}