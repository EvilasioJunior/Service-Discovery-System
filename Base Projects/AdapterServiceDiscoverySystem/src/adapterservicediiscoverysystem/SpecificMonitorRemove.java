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
import java.util.List;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificMonitorRemove extends Monitoring{
    
    public SpecificMonitorRemove(Webservice wMonitor){
        this.setMonitoring(wMonitor);
    }
    
    public SpecificMonitorRemove(){
    }

    @Override
    public void monitoring(){
        //this.monitoringWebservice();
         
        //Specific Code
        File currentfile = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicesAirport.xml");
        String resultaux;
        List<Webservice> lstwaux = new ArrayList<Webservice>(); 
        WSFuncs wsfuncs = new WSFuncs();
        
        //Clean monitor file
        try {             
            cleanfile(this.getValuesMonitor());
        } catch (IOException e) {}
        
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);        

        if(currentfile.length()>0){
            ArrayList<Webservice> lstwebservice = (ArrayList<Webservice>)parser.fromXML(currentfile);

            //Add services with defects to auxiliar list
            for(Webservice w: lstwebservice){
                try{
                resultaux = wsfuncs.fService(w,"");
                }catch(Exception e){
                    resultaux=null;
                }
                if(resultaux==null){
                    lstwaux.add(w);
                }
            }

            if(!lstwaux.isEmpty()){

                String xml = parser.toXML(lstwaux);

                //Write in file
                try{       
                BufferedWriter outs = new BufferedWriter(new FileWriter(this.getValuesMonitor()));
                outs.write(xml);  
                outs.close();
                }catch (IOException e){}
            }
        }   
        
        //Alert Observers
        this.Notify();
        
        //Time until next read
        try {  
           Thread.sleep(getTime()*1000);  //each getTime() seconds
        } catch (Exception e) {  
           e.printStackTrace();  
        }
    }
    //alterAlert()
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }
}
