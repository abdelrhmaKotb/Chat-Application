package gov.iti.jets.business.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ChatData {
    private FXMLLoader loader;

    private Parent view;

    private  String idintifer;

    private boolean isGroup;

    public boolean isGroup() {
        return isGroup;
    }

    public ChatData(FXMLLoader loader, Parent view, String idintifer,boolean isGroup) {
        this.loader = loader;
        this.view = view;
        this.idintifer=idintifer;
        this.isGroup = isGroup;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getView() {
        return view;
    }
    public String getIdntifier() {
        return idintifer;
    }

}
