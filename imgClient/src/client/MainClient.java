package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import rmi.ImageInterface;

public class MainClient {

    public static void main(String[] args) {
        ArrayList<Processor> processors = new ArrayList<>();
        processors.add(new Processor("127.0.0.1", 5000));
        
        Balancer balance = new Balancer(processors);
        balance.loop();
    }
}
