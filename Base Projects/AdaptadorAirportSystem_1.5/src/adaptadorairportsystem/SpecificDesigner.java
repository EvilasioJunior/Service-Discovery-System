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
public class SpecificDesigner extends Planning{
    
    public SpecificDesigner(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificDesigner(){
    }
    
    public void plan(){
        //Código Específico
        //Arquivo de saída e de antrada
        File arquivoSAeroporto = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicosAeroporto.xml");
        File arquivoAnalise = new File(this.getValuesAnalysis().getAbsolutePath());
        
        //Configurações do XML
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);
        
        //Listas de serviços atuais e que devem ser retirados
        if(arquivoAnalise.length()>0){
            ArrayList<Webservice> lwebservice = (ArrayList<Webservice>) parser.fromXML(arquivoSAeroporto);
            ArrayList<Webservice> lwebservicer = (ArrayList<Webservice>) parser.fromXML(arquivoAnalise);

            //variáveis auxiliares
            int[] ids;
            ids = new int[lwebservicer.size()];
            int i=0, indice=0;

            //Popula o array de ids com um valor coringa
            for(int j=0;j<ids.length;j++)
              ids[j] = -1;

            //Descobre os indices dos web services que devem ser retirados da lista
            for(Webservice w:lwebservice){            
               for(Webservice w2:lwebservicer){
                   if(w.getid()==w2.getid()){
                       ids[indice]=i;
                       indice++;
                   }
               }
               i++;
            }

            //Retira web services da lista
            for(i=ids.length-1;i>=0;i--){
               if(ids[i]!=-1) 
                lwebservice.remove(ids[i]);
            }

            //Cria nova lista
            List<Webservice> listwebservice = new ArrayList<Webservice>(lwebservice);
            String xml = parser.toXML(listwebservice);

            //Escreve no arquivo
            try{       
            BufferedWriter outs = new BufferedWriter(new FileWriter(arquivoSAeroporto.getAbsolutePath()));
            outs.write(xml);  
            outs.close();
            }catch (IOException e){}
        }
    }
}
