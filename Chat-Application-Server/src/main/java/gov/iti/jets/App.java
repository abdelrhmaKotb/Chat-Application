package gov.iti.jets;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.persistence.utils.DBConnecttion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App  extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

       /*  countryDaoImpl objDao=new countryDaoImpl();
        ArrayList<CountryDto>objCon=di.getCountries();
    
          for(CountryDto cDto:objCon)
             System.out.println(cDto.toString());*/
            
      
      
     
    }
    public static void main(String[] args) {
         RMIConnection rmi = RMIConnection.getInstance();
        DBConnecttion.getConnection();
        rmi.connect();
        Application.launch(args);
    }
}
