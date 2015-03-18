package imgclient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.ImageInterface;

public class ImgClient {

    public static void main(String[] args) {
        Registry registry = null;
        ImageInterface image = null;
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 5000);
            image = (ImageInterface) registry.lookup("Image");
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex.getMessage());
        }
        imgclient.Process p = new imgclient.Process(registry, image);
        p.loop();
    }
}
