/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

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
public class SpecificDesigner1 extends Planning{
    
    public SpecificDesigner1(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificDesigner1(){
        
        //Arquivo de saída e de antrada
        File arquivoSAeroporto = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicosAeroporto.xml");
        File arquivoAnalise = new File(this.getValuesAnalysis().getAbsolutePath());
        
        //Configurações do XML
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);
        
        if(arquivoAnalise.length()>0){
            //Listas de serviços atuais e que devem ser retirados
            ArrayList<Webservice> lwebservice = (ArrayList<Webservice>) parser.fromXML(arquivoSAeroporto);
            ArrayList<Webservice> lwebservicer = (ArrayList<Webservice>) parser.fromXML(arquivoAnalise);


            //Adiciona web services novos a lista
            for(Webservice w: lwebservicer){
                lwebservice.add(w);
            }

            //Cria nova lista
            List<Webservice> listwebservice = new ArrayList<Webservice>(lwebservice);
            String xml = parser.toXML(listwebservice);

            //Escreve no arquivo
            ///*
            try{
            BufferedWriter outs = new BufferedWriter(new FileWriter(arquivoSAeroporto.getAbsolutePath()));
            outs.write(xml);  
            outs.close();
            }catch (IOException e){}
            //*/
        }
    }
    
    public void plan(){
        this.planWebservice();
    }
}
