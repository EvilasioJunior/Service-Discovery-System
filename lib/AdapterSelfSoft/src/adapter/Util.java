/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import webservice.Webservice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 *
 * @author Evilasio
 */
public class Util {
    
    //Construtor
    public Util(){}
    
    //-----Funções específicas-------//
    public List<Monitoring> FiletoMonitors(File file){
        List<Monitoring> Monitors = new ArrayList();;
        Monitoring maux;
        List<Webservice> lwMonitoring = FiletoWebservices(file); 
        
        for(Webservice wmaux: lwMonitoring){
            maux = new Monitoring(wmaux);
            maux.setValuesMonitor(wmaux.getarguments().get(0));
            Monitors.add(maux);
        }
        return Monitors;
    }
    
    public List<Analysis> FiletoAnalyzers(File file){
        List<Analysis> Analyzers = new ArrayList();;
        Analysis aaux;
        List<Webservice> lwAnalysis = FiletoWebservices(file); 
        
        for(Webservice waaux: lwAnalysis){
            aaux = new Analysis(waaux);
            aaux.setValuesAnalysis(waaux.getarguments().get(0));
            Analyzers.add(aaux);
        }
        return Analyzers;
    }
    
    public List<Planning> FiletoDesigners(File file){
        List<Planning> Designers = new ArrayList();;
        Planning paux;
        List<Webservice> lwPlanning = FiletoWebservices(file); 
        
        for(Webservice wpaux: lwPlanning){
            paux = new Planning(wpaux);
            Designers.add(paux);
        }
        return Designers;
    }
    
    public List<Monitoring> FileXMLtoMonitors(File file){
        List<Monitoring> Monitors = new ArrayList();;
        Monitoring maux;
        List<Webservice> lwMonitoring = FiletoWebservicesXML(file); 
        
        for(Webservice wmaux: lwMonitoring){
            maux = new Monitoring(wmaux);
            maux.setValuesMonitor(wmaux.getarguments().get(0));
            Monitors.add(maux);
        }
        return Monitors;
    }
    
    public List<Analysis> FileXMLtoAnalyzers(File file){
        List<Analysis> Analyzers = new ArrayList();;
        Analysis aaux;
        List<Webservice> lwAnalise = FiletoWebservicesXML(file); 
        
        for(Webservice waaux: lwAnalise){
            aaux = new Analysis(waaux);
            aaux.setValuesAnalysis(waaux.getarguments().get(0));
            Analyzers.add(aaux);
        }
        return Analyzers;
    }
    
    public List<Planning> FileXMLtoDesigners(File file){
        List<Planning> Designers = new ArrayList();;
        Planning paux;
        List<Webservice> lwPlanning = FiletoWebservicesXML(file); 
        
        for(Webservice wpaux: lwPlanning){
            paux = new Planning(wpaux);
            Designers.add(paux);
        }
        return Designers;
    }
    
    //-----Funções genéricas-------//
    public List<Webservice> FiletoWebservices(File file){
        String aux;
        String targetName = "", Name= "", url="", Port ="";
        Webservice waux;
        List<Webservice> laux = new ArrayList();
        
        //Adicionando dados do serviço solicitado ao file
        try{    
            BufferedReader ins = new BufferedReader(new FileReader(file)); 
            aux = ins.readLine(); //Pirmeiro id
            

            //verifica se já está no final do file
            if(aux!=null){
                //Executando webservices
                do{
                    //Obter dados do webservice
                    targetName = ins.readLine();
                    Name = ins.readLine();
                    url = ins.readLine();
                    Port = ins.readLine();
                    waux = new Webservice(targetName, Name, url, Port);
                    aux = ins.readLine();//argumentos
                    if(aux!=null) {
                        if(!aux.equals(""))
                         waux.addarguments(aux);  
                    }
                    laux.add(waux);

                    aux=ins.readLine();//próximo id
                }while(aux!=null);
            }
            ins.close();
        } catch(IOException e){}
                
        return laux;
    }
    

    public static List<Webservice> FiletoWebservicesXML (File file){   
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(file);
        
        return lWaux;
    } 
}
