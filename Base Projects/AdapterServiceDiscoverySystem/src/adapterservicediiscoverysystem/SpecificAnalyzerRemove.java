/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterservicediiscoverysystem;

import adapter.Analysis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificAnalyzerRemove extends Analysis{

    public SpecificAnalyzerRemove(Webservice wAnalyzer){
        this.setAnalysis(wAnalyzer);
    }
    
    public SpecificAnalyzerRemove(){
    }

    public void analyze(){
        //Specific Code
        String aux;
        
        //Clean monitor file
        try {             
            cleanfile(this.getValueAnalysis());
        } catch (IOException e) {}
        
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
      
        
        this.Notify();
    }
     
    public void cleanfile(File file) throws IOException{
        BufferedWriter outm = new BufferedWriter(new FileWriter(file));
        outm.write("");  
        outm.flush();
        outm.close();
    }
}
