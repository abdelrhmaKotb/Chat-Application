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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import gov.iti.jets.presentation.utils.chatBot;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        //  Parent root = FXMLLoader.load(getClass().getResource("/views/MainPanelView.fxml"));
        // DBConnecttion.getConnection();
        // Scene sc = new Scene(root, 800, 600);
        // primaryStage.setScene(sc);
        // primaryStage.setTitle("Chat Application");
        // primaryStage.show();
        // System.out.println(chatBot.getMessageFromChatBot("hello"));
        // System.out.println(chatBot.getMessageFromChatBot("how old are you"));
        // System.out.println(chatBot.getMessageFromChatBot("i live in cairo"));
        // System.out.println(chatBot.getMessageFromChatBot("i love you"));
        // System.out.println(chatBot.getMessageFromChatBot("hello"));
        // Parent root = FXMLLoader.load(getClass().getResource("/views/MainPanelView.fxml"));
        // DBConnecttion.getConnection();
        // Scene sc = new Scene(root, 800, 600);
        // primaryStage.setScene(sc);
        // primaryStage.setTitle("Chat Application");
        // primaryStage.show();
        // System.out.println(chatBot.getMessageFromChatBot("hello"));
        // System.out.println(chatBot.getMessageFromChatBot("how old are you"));
        // System.out.println(chatBot.getMessageFromChatBot("i live in cairo"));
        // System.out.println(chatBot.getMessageFromChatBot("i love you"));
        // System.out.println(chatBot.getMessageFromChatBot("hello"));

        // System.out.println(chatBot.getMessageFromChatBot("hello my name yassin"));

        // primaryStage.setTitle("Admin Panel");
        // Parent root = FXMLLoader.load(getClass().getResource("/views/MainPanelView.fxml"));
        // primaryStage.setScene(new Scene(root, 850, 600));
        // primaryStage.show();

    }

    public static void main(String[] args) {
        RMIConnection rmi = RMIConnection.getInstance();
        DBConnecttion.getConnection();
        rmi.connect();
        Application.launch(args);
    }
}
