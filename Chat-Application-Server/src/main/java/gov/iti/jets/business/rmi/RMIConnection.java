package gov.iti.jets.business.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIConnection {

    private static RMIConnection instance = null;
    private static ServerImpl server = null;
    private static Registry registry;

    private RMIConnection() {
    }

    public static RMIConnection getInstance() {
        if (instance == null) {
            instance = new RMIConnection();
            return instance;
        }

        return instance;
    }

    public void connect() {

        try {
            // if (server == null && registry == null) {
            // System.out.println("get");
            // } else {

            // System.out.println("create");
            // registry = LocateRegistry.getRegistry(14785);
            // }

            // // try {
            // // System.out.println("get");
            // // } catch (Exception e) {
            // // System.out.println("create");
            // // }

            server = new ServerImpl();
            registry = LocateRegistry.createRegistry(14785);
            registry.rebind("rmi://localhost:14785/serverService", server);

            System.out.println("server Running ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reconnect() {

        try {
            // server = new ServerImpl();
            // registry = LocateRegistry.getRegistry(14785);
            registry.rebind("rmi://localhost:14785/serverService", server);
            UnicastRemoteObject.exportObject(server, 0);

            ServerImpl.clientsMap.keySet().forEach(e -> {
                try {
                    var cc = ServerImpl.clientsMap.get(e);
                    cc.notifyServerAvaliable();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            System.out.println("server Running ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void discConnect() {

        try {

            registry.unbind("rmi://localhost:14785/serverService");
            UnicastRemoteObject.unexportObject(server, true);
            // Naming.unbind("rmi://localhost:14785/serverService");
            // server = null;
            // registry = null;

            ServerImpl.clientsMap.keySet().forEach(e -> {
                try {
                    var cc = ServerImpl.clientsMap.get(e);
                    cc.notifyServerNotAvalible();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            System.out.println("server Stop ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
