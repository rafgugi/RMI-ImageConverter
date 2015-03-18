/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 *
 * @author sg
 */
public class Balancer {

    private final ArrayList<Processor> processors;

    public Balancer(ArrayList<Processor> processors) {
        this.processors = processors;
    }

    public void loop() {
        File srcFolder = new File(Helper.FOLDER_SRC);
        int i = 0;
        for (final File file : srcFolder.listFiles(Helper.imageFilter)) {
            boolean temp = true;
            while (temp) {
                for (Processor proc : processors) {
                    if (proc.isReady) {
                        Process p = new Process(proc, file);
                        ((Thread) p).start();
                        temp = false;
                        break;
                    }
                }
            }
        }
    }

}
