package client;

import java.util.ArrayList;

public class MainClient {

    public static void main(String[] args) {
        ArrayList<Processor> servers = new ArrayList<>();
        servers.add(new Processor("127.0.0.1", 22001));
        servers.add(new Processor("10.151.36.25", 5000));
        servers.add(new Processor("10.151.36.25", 22001));
        servers.add(new Processor("10.151.43.58", 22001));
        
        Balancer balance = new Balancer(servers);
        balance.loop();
    }
}
