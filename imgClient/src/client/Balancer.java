/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sg
 */
public class Balancer {

    private final ArrayList<Processor> processors;
    private ArrayList<Process> processes;

    public Balancer(ArrayList<Processor> processors) {
        this.processors = processors;
        processes = new ArrayList<>();
    }

    public void loop() {
        Date start = new Date();
        
        File srcFolder = new File(Helper.FOLDER_SRC);
        int i = 0;
        for (final File file : srcFolder.listFiles(Helper.imageFilter)) {
            boolean temp = true;
            while (temp) {
                for (Processor proc : processors) {
                    if (proc.isReady) {
                        Process p = new Process(proc, file);
                        processes.add(p);
                        p.start();
                        temp = false;
                        break;
                    }
                }
            }
        }
        
        for (Process p : processes) {
            try {
                p.join();
            } catch (InterruptedException ex) {
                System.out.println(p.processor + "> " + ex.getMessage());
            }
        }
        
        long totalTime = new Date().getTime() - start.getTime();
        System.out.println("Total time: " + Helper.etaConvert(totalTime));
    }

}
