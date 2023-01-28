package gov.iti.jets;
import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

       // Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
         Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        DBConnecttion.getConnection();
        Scene sc = new Scene(root, 880, 530);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
