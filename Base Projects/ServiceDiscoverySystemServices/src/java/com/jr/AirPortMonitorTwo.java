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
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import webservice.Webservice;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AirPortMonitorTwo {
   @WebMethod
    public String funcService(String arquivo){
        
        //variáveis de controle
        String MensagemRetorno = "Monitoramento Concluído com sucesso";
        File newservices = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\ServicosAeroportoR.xml");
        List<Webservice> lstwebservice = new ArrayList<Webservice>();
                
        XStream parser = new XStream(new DomDriver("ISO-8859-1"));
        parser.setMode(XStream.NO_REFERENCES);   
        
        if(newservices.length() > 0)
            lstwebservice = (ArrayList<Webservice>)parser.fromXML(newservices);
        
        if(!lstwebservice.isEmpty()){
            
            String xml = parser.toXML(lstwebservice);
            
            //Escreve no arquivo
            try{       
            BufferedWriter outs = new BufferedWriter(new FileWriter(arquivo));
            outs.write(xml);  
            outs.close();
            }catch (IOException e){}
        }
        
        try{
        //limpa arquivo de novos serviços
        BufferedWriter out3 = new BufferedWriter(new FileWriter(newservices));
        out3.write("");  
        out3.flush();
        out3.close();
        }catch(IOException e){}

        return MensagemRetorno;
    }
}
