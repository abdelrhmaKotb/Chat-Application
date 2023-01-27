package gov.iti.jets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/views/signUpPage.fxml"));
 //    Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
     //   Parent root = FXMLLoader.load(getClass().getResource("/views/Phone.fxml"));
        Scene sc = new Scene(root,855,503);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Chat Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
