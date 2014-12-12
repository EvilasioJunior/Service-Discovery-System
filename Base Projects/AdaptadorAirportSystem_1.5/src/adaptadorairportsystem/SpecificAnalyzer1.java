/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

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
public class SpecificAnalyzer1 extends Analysis{

    public SpecificAnalyzer1(Webservice wAnalyzer){
        this.setAnalysis(wAnalyzer);
    }
    
    public SpecificAnalyzer1(){
        
        File arquivoatual = new File(this.getValueMonitoring().getAbsolutePath());
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

            //Se a lista de serviços auxiliar tiver elementos grave no arquivo de saída
            if(!lstwaux.isEmpty()){

                String xml = parser.toXML(lstwaux);

                //Escreve no arquivo
                try{       
                BufferedWriter outs = new BufferedWriter(new FileWriter(this.getValueAnalysis()));
                outs.write(xml);  
                outs.close();
                }catch (IOException e){}
            }
         } 
    }

    public void analyze(){
        this.analyzeWebservice();
        this.alterAlert();
    }
     
}
