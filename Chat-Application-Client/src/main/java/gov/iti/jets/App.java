package gov.iti.jets;

import javafx.application.Application;
import javafx.stage.Stage;
import gov.iti.jets.business.helper.StageCoordinator;
import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.interfaces.Client;


public class App extends Application {

    static Client client;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageCoordinator coordinator = StageCoordinator.getInstance();
        coordinator.setStage(primaryStage);
        coordinator.moveToLogin();
        primaryStage.show();

    }

    public static void main(String[] args) {
     Application.launch(args);
       


    }

    @Override
    public void init() throws Exception {
        try {

            RMIConnection rmi = RMIConnection.getInstance();
//            rmi.connect("10.145.18.76");
            //  rmi.connect("10.145.17.169");
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
