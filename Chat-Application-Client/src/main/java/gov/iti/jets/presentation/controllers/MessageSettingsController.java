package gov.iti.jets.presentation.controllers;

import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.services.messageSettingsService;
import gov.iti.jets.dto.ContactDto;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    ContactDto contactDto;

    boolean changed;

    public MessageSettingsController(ContactDto contactDto) {
        this.contactDto = contactDto;
        System.out.println("-----------cont" + contactDto.getFriendPhoneNumber() + contactDto.isBold());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setFontStyle();
        size = contactDto.getFontSize();
        style = contactDto.getFontStyle();
        bgColor = contactDto.getBackgroundColor();
        color = contactDto.getFontColor();

        fontStyleChoice.getSelectionModel().select(contactDto.getFontStyle());
        System.out.println(contactDto.getFontSize());
        fontSizechoiceBox.getSelectionModel().select((Integer) contactDto.getFontSize());
        System.out.println(contactDto.getFontColor());
        fontcolorPicker.setValue(Color.web(contactDto.getFontColor()));
        bgcolorPicker.setValue(Color.web(contactDto.getBackgroundColor()));
        isBold = contactDto.isBold();
        isItalic = contactDto.isItalic();
        isUndelined = contactDto.isUnderlined();
        setTextStyle();
        if (isBold)
            boldCheck.setSelected(true);
        if (isItalic)
            italicCheck.setSelected(true);
        if (isUndelined)
            underlineCheck.setSelected(true);

        sampleTextfield.setStyle("-fx-background-color: " + bgColor + ";-fx-text-inner-color: " + color + ";");
        fontStyleChoice.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                style = fontStyleChoice.getSelectionModel().getSelectedItem().toString();
                contactDto.setFontStyle(style);
                sampleTextfield.setFont(Font.font(style, size));
                changed = true;
            }
        });
        fontSizechoiceBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                size = Integer.parseInt(fontSizechoiceBox.getSelectionModel().getSelectedItem().toString());
                System.out.println(size);
                contactDto.setFontSize(size);
                sampleTextfield.setFont(Font.font(style, size));
                changed = true;

            }});


        fontcolorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                color = "#" + fontcolorPicker.getValue().toString().substring(2, 8);
                contactDto.setFontColor(color);
                changed = true;
                sampleTextfield.setStyle("-fx-background-color: " + bgColor + ";-fx-text-inner-color: " + color + ";");

            }
        });
        bgcolorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                bgColor = "#" + bgcolorPicker.getValue().toString().substring(2, 8);
                contactDto.setBackgroundColor(bgColor);
                changed = true;
                sampleTextfield.setStyle("-fx-background-color: " + bgColor + ";-fx-text-inner-color: " + color + ";");

            }
        });
//        boldCheck.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                if (boldCheck.isSelected()) {
//                    sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, size));
//                    isBold = true;
//                    System.out.println("bold checked");
//                } else {
//                    sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, size));
//                    isBold = false;
//                }
//                contactDto.setBold(isBold);
//                changed = true;
//            }
//
//        });
//
//        italicCheck.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                if (italicCheck.isSelected()) {
//                    if(boldCheck.isSelected()) {
//                        sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, FontPosture.ITALIC, size));
//                    }
//                    else {
//                        sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, FontPosture.ITALIC, size));
//                    }
//                    isItalic = true;
//                } else {
//                    if(boldCheck.isSelected()) {
//                        sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, FontPosture.REGULAR, size));
//                    }
//                    else {
//                        sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, FontPosture.ITALIC, size));
//                    }
//                    isItalic = false;
//                }
//                contactDto.setItalic(isItalic);
//                changed = true;
//            }
//
//        });
//        underlineCheck.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                if (underlineCheck.isSelected()) {
//                    sampleTextfield.lookup(".text").setStyle("-fx-underline: true;");
//                    isUndelined = true;
//                } else {
//                    sampleTextfield.lookup(".text").setStyle("-fx-underline: false;");
//                    isUndelined = false;
//                }
//                contactDto.setUnderlined(isUndelined);
//                changed = true;
//            }
//
//        });

    }

    public void setStage(Stage popUp) {
        stage = popUp;
    }

    void setFontStyle() {
        List<String> fontFamilies = Font.getFamilies();
        fontStyleChoice.getItems().setAll(fontFamilies);
        List<Integer> fontSize = new ArrayList<>(
                Arrays.asList(10, 11, 12, 13, 14, 18, 24, 28));
        fontSizechoiceBox.getItems().setAll(fontSize);

    }
    public void setTextStyle() {
        sampleTextfield.setStyle("-fx-background-color: " + contactDto.getBackgroundColor() + "; -fx-font-size:" + contactDto.getFontSize() +
                "; -fx-background-radius: 3;" + ";-fx-text-inner-color: " + contactDto.getFontColor() + ";" );
        if (contactDto.isBold())
            sampleTextfield.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, contactDto.getFontSize()));
        else if (contactDto.isItalic() && contactDto.isBold())
            sampleTextfield.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, contactDto.getFontSize()));
        else if (contactDto.isItalic())
            sampleTextfield.setFont(Font.font(contactDto.getFontStyle(), FontWeight.BOLD, FontPosture.ITALIC, contactDto.getFontSize()));
        else
            sampleTextfield.setFont(Font.font(contactDto.getFontStyle(), contactDto.getFontSize()));


    }

    @FXML
    public void save(MouseEvent mouseEvent) {
        System.out.println("save");


            messageSettingsService settingsService = new messageSettingsService();
            settingsService.msgSettings(contactDto);
            System.out.println("SAVED");
            ModelsFactory modelsFactory = ModelsFactory.getInstance();
            ContactsModel contactsModel = modelsFactory.getContactsModel();
            contactsModel.editContact(contactDto);
            Stage popup = new Stage();
            // configure UI for popup etc...

            // hide popup after 3 seconds:
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> popup.hide());
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/edited.fxml"));
                popup.setScene(new Scene(root));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            popup.show();
            delay.play();



    }
    public void checkboxs(ActionEvent e) {

                if (boldCheck.isSelected()) {
                    sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, size));
                    isBold = true;
                    System.out.println("bold checked");
                } else {
                    sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, size));
                    isBold = false;
                }
                contactDto.setBold(isBold);
                changed = true;




                if (italicCheck.isSelected()) {
                    if(boldCheck.isSelected()) {
                        sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, FontPosture.ITALIC, size));
                    }
                    else {
                        sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, FontPosture.ITALIC, size));
                    }
                    isItalic = true;
                } else {
                    if(boldCheck.isSelected()) {
                        sampleTextfield.setFont(Font.font(style, FontWeight.BOLD, FontPosture.REGULAR, size));
                    }
                    else {
                        sampleTextfield.setFont(Font.font(style, FontWeight.NORMAL, FontPosture.ITALIC, size));
                    }
                    isItalic = false;
                }
                contactDto.setItalic(isItalic);
                changed = true;


                if (underlineCheck.isSelected()) {
                    sampleTextfield.lookup(".text").setStyle("-fx-underline: true;");
                    isUndelined = true;
                } else {
                    sampleTextfield.lookup(".text").setStyle("-fx-underline: false;");
                    isUndelined = false;
                }
                contactDto.setUnderlined(isUndelined);
                changed = true;



    }
}
