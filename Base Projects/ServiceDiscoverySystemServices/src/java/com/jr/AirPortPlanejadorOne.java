/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import webservice.Webservice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AirPortPlanejadorOne {
   @WebMethod
    public String funcService(String arquivoanalise){
        //Mensagem de sucesso
        String MensagemRetorno = "Planejamento concluído com sucesso";
        
        //Arquivo de saída e de antrada
        File arquivoSAeroporto = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicosAeroporto.xml");
        File arquivoAnalise = new File(arquivoanalise);
        
        //Configurações do XML
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);
//        parser.alias("Webservice", Webservice.class);
//        parser.aliasAttribute(Webservice.class, "arguments", "argumentos");
//        parser.aliasAttribute(Webservice.class, "id", "id");
//        parser.aliasAttribute(Webservice.class, "Descricao", "Descricao");
        
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
        return MensagemRetorno;
    }
}