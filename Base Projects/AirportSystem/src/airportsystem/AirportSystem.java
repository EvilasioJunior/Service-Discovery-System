/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsystem;

import java.io.*;
import webservice.WSFuncs;
import webservice.Webservice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author Genshiken
 */
public class AirportSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Funcionalidades (Serviços)
        Webservice service = new Webservice();
        
        //Weservices funções
        WSFuncs funcs = new WSFuncs();
        
        //ArquivosServiços
        File AllServices = new File("ServicosAeroporto.xml");
        
        //Interface Gráfica
        AirportView iAirPort = new AirportView();
        
        //VariáveisControle
        String aux= "",idservice = "";
        
        while(!idservice.equals("0")){
            
            //service = new Webservice("http://jr.com/", "AirPortMonitorTwoService", "http://localhost:8080/AirPortServices/AirPortMonitorTwo?wsdl", "AirPortMonitorTwoPort");
            //System.out.println(services.fService(service, "C:\\Users\\Genshiken\\Documents\\NetBeansProjects\\AirportSystem\\teste.txt"));
            
            //service = new Webservice("http://jr.com/", "AirPortAnalisadorTwoService", "http://localhost:8080/AirPortServices/AirPortAnalisadorTwo?wsdl", "AirPortAnalisadorTwoPort");
            //System.out.println(services.fService(service, "C:\\Users\\Genshiken\\Documents\\NetBeansProjects\\AirportSystem\\teste2.txt", "C:\\Users\\Genshiken\\Documents\\NetBeansProjects\\AirportSystem\\teste.txt"));
            
            //service = new Webservice("http://jr.com/", "AirPortPlanejadorTwoService", "http://localhost:8080/AirPortServices/AirPortPlanejadorTwo?wsdl", "AirPortPlanejadorTwoPort");
            //System.out.println(services.fService(service, "C:\\Users\\Genshiken\\Documents\\NetBeansProjects\\AirportSystem\\teste2.txt"));
    
            //Interface gráfica e seleção de serviço
            idservice = iAirPort.WriteinScreen(AllServices);

            //testa condição de parada
            if(idservice.equals("0") || idservice.equals("p"))
                continue;

            service = funcs.SearchServiceByIDXML(idservice,AllServices);

            //executa serviço escolhido
            if(service!=null){
                if(service.getarguments().size() > 0)
                    aux = funcs.fService(service, service.getarguments().toArray(new String[service.getarguments().size()]));
                else
                    aux = funcs.fService(service, service.getarguments().toArray(new String[service.getarguments().size()])); 
            }
            else
                System.out.printf("Você tentou executar um serviço inexistente, escolha outro serviço. \n");

            if(aux != null)
                System.out.println(aux + "\n");
        }
    }
}
