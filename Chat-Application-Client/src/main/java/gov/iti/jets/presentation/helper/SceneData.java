package gov.iti.jets.presentation.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneData {
    private FXMLLoader loader;

    private Parent view;

    private Scene scene;

    public SceneData(FXMLLoader loader, Parent view, Scene scene) {
        this.loader = loader;
        this.view = view;
        this.scene = scene;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Scene getScene() {
        return scene;
    }

    public Parent getView() {
        return view;
    }

}
