package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SendMsgController implements Initializable {
    @FXML
    Label msg;
    @FXML
    Circle circle;
    @FXML
    Text timeText;
    @FXML
    HBox hbox;
    String content;
    boolean recieve;
    public SendMsgController(String message,boolean recieve) {
        content=(message);
        this.recieve=recieve;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
       if(recieve) {
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
       }
       else {
            hbox.setAlignment(Pos.CENTER_RIGHT);
            
       }
        msg.setStyle("-fx-background-color: " + "#F5F7FB" + "; -fx-font-size: 15; -fx-background-radius: 3;");
        msg.setPadding(new Insets(5, 5, 5, 5));
        msg.setWrapText(true);
        msg.setTextAlignment(TextAlignment.JUSTIFY);
        msg.setMaxWidth(250);
        msg.setText(content);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //msg.setText(mDto.getMessage() + "\n[" + sdf.format(timestamp) + "]");
        timeText.setText(sdf.format(timestamp));
    }
    
}
