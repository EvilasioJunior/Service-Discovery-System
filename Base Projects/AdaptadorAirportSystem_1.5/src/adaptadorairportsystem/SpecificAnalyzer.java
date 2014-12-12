/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

import adapter.Analysis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificAnalyzer extends Analysis{

    public SpecificAnalyzer(Webservice wAnalyzer){
        this.setAnalysis(wAnalyzer);
    }
    
    public SpecificAnalyzer(){
    }

    public void analyze(){
        //Código Específico
        //variáveis de controle
        String aux;
        try{
            BufferedReader in = new BufferedReader(new FileReader(this.getValueMonitoring()));
            BufferedWriter out = new BufferedWriter(new FileWriter(this.getValueAnalysis()));
            aux = in.readLine();
            while(!(aux==null)){
                out.write(aux);
                aux = in.readLine();
                if(aux==null)
                    continue;
                out.newLine();
            }
            in.close();
            out.close();
        }catch(Exception e){}
      
        
        this.alterAlert();
    }
     
}
