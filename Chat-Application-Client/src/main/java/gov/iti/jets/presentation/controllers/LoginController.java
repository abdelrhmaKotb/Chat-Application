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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private PasswordField lblPassword;

    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    @FXML
    private ImageView imgErrorPhoneNumber;

    @FXML
    private ImageView imgErrorPassword;

    @FXML
    private Label lblErrorPhoneNumber;

    @FXML
    private Label lblErrorPassword;

    @FXML
    private Label lblErrorOrSucessLogin;


    @FXML
    private void handelLogin() throws IOException {

     

         if (txtPhoneNumber.getText().trim().equals(""))
            System.exit(0);

         if (lblPassword.getText().trim().equals("")){
                    lblErrorPassword.setOpacity(1);
                    imgErrorPassword.setOpacity(1);

         }

        if (!txtPhoneNumber.getText().trim().equals("") && !lblPassword.getText().trim().equals("")) {
            LoginService loginService = new LoginService();
            UserDto user = loginService.login(txtPhoneNumber.getText().trim(), lblPassword.getText().trim());

            if (user == null) {
                lblErrorPassword.setOpacity(0);
                lblErrorPhoneNumber.setOpacity(0);
                imgErrorPhoneNumber.setOpacity(0);
                imgErrorPassword.setOpacity(0);
                lblErrorOrSucessLogin.setOpacity(1);
                lblErrorOrSucessLogin.setText("Incorrect phonenumber or password");

            } else {
                lblErrorPassword.setOpacity(0);
                imgErrorPassword.setOpacity(0);
                lblErrorOrSucessLogin.setOpacity(1);
                lblErrorOrSucessLogin.setStyle("-fx-text-fill:green");
             //   lblErrorOrSucessLogin.setText("Login Success");

             Stage stage = (Stage) btnSignin.getScene().getWindow();
             stage.close();
             Stage primarystage = new Stage();
             primarystage.setTitle("Chat");
             Parent root = FXMLLoader.load(getClass().getResource("/views/chat.fxml"));
             primarystage.setScene(new Scene(root, 800, 600));
             primarystage.show();
     
            }
        }
    }

    @FXML
    public void handleSignup() throws IOException {

        Stage stage = (Stage) btnSignin.getScene().getWindow();
        stage.close();
        Stage primarystage = new Stage();
        primarystage.setTitle("Sign Up");
        Parent root = FXMLLoader.load(getClass().getResource("/views/signup.fxml"));
        primarystage.setScene(new Scene(root, 800, 600));
        primarystage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

  
}
