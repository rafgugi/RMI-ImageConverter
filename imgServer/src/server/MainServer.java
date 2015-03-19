/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    private void startServer() {
        try {
            Registry registry = LocateRegistry.createRegistry(22001);
            registry.rebind("Image", new Image());
            System.out.println("Server is ready");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainServer main = new MainServer();
        main.startServer();
    }
}
