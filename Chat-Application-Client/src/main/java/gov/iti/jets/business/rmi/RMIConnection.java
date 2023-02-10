package gov.iti.jets.business.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import gov.iti.jets.interfaces.Client;
import gov.iti.jets.interfaces.Server;

public class RMIConnection {

    private static RMIConnection instance = null;
    private static Server serverServices = null;
    private static Client currentClientConnection = null;

    private RMIConnection() {
    }

    public static RMIConnection getInstance() {
        if (instance == null) {
            return new RMIConnection();
        }

        return instance;
    }

    public void connect(String ip) throws RemoteException, NotBoundException, MalformedURLException {

        if (serverServices == null) {
            serverServices = (Server) Naming.lookup("rmi://" + ip + ":14785/serverService");
        }
    }

    public void registerClient(){
        try {
            currentClientConnection = new ClientImpl();
            serverServices.register(currentClientConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Client getCurrentClientConnection(){
        return currentClientConnection;
    }


    public static Server getServerServive() {
        return serverServices;
    }

}
