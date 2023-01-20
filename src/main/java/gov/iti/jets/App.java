package gov.iti.jets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text txt = new Text("chat application");
        VBox vb = new VBox(txt);
        Scene sc = new Scene(vb,400,500);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Chat Application");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
