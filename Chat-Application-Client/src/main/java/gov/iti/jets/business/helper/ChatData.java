package gov.iti.jets.business.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ChatData {
    private FXMLLoader loader;

    private Parent view;

    private static String phoneNumber;
    public ChatData(FXMLLoader loader, Parent view, String phone) {
        this.loader = loader;
        this.view = view;
        phoneNumber=phone;
        System.out.println("phoneeee"+phoneNumber);
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getView() {
        return view;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
