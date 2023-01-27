package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.PasswordField;
    
    public class LoginController implements Initializable {
    
        @FXML
        private PasswordField txtPassword;
    
        @FXML
        private Button btnSignin;
    
        @FXML
        private Label lblErrors;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            // TODO Auto-generated method stub
            
        }
    
    
      
}
