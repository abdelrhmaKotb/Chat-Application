package gov.iti.jets.business.helper;

import java.util.HashMap;
import java.util.Map;

import gov.iti.jets.presentation.controllers.MessageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ChatCoordinator {

    private static ChatCoordinator instance = null;
    private static VBox grid = null;

    private static Map<String, ChatData> chats = new HashMap<>();

    private ChatCoordinator() {
    }

    public static ChatCoordinator getInstance() {
        if (instance == null) {
            return new ChatCoordinator();
        }

        return instance;
    }

    public void setGrid(VBox gridd) {
        if (grid == null) {
            grid = gridd;
        }
    }

    public void openChat(String phone) {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }

            System.out.println(grid.getChildren().isEmpty());

            if (!chats.containsKey(phone)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/message.fxml"));
                    MessageController c = new MessageController();

                    loader.setController(c);
                    Parent view = loader.load();
                    c.setReciverName(phone);
                    ChatData chatData = new ChatData(loader, view);
                    chats.put(phone, chatData);
                    grid.getChildren().add(view);

                    System.out.println("new chat");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Parent view = chats.get(phone).getView();
                grid.getChildren().add(view);
                System.out.println("old chat");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
