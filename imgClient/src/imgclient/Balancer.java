/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imgclient;

import java.util.ArrayList;

/**
 *
 * @author sg
 */
public class Balancer {
    
    public ArrayList<Process> processes;
    
    public Balancer(ArrayList<Process> processes) {
        this.processes = processes;
    }

}
