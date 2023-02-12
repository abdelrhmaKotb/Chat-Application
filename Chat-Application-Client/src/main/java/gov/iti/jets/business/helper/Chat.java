package gov.iti.jets.business.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public interface Chat {
    public FXMLLoader getLoader();
    public Parent getView();
    public String getIdntifier();
}
