package gov.iti.jets;

import java.util.ArrayList;
import java.util.Scanner;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.business.rmi.ServerImpl;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.utils.DBConnecttion;
import gov.iti.jets.persistence.utils.chatBot;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gov.iti.jets.persistence.utils.chatBot;


public class App2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/views/MainPanelView.fxml"));
        DBConnecttion.getConnection();
        Scene sc = new Scene(root, 1400, 700);

        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Admin Panel");
        primaryStage.show();

    }


    @Override
    public void stop() throws Exception {
        Platform.exit();
    }

    public static void main(String[] args) {
        RMIConnection rmi = RMIConnection.getInstance();
        DBConnecttion.getConnection();
        rmi.connect();
        Application.launch(args);
    }
}
