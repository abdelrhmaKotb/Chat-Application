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


public class TestController implements Initializable {

    List<String> d = new ArrayList<>();

    @FXML
    ImageView btnUser;

    @FXML
    ImageView btnMessage;

    @FXML
    ImageView btnGroups;

    @FXML
    ImageView btnContacts;

    @FXML
    ImageView btnSettings;

    @FXML
    ListView<String> listContacts;

    @FXML
    TextField txtSearch;

    ObservableList<String> names;
    ObservableList<String> names2;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tooltip.install(btnUser, new Tooltip("Profile"));
        Tooltip.install(btnMessage, new Tooltip("Messages"));
        Tooltip.install(btnGroups, new Tooltip("Groups"));
        Tooltip.install(btnContacts, new Tooltip("Contacts"));
        Tooltip.install(btnSettings, new Tooltip("Settings"));

        d.add("ahmed");
        d.add("mohamed");
        d.add("ali");
        d.add("010066264");
        d.add("2154545");

        createList();
    }

    private void createList() {
        System.out.println("Fsd");
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
    public void tt() {
        names.add("null");
        System.out.println("fdfsdf");
    }


    @FXML
    public void handelShearch() {
        
        

        names2 = FXCollections.observableArrayList(names.stream().filter(e -> e.startsWith(txtSearch.getText())).collect(Collectors.toList()));

        listContacts.setItems(names2);

   
        
       
    }
}