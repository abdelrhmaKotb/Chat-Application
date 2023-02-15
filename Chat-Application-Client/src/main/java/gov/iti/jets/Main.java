package gov.iti.jets;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.helper.StageCoordinator;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.business.services.LoginService;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Client;
// import gov.iti.jets.presentation.utils.chatBot;
import gov.iti.jets.presentation.utils.GeneratePlainPassword;

public class Main extends Application {

    static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageCoordinator coordinator = StageCoordinator.getInstance();
        coordinator.setStage(primaryStage);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new javafx.event.EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    System.out.println("here");
                    ChatCoordinator.getInstance().openChat("home");
                }
            }
        });

        coordinator.moveToIPAddress();

        // primaryStage.setMinWidth(1315);
        // primaryStage.setMaxHeight(915);

        primaryStage.getIcons().add(new Image(getClass().getResource("/images/logo.png").toString()));
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void init() throws Exception {
        // try {

        // RMIConnection rmi = RMIConnection.getInstance();
        // rmi.connect(rmi.getSERVER_IP());
        // // client = new ClientImpl();

        // // Server serverServices = (Server)
        // // Naming.lookup("rmi://localhost:14785/serverService");

        // // serverServices.register(client);

        // // serverServices.sayHello();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

    }

    @Override
    public void stop() throws Exception {
        RMIConnection.getServerServive().unregister(RMIConnection.getInstance().getCurrentClientConnection());
        Platform.exit();
    }
}
