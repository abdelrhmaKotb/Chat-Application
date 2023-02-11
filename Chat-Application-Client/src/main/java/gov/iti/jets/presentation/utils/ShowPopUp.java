package gov.iti.jets.presentation.utils;

import gov.iti.jets.business.helper.StageCoordinator;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowPopUp {
    public void showNotifacation(String msg) {
        Label lbl = new Label(msg);
        lbl.setId("notification");
        StageCoordinator coordinator = StageCoordinator.getInstance();
        StackPane stackPane = new StackPane(lbl);
        Scene popupScene = new Scene(stackPane, 200, 50);
        popupScene.getStylesheets().add(getClass().getResource("/css/stylePopUp.css").toExternalForm());
        Stage popupStage = new Stage();
        popupStage.setX(coordinator.getStage().getWidth()-200 );
        popupStage.setY(coordinator.getStage().getHeight()-100);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.setScene(popupScene);
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(coordinator.getStage());
        popupStage.show();

        Thread ts = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Platform.runLater(() -> {
                        popupStage.close();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ts.start();
    }
}
