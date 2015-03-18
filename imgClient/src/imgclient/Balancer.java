/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgclient;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**
 *
 * @author sg
 */
public class Balancer {
    
    public ArrayList<Processor> processors;
    
    public Balancer(ArrayList<Processor> processors) {
        this.processors = processors;
    }

    public void loop() {
        FileFilter ff;
        ff = new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                String extension = Helper.getExtension(file);
                return ("png".equals(extension) || "jpg".equals(extension));
            }
        };
        
        int size = processors.size();

        File srcFolder = new File(Helper.FOLDER_SRC);
        int i = 0;
        for (final File file : srcFolder.listFiles(ff)) {
            Process p = new Process(processors.get(i%size), file);
            p.run();
            i++;
        }
    }

}
