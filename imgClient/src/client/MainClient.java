package client;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

public class MainClient {

    public static void main(String[] args) {
        ArrayList<Processor> servers = new ArrayList<>();
        servers.add(new Processor("127.0.0.1", 5000));
        
        Balancer balance = new Balancer(servers);
        balance.loop();
    }
}
