package gov.iti.jets.business.rmi;

import gov.iti.jets.business.helper.ChatCoordinator;
import gov.iti.jets.business.helper.ModelsFactory;
import gov.iti.jets.business.helper.NavCoordinator;
import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.models.CurrentUserModel;
import gov.iti.jets.dto.ContactDto;
import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Client;
import gov.iti.jets.presentation.utils.ShowPopUp;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client {
    static CurrentUserModel currentUser = ModelsFactory.getInstance().getCurrentUserModel();

    public ClientImpl() throws RemoteException {
    }

    @Override
    public void helloBack() throws RemoteException {
        System.out.println("hello back");
    }

    @Override
    public String getPhoneNumber() throws RemoteException {
        System.out.println(currentUser.getPhoneNumber());
        return currentUser.getPhoneNumber();
    }

    @Override
    public void reciveMessage(MessageDto Message) throws RemoteException {
        System.out.println(Message);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // MessageController.messageController.recive(Message);
                // String r = Message.getReciver();
                // ChatCoordinator.getInstance().getCurrentChatController().recive(Message);
                try {
                    ChatCoordinator.getInstance().getChatController(Message.getSender()).recive(Message);
                    RMIConnection.getServerServive().createMessage(Message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ChatCoordinator.getInstance().getChatController(Message.getReciver()).recive(Message);
            }
        });
    }

    @Override
    public void reciveMessageGroup (MessageDto Message) throws RemoteException {
        System.out.println(Message);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // MessageController.messageController.recive(Message);
                // String r = Message.getReciver();
                // ChatCoordinator.getInstance().getCurrentChatController().recive(Message);
                try {
                    ChatCoordinator.getInstance().getChatController(Message.getReciver()).recive(Message);
                    RMIConnection.getServerServive().createMessage(Message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ChatCoordinator.getInstance().getChatController(Message.getReciver()).recive(Message);
            }
        });
    }

    @Override
    public void userOnlineNotify(ContactDto contact) throws RemoteException {
        // System.out.println(contact.getUser() + " became online");
        ShowPopUp showPopUp = new ShowPopUp();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showPopUp.showNotifacation(contact.getFriendName() + " became online");
                NavCoordinator.getNotificationController()
                        .addInListOfNotifications(contact.getFriendName() + " became online");
                ContactsModel contactsModel = ModelsFactory.getInstance().getContactsModel();
                ContactDto c = new ContactDto();
                contactsModel.getContacts().add(c);
                contactsModel.getContacts().remove(c);

                // var contacts = contactsModel.getContacts();
                // contacts.
                // contacts.get(contacts.indexOf(contact)).setCategory("1");;
            }
        });
    }

    @Override
    public String getName() throws RemoteException {
        return ModelsFactory.getInstance().getCurrentUserModel().getName();
    }

    @Override
    public void userOfflineNotify(ContactDto contact) throws RemoteException {
        // System.out.println(contact.getUser() + " became online");
        ShowPopUp showPopUp = new ShowPopUp();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                showPopUp.showNotifacation(contact.getFriendName() + " became offline");
                NavCoordinator.getNotificationController()
                        .addInListOfNotifications(contact.getFriendName() + " became offline");
                ContactsModel contactsModel = ModelsFactory.getInstance().getContactsModel();
                ContactDto c = new ContactDto();

                contactsModel.getContacts().add(c);
                contactsModel.getContacts().remove(c);

                // var contacts = contactsModel.getContacts();
                // contacts.
                // contacts.get(contacts.indexOf(contact)).setCategory("1");;
            }
        });
    }

    @Override
    public void userNotifyRequest(UserDto user) throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ShowPopUp showPopUp = new ShowPopUp();
                showPopUp.showNotifacation(user.getName() + " Send Friend Request");
                NavCoordinator.getNotificationController()
                        .addInListOfNotifications(user.getName() + " Send Friend Request");
                System.out.println("user" + user + "send to you request");
            }
        });
    }

    @Override
    public void userNotifyAcceptRequest(UserDto user) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ShowPopUp showPopUp = new ShowPopUp();
                showPopUp.showNotifacation(user.getName() + " Accepted Your Request");
                NavCoordinator.getNotificationController()
                        .addInListOfNotifications(user.getName() + " Accepted Your Request");
                var c = new ContactDto(user.getName(), user.getPhoneNumber(), "1", false);
                c.setFriendName(user.getName());
                c.setImage(user.getImage());
                ModelsFactory.getInstance().getContactsModel().getContacts()
                        .add(c);
                System.out.println("user" + user + "send to you request");
            }
        });

    }

    @Override
    public void recieveFile(String fileName, byte[] data) throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

               String path=ChatCoordinator.getInstance().getCurrentChatController().reciveFile(fileName);
                System.out.println(path +"/"+fileName);

                File f = new File(path +"/"+fileName);

                try {
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(f));
                    outputStream.write(data);

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // ChatCoordinator.getInstance().getChatController(Message.getReciver()).recive(Message);
            }
        });


        /*
         * ByteBuffer byteBuffer = ByteBuffer.wrap(data);
         * 
         * try {
         * Path path = Paths.get(fileName);
         * FileChannel channelWrite =FileChannel.open(path,
         * StandardOpenOption.CREATE, StandardOpenOption.APPEND);
         * channelWrite.write(byteBuffer);
         * channelWrite.close();
         * } catch (IOException e) {
         * throw new RuntimeException(e);
         * }
         */

    }

    @Override
    public void userNotifyChangeHisProfile(UserDto contactDto) throws RemoteException {
        System.out.println("new updated Contatc " + contactDto);
        // System.out.println(contact.getUser() + " became online");
        ShowPopUp showPopUp = new ShowPopUp();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ContactsModel contactsModel = ModelsFactory.getInstance().getContactsModel();
                var contacs = contactsModel.getContacts();
                int index = -1;
                for(int i = 0; i < contacs.size(); i++){
                    if(contactDto.getPhoneNumber().equals(contacs.get(i).getFriendPhoneNumber())){
                        index = i;
                        break;
                    }
                }
                showPopUp.showNotifacation( contacs.get(index).getFriendName() + " change his profile data");
                NavCoordinator.getNotificationController()
                        .addInListOfNotifications( contacs.get(index).getFriendName() + " change his profile data");

              

                if(index > -1){
                    System.out.println("updated");
                    contacs.get(index).setFrinMood(contactDto.getStatus());
                    contacs.get(index).setFriendName(contactDto.getName());
                    if(contactDto.getImage() != null){
                        contacs.get(index).setImage(contactDto.getImage());
                    }
                }

                // contactsModel.getContacts().get(contactsModel.getContacts().)
                ContactDto c = new ContactDto();

                contactsModel.getContacts().add(c);
                contactsModel.getContacts().remove(c);

                // var contacts = contactsModel.getContacts();
                // contacts.
                // contacts.get(contacts.indexOf(contact)).setCategory("1");;
            }
        });
    }


    @Override
    public void notifyServerAvaliable() throws RemoteException {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ShowPopUp showPopUp = new ShowPopUp();
                showPopUp.showNotifacation("Server Available Now");
            }
        });

        try {
            RMIConnection.getInstance().connect(RMIConnection.getInstance().getSERVER_IP());
        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }


    @Override
    public void notifyServerNotAvalible() throws RemoteException {
       
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ShowPopUp showPopUp = new ShowPopUp();
                showPopUp.showNotifacation("Server Down Now");
            }
        });
    }


    @Override
    public boolean areYouThere() throws RemoteException {
        return true;
    }


    @Override
    public void serverAnnoncementNotify(String txt) throws RemoteException {
    

          ShowPopUp showPopUp = new ShowPopUp();
          Platform.runLater(new Runnable() {
              @Override
              public void run() {
                  showPopUp.showNotifacation(txt);
                  Media media = new Media(getClass().getResource("/Audio/notification_tone.mp3").toString());

                  // Instantiating MediaPlayer class
                  MediaPlayer mediaPlayer = new MediaPlayer(media);
          
                  // by setting this property to true, the audio will be played
                  mediaPlayer.setAutoPlay(true);
                  NavCoordinator.getNotificationController()
                          .addInListOfNotifications(txt);
  
              }
          });
        
    }
}
