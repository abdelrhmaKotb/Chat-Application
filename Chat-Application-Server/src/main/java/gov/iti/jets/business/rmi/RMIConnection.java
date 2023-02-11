package gov.iti.jets.business.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMIConnection {

    private static RMIConnection instance = null;

    private RMIConnection() {
    }

    public static RMIConnection getInstance() {
        if (instance == null) {
            return new RMIConnection();
        }

        return instance;
    }

    public void connect() {

        try {
            ServerImpl server = new ServerImpl();

            LocateRegistry.createRegistry(14785);
            Naming.rebind("rmi://localhost:14785/serverService", server);

            System.out.println("server Running ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
