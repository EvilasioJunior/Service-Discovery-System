/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

import adapter.Monitoring;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificMonitor2 extends Monitoring{
    
    public SpecificMonitor2(Webservice wMonitor){
        this.setMonitoring(wMonitor);
    }
    
    public SpecificMonitor2(){
    }

    public void monitoring(){
        this.monitoringWebservice();
        //Alert Observers
        this.alterAlert();
        
        //Time until next read
        try {  
           Thread.sleep(getTime()*1000);  //each getTime() seconds
        } catch (Exception e) {  
           e.printStackTrace();  
        }
    }
    //alterAlert();
     
    
}
