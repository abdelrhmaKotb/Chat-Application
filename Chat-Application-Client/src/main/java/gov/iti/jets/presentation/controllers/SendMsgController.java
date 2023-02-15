package gov.iti.jets.presentation.controllers;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ChatData;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.models.GroupsModel;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupsMembersDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.dto.UserDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

import java.io.ByteArrayInputStream;
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
    @FXML
    Text senderName;
    public SendMsgController(MessageDto messageDto, boolean recieve) {
        content = (messageDto.getMessage());
        this.recieve = recieve;
        msgDto = messageDto;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (recieve) {
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            ContactsModel contactsModel=new ContactsModel();
            ChatData chat = ChatCoordinator.getInstance().getCurrentChat();
            UserDto uDto;
            if(!chat.isGroup()) {
                // uDto = contactsModel.getContactDataByNumber(ChatCoordinator.getInstance().getCurrentChatOpen());
                uDto = contactsModel.getContactDataByNumber(msgDto.getSender());
                senderName.setVisible(false);
            }
            else {
                uDto = contactsModel.getContactDataByNumber(msgDto.getSender());
                senderName.setText(uDto.getName());
            }
            circle.setStroke(null);
            Image userImage = new Image(new ByteArrayInputStream(uDto.getImage()));
            circle.setFill(new ImagePattern(userImage));



        } else {
            hbox.setAlignment(Pos.CENTER_RIGHT);
            CurrentUserModel currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
                circle.setStroke(null);
                Image userImage = new Image(new ByteArrayInputStream(currentUserModel.getImage()));
                circle.setFill(new ImagePattern(userImage));

        }

        msg.setPadding(new Insets(5, 5, 5, 5));
        msg.setWrapText(true);
        msg.setTextAlignment(TextAlignment.JUSTIFY);
        msg.setMaxWidth(250);
        msg.setText(content);
        msg.setMaxHeight( Double.MAX_VALUE );
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //msg.setText(mDto.getMessage() + "\n[" + sdf.format(timestamp) + "]");
        timeText.setText(sdf.format(timestamp));
        setMsgFormat();
    }

    void setMsgFormat() {
        ContactDto contactDto;
        ChatData chat = ChatCoordinator.getInstance().getCurrentChat();

            if (!recieve && !chat.isGroup()) {
                ModelsFactory modelsFactory = ModelsFactory.getInstance();
                ContactsModel contactsModel = modelsFactory.getContactsModel();
                contactDto = contactsModel.getContactByPhoneNumber(ChatCoordinator.getInstance().getCurrentChatOpen());

                System.out.println(contactDto + "contactDto");
                System.out.println("-fx-background-color: " + contactDto.getBackgroundColor() + "; -fx-font-size:" + contactDto.getFontSize() +
                "; -fx-background-radius: 3;" + "-fx-text-fill: " + contactDto.getFontColor() + ";" + "-fx-underline:" + contactDto.isUnderlined() + ";");

                msg.setStyle("-fx-background-color: " + contactDto.getBackgroundColor() + "; -fx-font-size:" + contactDto.getFontSize() +
                        "; -fx-background-radius: 3;" + "-fx-text-fill: " + contactDto.getFontColor() + ";" + "-fx-underline:" + contactDto.isUnderlined() + ";");
                if (contactDto.isBold())
                    msg.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, contactDto.getFontSize()));
                else if (contactDto.isItalic() && contactDto.isBold())
                    msg.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, contactDto.getFontSize()));
                else if (contactDto.isItalic())
                    msg.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, contactDto.getFontSize()));
                else
                    msg.setFont(Font.font(contactDto.getFontStyle(), contactDto.getFontSize()));
            }
            else if(!recieve && chat.isGroup()) {
                
                ModelsFactory modelsFactory = ModelsFactory.getInstance();
                GroupsModel groupsModel = modelsFactory.getGroups();
                GroupsMembersDto groupsMembersDto = groupsModel.getGroupByGroup_id(Integer.parseInt(ChatCoordinator.getInstance().getCurrentChatOpen()));
                System.out.println(groupsMembersDto + "groupsMembersDto");

                msg.setStyle("-fx-background-color: " + groupsMembersDto.getBackgroundColor() + "; -fx-font-size:" + groupsMembersDto.getFontSize() +
                        "; -fx-background-radius: 3;" + ";-fx-text-fill: " + groupsMembersDto.getFontColor() + ";" + "-fx-underline:" + groupsMembersDto.isUnderlined() + ";");
                if (groupsMembersDto.isBold())
                    msg.setFont(Font.font(groupsMembersDto.getFontStyle(), FontWeight.BOLD, groupsMembersDto.getFontSize()));
                else if (groupsMembersDto.isItalic() && groupsMembersDto.isBold())
                    msg.setFont(Font.font(groupsMembersDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, groupsMembersDto.getFontSize()));
                else if (groupsMembersDto.isItalic())
                    msg.setFont(Font.font(groupsMembersDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, groupsMembersDto.getFontSize()));
                else
                    msg.setFont(Font.font(groupsMembersDto.getFontStyle(), groupsMembersDto.getFontSize()));
            }
            else {
                msg.setStyle("-fx-background-color: " + msgDto.getBackgroundColor() + "; -fx-font-size:" + msgDto.getFontSize() +
                        "; -fx-background-radius: 3;" + ";-fx-text-fill: " + msgDto.getFontColor() + ";" + "-fx-underline:" + msgDto.isUnderlined() + ";");
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

