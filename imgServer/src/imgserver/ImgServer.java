/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ImgServer {

    private void startServer() {
        try {
            /*
             * register server in port 5000 and bind as service named "Echo"
             */
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.rebind("Image", new Image());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Server is ready");
    }

    public static void main(String[] args) {
        ImgServer main = new ImgServer();
        main.startServer();
    }
}
