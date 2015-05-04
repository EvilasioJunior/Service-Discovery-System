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
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import webservice.Webservice;

/**
 *
 * @author junior
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AirPortPlanejadorTwo {
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
        return MensagemRetorno;
    }
}
