/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicediscoverysystem;

import java.io.*;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author Genshiken
 */
public class ServiceDiscoverySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Features (Services)
        Webservice service = new Webservice();
  
        //Weservices functions
        WSFuncs funcs = new WSFuncs();
        
        //Base features utilizing file
        File AllServices = new File("ServicesAirport.xml");
        if (!AllServices.exists())
            AllServices.createNewFile();
        
        //Graphic Interface
        ServiceDiscoveryView iAirPort = new ServiceDiscoveryView();
        
        //Specific variables
        String aux= "",idservice = "";
        
        //File used for Stop Criteria of the Adapter
        File sf = new File("StopFile.txt");
        sf.createNewFile();
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(sf));
            out.write("");  
            out.flush();
            out.close();
        }catch (IOException e){}
        
        //Executing Adapter
        String[] commands = {"","",""};
        commands[0] = "java";
        commands[1] = "-jar";
        commands[2] = "C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AdaptadorAirportSystem_1.5" +
                      "\\dist\\AdapterServiceDiscoverySystem.jar";
        Process p  = Runtime.getRuntime().exec(commands);
        
        while(!idservice.equals("0")){
            
            //Graphic Interface
            idservice = iAirPort.WriteinScreen(AllServices);

            //Condition Test of stop
            if(idservice.equals("0") || idservice.equals("p"))
                continue;

            service = funcs.SearchServiceByIDXML(idservice,AllServices);

            if(service!=null){
                if(service.getarguments().size() > 0)
                    aux = funcs.fService(service, service.getarguments().toArray(new String[service.getarguments().size()]));
                else
                    aux = funcs.fService(service, service.getarguments().toArray(new String[service.getarguments().size()])); 
            }
            else
                System.out.printf("service not exist, choice other service. \n");

            if(aux != null)
                System.out.println(aux + "\n");
        }
        
        //Stop Criteria of the Adapter
        try{       
            BufferedWriter out = new BufferedWriter(new FileWriter(sf));
            out.write("stop");  
            out.close();
        }catch (IOException e){}
    }
}
