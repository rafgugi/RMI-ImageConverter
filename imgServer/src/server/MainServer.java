package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    private void startServer() {
        try {
            /*
             * register server in port 5000 and bind as service named "Echo"
             */
            Registry registry = LocateRegistry.createRegistry(22003);
            registry.rebind("Image", new Image());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Server is ready");
    }

    public static void main(String[] args) {
        MainServer main = new MainServer();
        main.startServer();
    }
}
