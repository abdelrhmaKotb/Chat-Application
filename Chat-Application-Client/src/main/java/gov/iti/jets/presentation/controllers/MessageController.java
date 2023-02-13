package gov.iti.jets.presentation.controllers;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ChatData;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.models.GroupsModel;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

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
    String messageContent;
    Parent root;

    public static MessageController messageController;

    public MessageController() {
        messageController = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { 

        staticImage = new ImageView();
        msgTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    System.out.println("enter");
                    send();
                }
            }
        });

    }

    @FXML
    private void sendAction(MouseEvent event) {

        send();

    }

    public void send() {

        ChatData chat = ChatCoordinator.getInstance().getCurrentChat();

        // msg.setReciver(chat.getIdntifier());

        MessageDto msg=null;

        try {
            if(chat.isGroup()){

                ModelsFactory modelsFactory = ModelsFactory.getInstance();
                GroupsModel groupsModel = modelsFactory.getGroups();
                GroupsMembersDto groupsMembersDto = groupsModel.getGroupByGroup_id(Integer.parseInt(ChatCoordinator.getInstance().getCurrentChatOpen()));
                 msg = new MessageDto(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber(),
                        msgTextField.getText(), groupsMembersDto.getFontSize(), groupsMembersDto.getFontStyle(), groupsMembersDto.getFontColor(),
                        groupsMembersDto.getBackgroundColor(), groupsMembersDto.isBold(), groupsMembersDto.isUnderlined(), groupsMembersDto.isItalic(), chat.getIdntifier());
                // msg.setReciver(chat);
                msg.setSender(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
                msg.setMessage(msgTextField.getText());
                RMIConnection.getServerServive().sendGroupMessage(msg);
            }else{

                ModelsFactory modelsFactory = ModelsFactory.getInstance();
                ContactsModel contactsModel = modelsFactory.getContactsModel();
                ContactDto contactDto = contactsModel.getContactByPhoneNumber(ChatCoordinator.getInstance().getCurrentChatOpen());
                 msg = new MessageDto(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber(),
                        msgTextField.getText(), contactDto.getFontSize(), contactDto.getFontStyle(), contactDto.getFontColor(),
                        contactDto.getBackgroundColor(), contactDto.isBold(), contactDto.isUnderlined(), contactDto.isItalic(), chat.getIdntifier());
                // msg.setReciver(chat);
                msg.setSender(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
                msg.setMessage(msgTextField.getText());
                RMIConnection.getServerServive().send(msg);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        chatComponent(false, msg);
    }

    public void recive(MessageDto mDto) {
        chatComponent(true, mDto);
    }

    public void setRecievedMsg(String msg) {

    }

    private void chatComponent(Boolean recieve, MessageDto messageDto) {

        try {
            if (messageDto.getMessage() != "") {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/sendMsg.fxml"));
                SendMsgController controller = new SendMsgController(messageDto, recieve);
                loader.setController(controller);
                HBox hbox = loader.load();

                msgvBox.getChildren().add(hbox);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

        msgTextField.clear();
    }

    public void setReciverName(String name) {
        recieverNameText.setText(name);
    }

    @FXML
    private void SetMessage(MouseEvent event) {
        // MessageSettingsController createGroupCont = null;

        System.out.println(ChatCoordinator.getInstance().getCurrentChatOpen());
        ModelsFactory modelsFactory = ModelsFactory.getInstance();
        ContactsModel contactsModel = modelsFactory.getContactsModel();
        ChatData chatData = ChatCoordinator.getInstance().getCurrentChat();
        ContactDto dto = null;
        GroupsModel groupsModel=new GroupsModel();
        GroupsMembersDto g=groupsModel.getGroupByGroup_id(1);
        System.out.println("--------------");
        System.out.println("------"+ChatCoordinator.getInstance().getCurrentChatOpen());

        if(chatData.isGroup()) {
            groupMessageSettingsController messageSettingsController=null;
            System.out.println("--------------");

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/messageSettings.fxml"));
                messageSettingsController = new groupMessageSettingsController(g);
                fxmlLoader.setController(messageSettingsController);
                root = fxmlLoader.load();
                // createGroupCont = fxmlLoader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Message settings");
            messageSettingsController.setStage(stage);
            Scene scene1 = new Scene(root, 501, 400);
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.showAndWait();
        }
        else {
            MessageSettingsController messageSettingsController = null;
            dto = contactsModel.getContactByPhoneNumber(ChatCoordinator.getInstance().getCurrentChatOpen());
            System.out.println(dto.getFontColor());
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/messageSettings.fxml"));
                messageSettingsController = new MessageSettingsController(dto);
                fxmlLoader.setController(messageSettingsController);
                root = fxmlLoader.load();
                // createGroupCont = fxmlLoader.getController();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Message settings");
            messageSettingsController.setStage(stage);
            Scene scene1 = new Scene(root, 501, 400);
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.showAndWait();
        }
    }

}
