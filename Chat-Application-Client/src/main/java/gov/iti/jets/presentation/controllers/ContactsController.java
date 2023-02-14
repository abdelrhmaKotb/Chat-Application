package gov.iti.jets.presentation.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.enums.Mood;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;

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

        contacts.addListener(new ListChangeListener<ContactDto>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends ContactDto> c) {
                System.out.println("Changed on " + c);
                listContacts.refresh();

            }
        });

        listContacts.getSelectionModel().selectedItemProperty().addListener((obs, old, newVal) -> {
            if (newVal == null)
                return;

            System.out.println(newVal);

            ChatCoordinator.getInstance().openChat(newVal.getFriendPhoneNumber());
        });

        listContacts.setCellFactory(lv -> {
            ListCell<ContactDto> cell = new ListCell<ContactDto>() {

                // private HBox rootHbox = new HBox();
                // private VBox vBoxOfHboxAndNum = new VBox();
                // private HBox hBoxOfNameAndImg = new HBox();
                // Circle imgCircle = new Circle(22);
                // private Label namelabel = new Label();
                // private Label phonelabel = new Label();
                // ImageView imageView = new ImageView();

                // Image userImage = new
                // Image(getClass().getResource("/images/sheka.jpg").toString());
                // {
                // imgCircle.setFill(new ImagePattern(userImage));
                // imgCircle.setStroke(Color.BLACK);
                // setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                // rootHbox.setSpacing(3);
                // hBoxOfNameAndImg.getChildren().addAll(namelabel, imageView);
                // vBoxOfHboxAndNum.getChildren().addAll(hBoxOfNameAndImg, phonelabel);
                // rootHbox.getChildren().addAll(imgCircle, vBoxOfHboxAndNum);
                // }

                @Override
                protected void updateItem(ContactDto item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox rootHbox = new HBox();
                        VBox vBoxOfHboxAndNum = new VBox();
                        HBox hBoxOfNameAndImg = new HBox();
                        Circle imgCircle = new Circle(22);
                        Label namelabel = new Label();
                        Label phonelabel = new Label();
                        ImageView imageView = new ImageView();

                        Image userImage = null;

                        if (item.getImage() != null) {
                            userImage = new Image(new ByteArrayInputStream(item.getImage()));
                        } else {
                            userImage = new Image(getClass().getResource("/images/user.png").toString());
                        }
                        {
                            imgCircle.setFill(new ImagePattern(userImage));
                            imgCircle.setStroke(Color.BLACK);
                            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                            rootHbox.setSpacing(3);
                            hBoxOfNameAndImg.getChildren().addAll(namelabel, imageView);
                            vBoxOfHboxAndNum.getChildren().addAll(hBoxOfNameAndImg, phonelabel);
                            rootHbox.getChildren().addAll(imgCircle, vBoxOfHboxAndNum);
                        }

                        String image = "";
                        try {
                            if (RMIConnection.getServerServive().isUserOnline(item)) {
                                if (item.getFrinMood() == Mood.AWAY) {
                                    image = "away.png";
                                } else if (item.getFrinMood() == Mood.BUSY) {
                                    image = "red.png";
                                } else {
                                    image = "green.png";
                                }
                                imageView.setImage(new Image(getClass().getResource("/images/" + image).toString()));
                                imageView.setFitHeight(15);
                                imageView.setFitWidth(15);

                            } else {
                                imageView.setImage(new Image(getClass().getResource("/images/offline.png").toString()));
                                imageView.setFitHeight(15);
                                imageView.setFitWidth(15);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        namelabel.setText(item.getFriendName());
                        namelabel.setWrapText(true);
                        namelabel.setTextAlignment(TextAlignment.JUSTIFY);

                        Font font = Font.font("SansSerif", FontWeight.BOLD, 11);
                        namelabel.setFont(font);
                        phonelabel.setText(item.getFriendPhoneNumber());
                        rootHbox.setMaxWidth(150);
                        setGraphic(rootHbox);
                    }
                }
            };
            return cell;
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