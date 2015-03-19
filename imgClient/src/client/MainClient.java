package client;

import java.util.ArrayList;

public class MainClient {

    public static void main(String[] args) {
        ArrayList<Processor> servers = new ArrayList<>();
<<<<<<< HEAD
        servers.add(new Processor("127.0.0.1", 22001));
//        servers.add(new Processor("10.151.36.25", 5000));
//        servers.add(new Processor("10.151.36.25", 22001));
        servers.add(new Processor("10.181.1.183", 22001));
=======
        servers.add(new Processor("127.0.0.1", 22003));
        servers.add(new Processor("10.151.36.25", 22001));
//        servers.add(new Processor("10.181.1.183", 22003));
>>>>>>> mboh kah udah selesai
        
        Balancer balance = new Balancer(servers);
        balance.loop();
    }
}
