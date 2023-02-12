package gov.iti.jets.presentation.controllers;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.MessageDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

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
    MessageDto msgDto;

    public SendMsgController(MessageDto messageDto, boolean recieve) {
        content = (messageDto.getMessage());
        this.recieve = recieve;
        msgDto = messageDto;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        if (recieve) {
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
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
        setMsgFormat();
    }

    void setMsgFormat() {
        ContactDto contactDto;
        if (!recieve) {
            ModelsFactory modelsFactory = ModelsFactory.getInstance();
            ContactsModel contactsModel = modelsFactory.getContactsModel();
            contactDto = contactsModel.getContactByPhoneNumber(ChatCoordinator.getInstance().getCurrentChatOpen());

            msg.setStyle("-fx-background-color: " + contactDto.getBackgroundColor() + "; -fx-font-size:" + contactDto.getFontSize() +
                    "; -fx-background-radius: 3;" + ";-fx-text-inner-color: " + contactDto.getFontColor() + ";" + "-fx-underline:" + contactDto.isUnderlined() + ";");
            if (contactDto.isBold())
                msg.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, contactDto.getFontSize()));
            else if (contactDto.isItalic() && contactDto.isBold())
                msg.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, contactDto.getFontSize()));
            else if (contactDto.isItalic())
                msg.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, contactDto.getFontSize()));
            else
                msg.setFont(Font.font(contactDto.getFontStyle(), contactDto.getFontSize()));
        } else {
            msg.setStyle("-fx-background-color: " + msgDto.getBackgroundColor() + "; -fx-font-size:" + msgDto.getFontSize() +
                    "; -fx-background-radius: 3;" + ";-fx-text-inner-color: " + msgDto.getFontColor() + ";" + "-fx-underline:" + msgDto.isUnderlined() + ";");
            if (msgDto.isBold())
                msg.setFont(Font.font(msgDto.getFontStyle(), FontWeight.BOLD, msgDto.getFontSize()));
            else if (msgDto.isItalic() && msgDto.isBold())
                msg.setFont(Font.font(msgDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, msgDto.getFontSize()));
            else if (msgDto.isItalic())
                msg.setFont(Font.font(msgDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, msgDto.getFontSize()));
            else
                msg.setFont(Font.font(msgDto.getFontStyle(), msgDto.getFontSize()));
        }

    }
}

