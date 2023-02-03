package gov.iti.jets;

import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import java.rmi.Naming;

public class App extends Application {

    static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {

      //  Parent root = FXMLLoader.load(getClass().getResource("/views/signUpPage.fxml"));
         Parent root = FXMLLoader.load(getClass().getResource("/views/home.fxml"));
        DBConnecttion.getConnection();
        Scene sc = new Scene(root, 800, 600);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Chat Application");
       // primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {

        try {

            client = new ClientImpl();

            Server serverServices = (Server) Naming.lookup("rmi://localhost:14785/serverService");

            serverServices.register(client);

            serverServices.sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Application.launch(args);
    }
}
