package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;

public class NotificationController implements Initializable {

    @FXML
    private VBox vBoxRoot;
    @FXML
    private Text notificationsLbl;
    @FXML
    private ListView<String> listOfNotifications;

    public static NotificationController notificationController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void addInListOfNotifications(String notification) {

        listOfNotifications.getItems().add(0, notification);
        listOfNotifications.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<String>() {

                private HBox hbox = new HBox();
                private Label label = new Label();
                private ImageView imageView = new ImageView(getClass().getResource("/images/round.png").toString());
                {
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    imageView.setFitHeight(10);
                    imageView.setFitWidth(10);
                    imageView.setTranslateY(4);
                    hbox.getChildren().addAll(imageView, label);
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        label.setText(item);
                        //label.setWrapText(true);
                        //label.setTextAlignment(TextAlignment.LEFT);
                        //label.setMaxWidth(300);
                        //hbox.setMaxWidth(300);
                        setGraphic(hbox);
                    }
                }
            };
            return cell;
        });
    }

}
