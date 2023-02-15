package gov.iti.jets.business.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;
import gov.iti.jets.presentation.utils.ShowPopUp;

public class RMIConnection {

    private static RMIConnection instance = null;
    private static Server serverServices = null;
    private static Client currentClientConnection = null;
    private static Registry registry;
    private static String IP = "localhost";

    public  String getSERVER_IP() {
        return IP;
    }

    public  void setSERVER_IP(String sERVER_IP) {
        IP = sERVER_IP;
    }

    private RMIConnection() {
    }

    public static RMIConnection getInstance() {
        if (instance == null) {
            return new RMIConnection();
        }

        return instance;
    }

    public void connect(String ip) throws RemoteException, NotBoundException, MalformedURLException {

        registry = LocateRegistry.getRegistry(14785);
        serverServices = (Server) registry.lookup("rmi://" + ip + ":14785/serverService");
        // if (serverServices == null) {
        // }
    }

    

    public void registerClient(){
        try {
            currentClientConnection = new ClientImpl();
            serverServices.register(currentClientConnection);
        } catch (Exception e) {
            e.printStackTrace();
            new ShowPopUp().notifyServerDown();
        }
    }


    public Client getCurrentClientConnection(){
        return currentClientConnection;
    }


    public static Server getServerServive() {
        return serverServices;
    }

}
