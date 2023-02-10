package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import gov.iti.jets.business.services.LoginService;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.helper.StageCoordinator;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.rmi.ClientImpl;
import gov.iti.jets.business.rmi.RMIConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

        if (lblPassword.getText().trim().equals("")) {
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

                CurrentUserModel currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
                currentUserModel.setName(user.getName());
                
                currentUserModel.setPhoneNumber(user.getPhoneNumber());
                System.out.println(currentUserModel.getPhoneNumber() + " sfdf phome");
                currentUserModel.setEmail(user.getEmail());

                // RMIConnection.getServerServive().register(new ClientImpl());
                 RMIConnection.getInstance().registerClient();
                 RMIConnection.getServerServive().notifyUsersOnline(RMIConnection.getInstance().getCurrentClientConnection());
                // currentUserModel.setStatus(user.getStatus());
                 StageCoordinator coordinator = StageCoordinator.getInstance();
                 coordinator.moveToChat();

                 System.out.println(user);
             

            }
        }
    }

    @FXML
    public void handleSignup() throws IOException {
        
        StageCoordinator coordinator = StageCoordinator.getInstance();
        coordinator.moveToSingup();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
