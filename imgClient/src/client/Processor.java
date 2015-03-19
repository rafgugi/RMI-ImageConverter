/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.ImageInterface;

/**
 *
 * @author sg
 */
public class Processor {

    private Registry registry;
    private ImageInterface image;
    public boolean isReady;
    private String server;
    private int port;

    public Processor(String server, int port) {
        isReady = true;
        this.server = server;
        this.port = port;
        try {
            registry = LocateRegistry.getRegistry(server, port);
            image = (ImageInterface) registry.lookup("Image");
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(this + "> " + ex.getMessage());
            isReady = false;
        }
    }

    public ImageInterface getImage() {
        return image;
    }

    @Override
    public String toString() {
        return server + ":" + port;
    }

}
