/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsystem;

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
public class AirportView {
  
    
    public AirportView(){}
    
    // Informações na tela e entrada de dados do teclado
    public String WriteinScreen(File Valores){
        String idservice;
        Scanner entrada = new Scanner(System.in);
        
        //configurações para leitura xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        System.out.printf("Lista de serviços disponíveis:\n");
        System.out.printf("==============================\n");
        
        ArrayList<Webservice> lservice = (ArrayList<Webservice>) parser.fromXML(Valores);
        for(Webservice w:lservice)
            System.out.println("id "+w.getid()+": "+w.getDescription());

        System.out.printf("id 0: Sair do Sitema\n");
        System.out.printf("Digite p para procurar novos servicos\n");
        System.out.printf("==============================\n");
        System.out.printf("Informe Id do serviço desejado\n");

        idservice = entrada.nextLine();

    
        return idservice;
    }
   
}
