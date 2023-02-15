/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.business.rmi.ServerImpl;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.interfaces.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ChatController implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private Text nameText;

    @FXML
    private Text recieverNameText;

    @FXML
    private ImageView callIcon;

    @FXML
    private ImageView videoIcon;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox msgvBox;

    @FXML
    private TextField msgTextField;

    @FXML
    private ImageView textEditorBtn;

    @FXML
    private ImageView attachFileIcon;

    @FXML
    private ImageView sendIcon;

    @FXML
    void SetMessage(MouseEvent event) {

    }

    @FXML
    void attach(MouseEvent event) {

    }

    @FXML
    void sendAction(MouseEvent event) throws RemoteException {

        ServerImpl s=new ServerImpl();
        s.serverAnnoncementNotify(msgTextField.getText().trim());
        msgTextField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
