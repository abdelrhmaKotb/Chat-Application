package gov.iti.jets;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;



public class App  {



    public static void main(String[] args) {
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
