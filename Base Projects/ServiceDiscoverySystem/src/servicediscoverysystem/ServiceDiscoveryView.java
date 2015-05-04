/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicediscoverysystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import webservice.Webservice;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 *
 * @author Evilasio
 */
public class ServiceDiscoveryView { 
    
    public ServiceDiscoveryView(){}
    
    public String WriteinScreen(File Values){
        String idservice;
        Scanner in = new Scanner(System.in);
        ArrayList<Webservice> lservice = new ArrayList<Webservice>();

        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        System.out.printf("List of available services:\n");
        System.out.printf("==============================\n");
        
        if(Values.length()>0)
            lservice = (ArrayList<Webservice>) parser.fromXML(Values);
            
        for(Webservice w:lservice)
            System.out.println("id "+w.getid()+": "+w.getDescription());

        System.out.printf("id 0: Exit System\n");
        System.out.printf("Enter p for find new services\n");
        System.out.printf("==============================\n");
        System.out.printf("Report Id of the desired service\n");

        idservice = in.nextLine();

        return idservice;
    }
   
}
