package gov.iti.jets.business.helper;

import java.util.HashMap;
import java.util.Map;

import gov.iti.jets.presentation.controllers.NotificationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class NavCoordinator {
    private static NavCoordinator instance = null;
    private static VBox grid = null;
    private static NavData currentNav = null;

    private static Map<String, NavData> navs = new HashMap<>();

    public static Map<String, NavData> getNavs() {
        return navs;
    }

    private NavCoordinator() {
    }

    public static NavCoordinator getInstance() {
        if (instance == null) {
            instance = new NavCoordinator();
            return instance;
        }

        return instance;
    }

    public void setGrid(VBox gridd) {
        if (grid == null) {
            grid = gridd;
        }
    }

    public void goToContacts() {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }

            System.out.println(grid.getChildren().isEmpty());

            if (!navs.containsKey("contacts")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/contacts.fxml"));

                // ContactsController c = new ContactsController();

                // loader.setController(c);
                Parent view = loader.load();
                NavData navData = new NavData(loader, view);
                currentNav = navData;
                navs.put("contacts", navData);
                grid.getChildren().add(view);

                System.out.println("new chat");

            } else {
                var nav = navs.get("contacts");
                Parent view = nav.getView();
                currentNav = nav;
                grid.getChildren().add(view);
                System.out.println("old chat");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToEditProfile() {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }

            System.out.println(grid.getChildren().isEmpty());

            if (!navs.containsKey("editProfile")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editProfile.fxml"));

                // ContactsController c = new ContactsController();

                // loader.setController(c);
                Parent view = loader.load();
                NavData navData = new NavData(loader, view);
                currentNav = navData;
                navs.put("editProfile", navData);
                grid.getChildren().add(view);

                System.out.println("new editProfile");

            } else {
                var nav = navs.get("editProfile");
                Parent view = nav.getView();
                currentNav = nav;
                grid.getChildren().add(view);
                System.out.println("old editProfile");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToGroups() {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }

            System.out.println(grid.getChildren().isEmpty());

            if (!navs.containsKey("groups")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/groups.fxml"));

                // ContactsController c = new ContactsController();

                // loader.setController(c);
                Parent view = loader.load();
                NavData navData = new NavData(loader, view);
                currentNav = navData;
                navs.put("groups", navData);
                grid.getChildren().add(view);

                System.out.println("new groups");

            } else {
                var nav = navs.get("groups");
                Parent view = nav.getView();
                currentNav = nav;
                grid.getChildren().add(view);
                System.out.println("old groups");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToNotifications() {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }

            System.out.println(grid.getChildren().isEmpty());

            if (!navs.containsKey("notifications")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/notifications.fxml"));

                // ContactsController c = new ContactsController();

                // loader.setController(c);
                Parent view = loader.load();
                NavData navData = new NavData(loader, view);
                currentNav = navData;
                navs.put("notifications", navData);
                grid.getChildren().add(view);

                System.out.println("new notifications");

            } else {
                var nav = navs.get("notifications");
                Parent view = nav.getView();
                currentNav = nav;
                grid.getChildren().add(view);
                System.out.println("old notifications");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToInvitations() {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }

            System.out.println(grid.getChildren().isEmpty());

            if (!navs.containsKey("showInvitations")) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/showInvitations.fxml"));

                // ContactsController c = new ContactsController();

                // loader.setController(c);
                Parent view = loader.load();
                NavData navData = new NavData(loader, view);
                currentNav = navData;
                navs.put("showInvitations", navData);
                grid.getChildren().add(view);

                System.out.println("new showInvitations");

            } else {
                var nav = navs.get("showInvitations");
                Parent view = nav.getView();
                currentNav = nav;
                grid.getChildren().add(view);
                System.out.println("old showInvitations");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public MessageController getCurrentChatController() {
    // return (MessageController) currentChat.getLoader().getController();
    // }

    public static NotificationController getNotificationController() {

        return navs.get("notifications").getLoader().getController();

    }

}
