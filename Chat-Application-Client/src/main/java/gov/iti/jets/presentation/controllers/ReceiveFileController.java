package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ReceiveFileController implements Initializable {
    Stage stage;
    @FXML
    Button rejectBtn;
    @FXML
    Button acceptbtn;
    static String path;
    @FXML
    Label fileNamelbl;
    @FXML
    Label fromLbl;
    public  static String getPath() {
        return path;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileNamelbl.setText(MessageController.getFileName());
    }

    public void setStage(Stage popUp) {
        stage = popUp;
    }

    @FXML
    public void accept(MouseEvent mouseEvent) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("c:\\"));
        Stage stage = (Stage) acceptbtn.getScene().getWindow();


        File selectedDirectory = directoryChooser.showDialog(stage);


        path=selectedDirectory.getAbsolutePath();
        System.out.println(path);
        stage.close();
    }

    @FXML
    public void reject(MouseEvent mouseEvent) {
        Stage stage = (Stage) rejectBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
