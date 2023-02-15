package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.iti.jets.business.helper.StageCoordinator;
import gov.iti.jets.business.rmi.RMIConnection;
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
                coordinator.moveToLogin();
            } catch (Exception e) {
                showErrorMessageLabel(errorLabel, IPAddressTextField, "Invalid IP Address or contact the Administrator");
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
