/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterservicediiscoverysystem;

import adapter.Planning;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificPlannerAdd extends Planning{
    
    public SpecificPlannerAdd(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificPlannerAdd(){
              
    }
    
    @Override
    public void plan(){
        File fileSAirport = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicesAirport.xml");
        File fileAnalysis = new File(this.getValuesAnalysis().getAbsolutePath());
        
        //Auxiliar list of Webservices
        ArrayList<Webservice> lwebservicer;
        ArrayList<Webservice> lwebservice;
        
        //Configuration of XML
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);

        if(fileAnalysis.length()>0){
            //List of current services and that must be removed
            lwebservicer = (ArrayList<Webservice>) parser.fromXML(fileAnalysis);
            
            if(fileSAirport.length()>0){
                lwebservice = (ArrayList<Webservice>) parser.fromXML(fileSAirport);
            }
            else{
                lwebservice = new ArrayList<Webservice>();
            }
            
            //Add web services new lists
            for(Webservice w: lwebservicer){
                lwebservice.add(w);
            }

            //Create new list
            List<Webservice> listwebservice = new ArrayList<Webservice>(lwebservice);
            String xml = parser.toXML(listwebservice);

            //Write in file
            try{
            BufferedWriter outs = new BufferedWriter(new FileWriter(fileSAirport.getAbsolutePath()));
            outs.write(xml);  
            outs.close();
            }catch (IOException e){}
        }
    }
}
