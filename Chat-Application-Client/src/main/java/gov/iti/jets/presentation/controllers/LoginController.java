package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.business.services.LoginService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private TextField txtPhonenumber;

    @FXML
    private PasswordField txtPassword;
   
    @FXML
    private Label lblErrorPhoneNumber;

    @FXML
    private Label lblErrorPassword;

    @FXML
    private Label lblErrorLogin;
    
    @FXML
    private Label lblSuccessLogin;
    
    @FXML
    private Button btnSignin;
    
 
    @FXML
    private void handelLogin() throws IOException
    {
        String phoneNumber = txtPhonenumber.getText().trim();
        String password = txtPassword.getText().trim();
        
        if(phoneNumber.trim().equals(""))
            System.exit(0);
   
        if(password.trim().equals(""))
          showErrorMessage(lblErrorPassword, txtPassword);
        
        if(!phoneNumber.trim().equals("")&&!password.trim().equals("")){
                LoginService loginService = new LoginService();
                UserDto user =  loginService.login(phoneNumber, password);

                if(user == null)
                {
              
                    lblErrorPhoneNumber.setOpacity(0);
                    lblErrorPassword.setOpacity(0);
                    showErrorMessage(lblErrorLogin, txtPhonenumber);
                    showErrorMessage(lblErrorLogin, txtPassword);


                }else{
               
                    showSuccessMessage();

                }
         }
    }

    @FXML
    public void handleSignup() throws IOException{

        Stage stage=(Stage) btnSignin.getScene().getWindow();
        stage.close();
        Stage primarystage=new Stage();
        primarystage.setTitle("Sign Up");
        Parent root = FXMLLoader.load(getClass().getResource("/views/signUpPage.fxml"));
        primarystage.setScene(new Scene(root,880,530));
        primarystage.show();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void showErrorMessage(Label errorName,TextField errorStyel){
        String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
        errorName.setOpacity(1.0);
        errorStyel.setStyle(errorStyle);

    }
    public void showSuccessMessage(){

        txtPhonenumber.setStyle("-fx-border-color:GREEN");
        txtPassword.setStyle("-fx-border-color:GREEN");
        lblErrorPhoneNumber.setOpacity(0);
        lblErrorPassword.setOpacity(0);
        lblErrorLogin.setOpacity(0);
        lblSuccessLogin.setOpacity(1);

    }


  
  
}
