package gov.iti.jets;

import javafx.application.Application;
import javafx.application.Platform;
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

public class App2 extends Application {

    static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageCoordinator coordinator = StageCoordinator.getInstance();
        coordinator.setStage(primaryStage);
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new javafx.event.EventHandler<KeyEvent>
        () {
      
              @Override
              public void handle(KeyEvent t) {
                if(t.getCode()==KeyCode.ESCAPE)
                {
                    System.out.println("here");
                    ChatCoordinator.getInstance().openChat("home");
                }
              }
          });
    

        File f = new File("keypassword.txt");
          boolean p = false;
        // if (f.exists()) {
        if (p) {

            String[] userData = GeneratePlainPassword.decrypte();
            System.out.println(userData[0] + "  " + userData[1]);
            if (!userData[0].equals("") && !userData[1].equals("")) {
                LoginService loginService = new LoginService();

                UserDto user = loginService.login(userData[0], userData[1]);

                CurrentUserModel currentUserModel = ModelsFactory.getInstance().getCurrentUserModel();
                currentUserModel.setName(user.getName());

                currentUserModel.setPhoneNumber(user.getPhoneNumber());
                System.out.println(currentUserModel.getPhoneNumber() + " sfdf phome");
                currentUserModel.setEmail(user.getEmail());
                currentUserModel.setBio(user.getBio());
                currentUserModel.setStatus(user.getStatus().ordinal());
                // System.out.println("image" + Arrays.toString(user.getImage()));
                currentUserModel.setImage(user.getImage());
                currentUserModel.setCountry(user.getCountry());
                currentUserModel.setDate(user.getDateOfBirth().toLocalDate());

                if (user != null)
                    coordinator.moveToChat();
            }

        } else
            coordinator.moveToIPAddress();

        // primaryStage.setMinWidth(1315);
        // primaryStage.setMaxHeight(915);
    
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void init() throws Exception {
        // try {

        //     RMIConnection rmi = RMIConnection.getInstance();
        //     rmi.connect(rmi.getSERVER_IP());
        //     // client = new ClientImpl();

        //     // Server serverServices = (Server)
        //     // Naming.lookup("rmi://localhost:14785/serverService");

        //     // serverServices.register(client);

        //     // serverServices.sayHello();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

    }

    @Override
    public void stop() throws Exception {
        RMIConnection.getServerServive().unregister(RMIConnection.getInstance().getCurrentClientConnection());
        Platform.exit();
    }
}
