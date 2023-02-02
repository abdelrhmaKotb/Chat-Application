package gov.iti.jets;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;

public class ServerImpl extends UnicastRemoteObject implements Server {
    List<Client> clients = new ArrayList<>();

    public ServerImpl() throws RemoteException{
        super();
    }


    public void sayHello()
    {
        System.out.println("hello from server");
    }

    public void register(Client client) throws RemoteException
    {
        System.out.println("register");
        clients.add(client);
        System.out.println(clients);
        client.helloBack();
    }

    public void unregister(Client client) throws RemoteException
    {
        System.out.println("unregister");
        clients.remove(client);
        System.out.println(clients);
    }
 
}
