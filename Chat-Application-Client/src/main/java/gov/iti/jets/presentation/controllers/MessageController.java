package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.MessageDto;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

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
    HBox f = null;
    public static MessageController messageController;

    public MessageController() {
        messageController = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        staticImage = new ImageView();

    }

    @FXML
    private void sendAction(MouseEvent event) {

        send();

    }

    public void send() {
        MessageDto msg = new MessageDto();
        msg.setReciver(recieverNameText.getText());
        msg.setSender(ModelsFactory.getInstance().getCurrentUserModel().getPhoneNumber());
        msg.setMessage(msgTextField.getText());

        try {
            RMIConnection.getServerServive().send(msg);
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/sendMsg.fxml"));
            SendMsgController controller = new SendMsgController(msgTextField.getText(), recieve);
            loader.setController(controller);
            HBox hbox = loader.load();

            msgvBox.getChildren().add(hbox);
        } catch (IOException e) {

            e.printStackTrace();
        }

        msgTextField.clear();
    }

    public void setReciverName(String name) {
        recieverNameText.setText(name);
    }

}
