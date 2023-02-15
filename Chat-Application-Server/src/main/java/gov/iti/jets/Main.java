package gov.iti.jets;



import gov.iti.jets.business.rmi.RMIConnection;

import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/views/MainPanelView.fxml"));
        DBConnecttion.getConnection();
        Scene sc = new Scene(root, 800, 600);
        primaryStage.setScene(sc);
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
