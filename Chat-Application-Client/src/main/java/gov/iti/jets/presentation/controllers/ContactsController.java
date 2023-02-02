package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    List<String> d = new ArrayList<>();

    @FXML
    ListView<String> listContacts;

    @FXML
    TextField txtSearch;

    ObservableList<String> names;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init");
        d.add("ahmed");
        d.add("mohamed");
        d.add("ali");
        d.add("010066264");
        d.add("2154545");

        createList();
    }

    private void createList() {
        names = FXCollections.observableArrayList(d);
        // listContacts = new ListView<String>(names);
        listContacts.setItems(names);
        // listContacts.refresh();

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
        listContacts.setItems(FXCollections.observableArrayList(names.stream().filter(e -> e.contains(txtSearch.getText())).collect(Collectors.toList())));   

    }
}