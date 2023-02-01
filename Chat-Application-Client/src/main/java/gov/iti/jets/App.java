package gov.iti.jets;

import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/views/test.fxml"));
        // Parent root =
        FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        DBConnecttion.getConnection();
        Scene sc = new Scene(root, 855, 503);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Chat Application");
        // primaryStage.setResizable(false);
        primaryStage.show();

        // Label label = new Label("Educational qualification:");
        // Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        // label.setFont(font);
        // // list View for educational qualification
        // ObservableList<String> names = FXCollections.observableArrayList("Engineering", "MCA", "MBA", "Graduation",
        //         "MTECH", "Mphil", "Phd");
        // ListView<String> listView = new ListView<String>(names);
        // listView.setMaxSize(200, 160);
        // // Creating the layout
        // VBox layout = new VBox(10);
        // layout.setPadding(new Insets(5, 5, 5, 50));
        // layout.getChildren().addAll(label, listView);
        // layout.setStyle("-fx-background-color: BEIGE");
        // // Setting the stage
        // Scene scene = new Scene(layout, 595, 200);
        // stage.setTitle("List View Example");
        // stage.setScene(scene);
        // stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
