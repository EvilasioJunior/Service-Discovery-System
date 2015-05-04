/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterservicediiscoverysystem;

import adapter.Monitoring;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificMonitorAdd extends Monitoring{
    
    public SpecificMonitorAdd(Webservice wMonitor){
        this.setMonitoring(wMonitor);
    }
    
    public SpecificMonitorAdd(){
        
    }

    @Override
    public void monitoring(){

        File currentfile = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AdaptadorAirportSystem_1.5\\Services.xml"); 
        
        //Clean monitor file
        try {             
            cleanfile(this.getValuesMonitor());
        } catch (IOException e) {}
        
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);        

        if(currentfile.length()>0){
            ArrayList<Webservice> lstwebservice = 
                    (ArrayList<Webservice>)parser.fromXML(currentfile);

            //If the auxilliary list have elements, writes in the out file
            if(!lstwebservice.isEmpty()){

                String xml = parser.toXML(lstwebservice);

                //Write in the file
                try{       
                    BufferedWriter outs = new BufferedWriter(new FileWriter(this.getValuesMonitor()));
                    outs.write(xml);
                    outs.close();
                    
                }catch (IOException e){}
                
                //Clean currentfile
                try{       
                    BufferedWriter outc = new BufferedWriter(new FileWriter(currentfile));
                    outc.write("");  
                    outc.flush();
                    outc.close();
                    
                }catch (IOException e){}
            }
        }   
        
        //Alert Observers
        this.Notify();
        
        //Time until next read
        try {  
           Thread.sleep(getTime()*1000);  //each getTime() seconds
        } catch (Exception e) {  
        }
    }
    //alterAlert();
    
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }
    
}
