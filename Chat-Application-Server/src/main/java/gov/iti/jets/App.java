package gov.iti.jets;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.stage.Stage;

public class App  extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    public static void main(String[] args) {
        RMIConnection rmi = RMIConnection.getInstance();
        DBConnecttion.getConnection();
        rmi.connect();
        Application.launch(args);
    }
}
