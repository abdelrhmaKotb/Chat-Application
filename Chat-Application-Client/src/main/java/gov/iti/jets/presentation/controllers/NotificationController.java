package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class NotificationController implements Initializable {

    @FXML
    private VBox contacts;
    @FXML
    private Text notificationsLbl;
    @FXML
    private VBox notificationsVBox;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i<20; i++){
            Label lbl = new Label("Notificatoins"+i);
            notificationsVBox.getChildren().add(lbl);
        }
        
    }    
    
}
