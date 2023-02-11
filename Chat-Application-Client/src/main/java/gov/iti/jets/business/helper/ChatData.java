package gov.iti.jets.business.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ChatData {
    private FXMLLoader loader;

    private Parent view;

    public ChatData(FXMLLoader loader, Parent view) {
        this.loader = loader;
        this.view = view;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getView() {
        return view;
    }

}
