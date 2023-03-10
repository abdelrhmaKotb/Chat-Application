package gov.iti.jets.presentation.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;
// import java.util.Date;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import gov.iti.jets.business.services.LoginService;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.presentation.utils.GenerateEncryptionPassword;
import gov.iti.jets.presentation.utils.GeneratePlainPassword;
import gov.iti.jets.presentation.validation.SignUpValidation;
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
    private void handelLogin() throws IOException, InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        if (txtPhoneNumber.getText().trim().equals(""))
            System.exit(0);

        if (lblPassword.getText().trim().equals("")) {
            lblErrorPassword.setOpacity(1);
            imgErrorPassword.setOpacity(1);

        }

        if (!txtPhoneNumber.getText().trim().equals("") && !lblPassword.getText().trim().equals("")) {
            LoginService loginService = new LoginService();
            UserDto user = loginService.login(txtPhoneNumber.getText().trim(), lblPassword.getText().trim());
            // lblPassword.setText("");
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
                currentUserModel.setBio(user.getBio());
                currentUserModel.setStatus(user.getStatus().ordinal());
                // System.out.println("image" + Arrays.toString(user.getImage()));
                currentUserModel.setImage(user.getImage());
                currentUserModel.setCountry(user.getCountry());
                currentUserModel.setDate(user.getDateOfBirth().toLocalDate());
                // RMIConnection.getServerServive().register(new ClientImpl());
                RMIConnection.getInstance().registerClient();
                RMIConnection.getServerServive()
                        .notifyUsersOnline(RMIConnection.getInstance().getCurrentClientConnection());
                // currentUserModel.setStatus(user.getStatus());
                GenerateEncryptionPassword.encrypte(txtPhoneNumber.getText().trim(), lblPassword.getText().trim());

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
        txtPhoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            isValidPhoneNumber();
            // lblErrorOrSucessLogin.setOpacity(0);

        });
    }

    public boolean isValidPhoneNumber() {
        boolean flag = false;
        SignUpValidation validation = new SignUpValidation();
        if (validation.validatePhoneNumber(txtPhoneNumber.getText().trim()).equals("invalid phone")) {
            flag = false;
            lblPassword.setDisable(true);
            lblErrorOrSucessLogin.setOpacity(1);
            lblPassword.setPromptText("");
            lblErrorOrSucessLogin.setText("Incorrect phonenumber");
        } else {
            flag = true;
            lblErrorOrSucessLogin.setOpacity(0);
            lblPassword.setDisable(false);
            lblPassword.setPromptText("Enter Password");
        }
        return flag;
    }

}
