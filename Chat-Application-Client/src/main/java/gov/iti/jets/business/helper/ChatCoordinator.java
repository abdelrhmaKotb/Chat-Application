package gov.iti.jets.business.helper;

import java.util.HashMap;
import java.util.Map;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.GroupDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.presentation.controllers.MessageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class ChatCoordinator {

    private static ChatCoordinator instance = null;
    private static VBox grid = null;
    private static ChatData currentChat = null;

    private static Map<String, ChatData> chats = new HashMap<>();
    // private static Map<Integer, GroupChatData> groupChats = new HashMap<>();
    private static String currentPhone;

    public static boolean isIsGroup() {
        return isGroup;
    }

    private static boolean isGroup;

    private ChatCoordinator() {
    }

    public static ChatCoordinator getInstance() {
        if (instance == null) {
            instance = new ChatCoordinator();
            return instance;
        }

        return instance;
    }

    public static String getCurrentPhone() {
        return currentPhone;
    }

    public void setGrid(VBox gridd) {
        if (grid == null) {
            grid = gridd;
        }
    }

    public void openHome(String phone) {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }
            currentPhone = "";
            System.out.println(grid.getChildren().isEmpty());

            if (!chats.containsKey(phone)) {
                try {
                    isGroup = false;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/home.fxml"));
                    // MessageController c = new MessageController();

                    // loader.setController(c);
                    Parent view = loader.load();
                    // c.setReciverName(phone);
                    ChatData chatData = new ChatData(loader, view, phone, false);
                    currentChat = chatData;
                    chats.put(phone, chatData);
                    grid.getChildren().add(view);

                    System.out.println("new chat");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                var chat = chats.get(phone);
                Parent view = chat.getView();
                currentChat = chat;
                grid.getChildren().add(view);
                System.out.println("old chat");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openChat(String phone) {
        System.out.println("phone in openChat " + phone);
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());

            }
            currentPhone = phone;
            System.out.println(grid.getChildren().isEmpty());

            if (!chats.containsKey(phone)) {
                try {
                    isGroup = false;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/message.fxml"));
                    MessageController c = new MessageController();

                    loader.setController(c);
                    Parent view = loader.load();
                    c.setReciverName(phone);
                    ChatData chatData = new ChatData(loader, view, phone, false);
                    currentChat = chatData;
                    chats.put(phone, chatData);
                    grid.getChildren().add(view);

                    System.out.println("new chat");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                var chat = chats.get(phone);
                Parent view = chat.getView();
                currentChat = chat;
                grid.getChildren().add(view);
                System.out.println("old chat");
            }

            System.out.println("chats" + chats);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createChat(String phone) {
        try {

            if (!chats.containsKey(phone)) {
                try {
                    isGroup = false;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/message.fxml"));
                    MessageController c = new MessageController();

                    loader.setController(c);
                    Parent view = loader.load();

                    UserDto contactDto = ModelsFactory.getInstance().getContactsModel()
                            .getContactDataByNumber(phone);

                    c.setReciverName(phone);

                    // c.setNameText(contactDto.getName());

                    c.setUserDto(contactDto);


                    ChatData chatData = new ChatData(loader, view, phone, false);
                    chats.put(phone, chatData);

                    System.out.println("chat " + phone + " created");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("createion faild");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(chats);
    }

    public void openGroupChat(GroupDto group) {
        try {

            if (!grid.getChildren().isEmpty()) {
                grid.getChildren().removeAll(grid.getChildren());
                System.out.println("remove old chat");
            }

            System.out.println(grid.getChildren().isEmpty());

            if (!chats.containsKey(String.valueOf(group.getId()))) {
                try {
                    isGroup = true;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/message.fxml"));
                    MessageController c = new MessageController();

                    loader.setController(c);
                    Parent view = loader.load();
                    c.setReciverName(group.getName());
                    ChatData chatData = new ChatData(loader, view, String.valueOf(group.getId()), true);
                    currentChat = chatData;
                    chats.put(String.valueOf(group.getId()), chatData);
                    grid.getChildren().add(view);

                    System.out.println("new grup chat" + group.getId());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                var grp = chats.get(String.valueOf(group.getId()));
                Parent view = grp.getView();
                currentChat = grp;
                grid.getChildren().add(view);
                System.out.println("old grup chat" + group.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MessageController getCurrentChatController() {
        return (MessageController) currentChat.getLoader().getController();
    }

    public MessageController getChatController(String phone) {
        if (!chats.containsKey(phone)) {
            System.out.println("controller from created");
            createChat(phone);
        }
        return chats.get(phone).getLoader().getController();
    }

    public String getCurrentChatOpen() {
        return currentChat.getIdntifier();
    }

    public ChatData getCurrentChat() {
        return currentChat;
        // return currentPhone;
    }

}
