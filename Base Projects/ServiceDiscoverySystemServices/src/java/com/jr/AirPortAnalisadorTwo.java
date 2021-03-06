/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jr;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import webservice.Webservice;
import webservice.WSFuncs;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AirPortAnalisadorTwo {
   @WebMethod
    public String funcService(String arquivo, String arquivomonitor){
         
        //variáveis de controle
        //String aux = "", resultaux;
        //String targetName, Name, url, Port; 
        File arquivoatual = new File(arquivomonitor);
        String resultaux;
        List<Webservice> lstwaux = new ArrayList<Webservice>(); 
        String MensagemRetorno = "Analise Concluída com sucesso";
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
                BufferedWriter outs = new BufferedWriter(new FileWriter(arquivo));
                outs.write(xml);  
                outs.close();
                }catch (IOException e){}
            }
         }   
        return MensagemRetorno;
    }
}
