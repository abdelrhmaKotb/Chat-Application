package gov.iti.jets;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

import gov.iti.jets.business.helper.StageCoordinator;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.interfaces.Client;
// import gov.iti.jets.presentation.utils.chatBot;
import gov.iti.jets.presentation.utils.GenerateEncryptionPassword;
import gov.iti.jets.presentation.utils.GeneratePlainPassword;


public class App extends Application {

    static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
         StageCoordinator coordinator = StageCoordinator.getInstance();
        coordinator.setStage(primaryStage);
        
        File f = new File("D:\\Group3_Chatting Application\\Group3_Chatting Application\\Group3_Chatting Application\\github\\Chat-Application\\Chat-Application-Client\\keypassword.txt");

        if (f.exists())

        coordinator.moveToChat();

        else
        coordinator.moveToLogin();

        
        primaryStage.setMinWidth(1315);
        primaryStage.setMaxHeight(915);
        primaryStage.show();
    //    GenerateEncryptionPassword.encrypte("01147775184","Yassin@22");
    //System.out.println(GeneratePlainPassword.decrypte());
      

    }

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void init() throws Exception {
        try {

            RMIConnection rmi = RMIConnection.getInstance();
            rmi.connect("localhost");
            // client = new ClientImpl();

            // Server serverServices = (Server) Naming.lookup("rmi://localhost:14785/serverService");

            // serverServices.register(client);

            // serverServices.sayHello();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() throws Exception {
        RMIConnection.getServerServive().unregister(RMIConnection.getInstance().getCurrentClientConnection());
    }
}
