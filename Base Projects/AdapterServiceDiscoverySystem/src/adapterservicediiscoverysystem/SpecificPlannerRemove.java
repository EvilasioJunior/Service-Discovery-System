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
public class SpecificPlannerRemove extends Planning{
    
    public SpecificPlannerRemove(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificPlannerRemove(){
    }
    
    public void plan(){
        //Specific Code
        File fileSAirport = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicesAirport.xml");
        File fileAnalysis = new File(this.getValuesAnalysis().getAbsolutePath());
        
        //Configuration of the XML
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);
        
        //List of current services and that must be removed
        if(fileAnalysis.length()>0){
            ArrayList<Webservice> lwebservice = (ArrayList<Webservice>) parser.fromXML(fileSAirport);
            ArrayList<Webservice> lwebservicer = (ArrayList<Webservice>) parser.fromXML(fileAnalysis);

            //Auxiliar variables
            int[] ids;
            ids = new int[lwebservicer.size()];
            int i=0, indice=0;

            //Complete the array of ids with a value auxiliar
            for(int j=0;j<ids.length;j++)
              ids[j] = -1;

            //Discover the indices of the web services that must be removed of the list
            for(Webservice w:lwebservice){            
               for(Webservice w2:lwebservicer){
                   if(w.getid()==w2.getid()){
                       ids[indice]=i;
                       indice++;
                   }
               }
               i++;
            }

            //Remove web services of the list
            for(i=ids.length-1;i>=0;i--){
               if(ids[i]!=-1) 
                lwebservice.remove(ids[i]);
            }

            //Create new list
            List<Webservice> listwebservice = new ArrayList<Webservice>(lwebservice);
            String xml = parser.toXML(listwebservice);

            //Read in file
            try{       
            BufferedWriter outs = new BufferedWriter(new FileWriter(fileSAirport.getAbsolutePath()));
            outs.write(xml);  
            outs.close();
            }catch (IOException e){}
        }
    }
}
