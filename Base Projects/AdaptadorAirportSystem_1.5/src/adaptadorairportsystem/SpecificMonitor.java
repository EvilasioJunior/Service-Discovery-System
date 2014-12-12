/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

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
public class SpecificMonitor extends Monitoring{
    
    public SpecificMonitor(Webservice wMonitor){
        this.setMonitoring(wMonitor);
    }
    
    public SpecificMonitor(){
    }

    public void monitoring(){
        //this.monitoringWebservice();
         
        //Código Específico
        File arquivoatual = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicosAeroporto.xml");
        String resultaux;
        List<Webservice> lstwaux = new ArrayList<Webservice>(); 
        WSFuncs wsfuncs = new WSFuncs();
        
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);        

        if(arquivoatual.length()>0){
            ArrayList<Webservice> lstwebservice = (ArrayList<Webservice>)parser.fromXML(arquivoatual);

            //Adiciona serviços com defeitos a lista auxiliar
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

            //Se a lista de serviços auxiliar tiver elementos grave no aruivo de saída
            if(!lstwaux.isEmpty()){

                String xml = parser.toXML(lstwaux);

                //Escreve no arquivo
                try{       
                BufferedWriter outs = new BufferedWriter(new FileWriter(this.getValuesMonitor()));
                outs.write(xml);  
                outs.close();
                }catch (IOException e){}
            }
        }   
        
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
