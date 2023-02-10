package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javafx.scene.control.TextArea;
public class MessageController implements Initializable {
    @FXML
    Text recieverNameText;
    @FXML
    TextArea textArea;

    @FXML
    VBox msgvBox;
    @FXML
    TextField msgTextField;
    ImageView staticImage;
    @FXML
    Circle circle;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        staticImage =new ImageView();

    }
    @FXML
    private void sendAction(MouseEvent event) {

        send();

    }


    public void send() {
        chatComponent(Pos.CENTER_RIGHT);
    }


    public void setRecievedMsg(String msg) {

    }
    private Circle imageChatCSS() {
        ImageView chatImage = new ImageView();
        File file = new File("C:/Users/PC/Downloads/images.jpg");



        Circle circle=new Circle();
        circle.setRadius(11);
        chatImage.setImage(staticImage.getImage());
        chatImage.setFitHeight(35);
        chatImage.maxHeight(35);
        chatImage.setFitWidth(35);
        chatImage.maxWidth(35);
        // chatImage.setPreserveRatio(true);
        return circle;
    }

    private Label messageChatCSS(String color) {
        Label msg = new Label();
        msg.setId("msglabel");
        msg.setStyle("-fx-background-color: " + color + "; -fx-font-size: 15; -fx-background-radius: 3;");
        msg.setPadding(new Insets(5, 5, 5, 5));
        msg.setWrapText(true);
        msg.setTextAlignment(TextAlignment.JUSTIFY);
        msg.setMaxWidth(250);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        msg.setText(msgTextField.getText()+"\n["+sdf.format(timestamp)+"]");


        return msg;
    }

    private void chatComponent(Pos p) {
        HBox hbox = new HBox();
        hbox.setAlignment(p);
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.getChildren().addAll(messageChatCSS("#F5F7FB"), imageChatCSS());

        msgvBox.getChildren().add(hbox);
        msgTextField.clear();
    }


    public  void setReciverName(String name)
    {
        recieverNameText.setText(name);
    }


}
