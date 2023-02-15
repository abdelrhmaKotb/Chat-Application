package gov.iti.jets.presentation.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.helper.StageCoordinator;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.business.services.LoginService;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.presentation.utils.GeneratePlainPassword;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Esraa
 */
public class IPAddressController implements Initializable {

    @FXML
    private Label IPAddressLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField IPAddressTextField;
    @FXML
    private Button closeBtn;
    @FXML
    private Button connectBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void closeBtn(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void connectToServer(ActionEvent event) {
        if (validateIPAddress(IPAddressTextField.getText())
                || IPAddressTextField.getText().toLowerCase().equals("localhost")) {
            RMIConnection.getInstance().setSERVER_IP(IPAddressTextField.getText());
            try {
                RMIConnection rmi = RMIConnection.getInstance();
                rmi.connect(IPAddressTextField.getText());
                StageCoordinator coordinator = StageCoordinator.getInstance();
                File f = new File("keypassword.txt");
                // boolean p = false;
                if (f.exists()) {
                    // if (p) {
                    try {

                        String[] userData = GeneratePlainPassword.decrypte();

                        System.out.println(userData[0] + "  " + userData[1]);
                        if (!userData[0].equals("") && !userData[1].equals("")) {
                            LoginService loginService = new LoginService();

                            UserDto user = loginService.login(userData[0], userData[1]);

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

                            if (user != null) {
                                coordinator.moveToChat();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    coordinator.moveToLogin();
                }
            } catch (Exception e) {
                showErrorMessageLabel(errorLabel, IPAddressTextField,
                        "Invalid IP Address or contact the Administrator");
                e.printStackTrace();
            }

        } else if (IPAddressTextField.getText().trim().equals("")) {
            showErrorMessageLabel(errorLabel, IPAddressTextField, "Should not be empty");
        } else {
            showErrorMessageLabel(errorLabel, IPAddressTextField, "Invalid IP Address syntax");
        }
    }

    public boolean validateIPAddress(String ip) {
        String IPV4_REGEX = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);

        if (ip == null) {
            return false;
        }

        Matcher matcher = IPv4_PATTERN.matcher(ip);

        return matcher.matches();
    }

    public void showErrorMessageLabel(Label errorName, TextField field, String str) {
        String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
        errorName.setOpacity(1.0);
        field.setStyle(errorStyle);
        errorName.setText(str);

    }

}
