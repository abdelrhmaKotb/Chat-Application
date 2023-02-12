
package gov.iti.jets.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPanelController implements Initializable {

    @FXML
    private BorderPane borderPane;

   


  

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/views/charts.fxml"));
            borderPane.setCenter(parent);

        } catch (IOException ex) {
            Logger.getLogger(MainPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 

    @FXML
    private void clear() {
        
        borderPane.setCenter(null);
    }

    

    @FXML
    private void close() throws IOException {

     System.exit(0);
    }

    @FXML
    private void loadPage01View(ActionEvent e) {
    }

    @FXML
    private void loadPage02View(ActionEvent e) {
    }

    
   
   
}
