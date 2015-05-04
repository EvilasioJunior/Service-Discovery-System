/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterservicediiscoverysystem;

import adapter.Analysis;
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
public class SpecificAnalyzerAdd extends Analysis{

    public SpecificAnalyzerAdd(Webservice wAnalyzer){
        this.setAnalysis(wAnalyzer);
    }
    
    public SpecificAnalyzerAdd(){
 
    }

    public void analyze(){
                
        File currentfile = new File(this.getValueMonitoring().getAbsolutePath());
        String resultaux;
        List<Webservice> lstwaux = new ArrayList<Webservice>(); 
        WSFuncs wsfuncs = new WSFuncs();
        
        //Clean monitor file
        try {             
            cleanfile(this.getValueAnalysis());
        } catch (IOException e) {}
        
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);        
        
        if(currentfile.length()>0){
            ArrayList<Webservice> lstwebservice = (ArrayList<Webservice>)parser.fromXML(currentfile);

            //Add servivces without defects to auxiliar list
            for(Webservice w: lstwebservice){
                try{
                resultaux = wsfuncs.fService(w,"");
                }catch(Exception e){
                    System.out.println(e);
                    resultaux=null;
                }
                if(resultaux!=null){
                    lstwaux.add(w);
                }
            }

            if(!lstwaux.isEmpty()){

                String xml = parser.toXML(lstwaux);

                //Write in file
                try{       
                BufferedWriter outs = new BufferedWriter(new FileWriter(this.getValueAnalysis()));
                outs.write(xml);  
                outs.close();
                }catch (IOException e){}
            }
         }
        
        this.Notify();
    }
    
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }
     
}
