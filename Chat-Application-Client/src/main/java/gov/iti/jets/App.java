package gov.iti.jets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.entities.Country;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/views/signup.fxml"));
       //  Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        DBConnecttion.getConnection();
       
 
        Scene sc = new Scene(root, 800, 600);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Chat Application");
       // primaryStage.setResizable(false);
        primaryStage.show();

       

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
