package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShowInvitationsController implements Initializable {

    @FXML
    private VBox vBoxRoot;
    @FXML
    private Text invitationsLbl;
    @FXML
    private VBox invitationsVBox;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i<20; i++){
            Label lbl = new Label("Invitations"+i);
            invitationsVBox.getChildren().add(lbl);
        }
    }    
    
}
