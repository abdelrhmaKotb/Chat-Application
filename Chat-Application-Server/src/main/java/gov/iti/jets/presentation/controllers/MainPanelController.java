
package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPanelController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button startSerververId;

    @FXML
    private Button stopSerververId;

    @FXML
    private Button logout;

    @FXML
    private Button sendMessage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stopSerververId.setDisable(false);
        startSerververId.setDisable(true);

   /*      Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/views/charts.fxml"));
            borderPane.setCenter(parent);

        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        loadFXML("charts");
    }

    @FXML
    void startHover(DragEvent event) {

    }

    @FXML
    private void clear() {

        borderPane.setCenter(null);
    }

    @FXML
    private void close() throws IOException {

        System.exit(0);
    }

    @FXML
    private void stopServerAction(ActionEvent e) {
        RMIConnection rmi = RMIConnection.getInstance();
        rmi.discConnect();
        stopSerververId.setDisable(true);
        startSerververId.setDisable(false);
    }

    @FXML
    void startServerAction(ActionEvent event) {
        stopSerververId.setDisable(false);
        startSerververId.setDisable(true);
        RMIConnection rmi = RMIConnection.getInstance();
        DBConnecttion.getConnection();
        rmi.reconnect();
    }

    @FXML
    void sendMessageAction(ActionEvent event) {

        loadFXML("chat");

    }

    @FXML
    private void loadFXML(String fileName) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/views/" + fileName + ".fxml"));
            borderPane.setCenter(parent);
           
          

        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
