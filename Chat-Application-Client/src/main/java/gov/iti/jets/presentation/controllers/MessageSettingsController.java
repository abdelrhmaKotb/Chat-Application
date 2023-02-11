package gov.iti.jets.presentation.controllers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MessageSettingsController implements Initializable {
    Stage stage;
    @FXML
    TextField sampleTextfield;
    @FXML
    ChoiceBox<String> fontStyleChoice;
    @FXML
    ColorPicker fontcolorPicker;
    @FXML
    ColorPicker bgcolorPicker;
    @FXML
    ChoiceBox<Integer> fontSizechoiceBox;
    @FXML
    CheckBox boldCheck;
    @FXML
    CheckBox underlineCheck;
    @FXML
    CheckBox italicCheck;
    String color;
    String bgColor;
    String style;
    int size;
    boolean isBold;
    boolean isUndelined;
    boolean isItalic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        setFontStyle();
        size = 12;
        style = "System";
        bgColor = "#7269EF";
        color = "white";
        List<Integer> fontSize = new ArrayList<>(
                Arrays.asList(10, 11, 12, 13, 14, 18, 24, 28, 36));
        fontSizechoiceBox.getItems().setAll(fontSize);
        fontStyleChoice.getSelectionModel().select("System");
        fontSizechoiceBox.getSelectionModel().select(12);
        fontcolorPicker.setValue(Color.WHITE);
        bgcolorPicker.setValue(Color.web("#7269EF"));
        sampleTextfield.setStyle("-fx-background-color: " + bgColor + ";-fx-text-inner-color: " + color + ";");
        fontStyleChoice.getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue) -> {
            style = fontStyleChoice.getSelectionModel().getSelectedItem().toString();
            sampleTextfield.setFont(Font.font(style, size));
        });
        fontSizechoiceBox.getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue) -> {
            size = Integer.parseInt(fontSizechoiceBox.getSelectionModel().getSelectedItem().toString());
            sampleTextfield.setFont(Font.font(style, size));
        });
       
        fontcolorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                color = "#" + fontcolorPicker.getValue().toString().substring(2, 8);
                sampleTextfield.setStyle("-fx-background-color: " + bgColor + ";-fx-text-inner-color: " + color + ";");

            }
        });
        bgcolorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                bgColor = "#" + bgcolorPicker.getValue().toString().substring(2, 8);
                sampleTextfield.setStyle("-fx-background-color: " + bgColor + ";-fx-text-inner-color: " + color + ";");

            }
        });
        boldCheck.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (boldCheck.isSelected()) {
                    sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, size));
                    isBold = true;
                } else {
                    sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, size));
                    isBold = false;
                }

            }

        });

        italicCheck.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (italicCheck.isSelected()) {
                    sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, FontPosture.ITALIC, size));
                    isItalic = true;
                } else {
                    sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, FontPosture.REGULAR, size));
                    isItalic = false;
                }
            }

        });
        underlineCheck.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (italicCheck.isSelected()) {
                    sampleTextfield.lookup(".text").setStyle("-fx-underline: true;");
                    isUndelined = true;
                } else {
                    sampleTextfield.lookup(".text").setStyle("-fx-underline: false;");
                    isUndelined = false;
                }
            }

        });

    }

    public void setStage(Stage popUp) {
        stage = popUp;
    }

    void setFontStyle() {
        List<String> fontFamilies = Font.getFamilies();
        fontStyleChoice.getItems().setAll(fontFamilies);

    }
}
