/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author junior
 */
public class Monitoring implements Monitor{
    
    //Atributos
    private File ValuesMonitor;
    private Webservice wMonitoring;
    int time = 0;
    
    //Atributos para Observer Objeto Observado
    private Boolean alertSonorous = false;
    private ArrayList analyzers = new ArrayList();
    
    //Inicilizador
    public Monitoring(Webservice wMonitoring){
        //Inicializa variáveis
        this.wMonitoring = wMonitoring;
    }
    
    //gets
    public File getValuesMonitor(){
        return this.ValuesMonitor;
    }
    
    //Setar novo monitor
    public void setMonitoring(Webservice wMonitoring){
        this.wMonitoring = wMonitoring;
    }
    
    public void setTime(int t){
        this.time = t;
    }
    
    public void setValuesMonitor(String ValuesMonitor){
        this.ValuesMonitor = new File(ValuesMonitor);
        
        //Esvazia arquivo com informações trazidas pelo monitor
        try{
        BufferedWriter outMonitor = new BufferedWriter(new FileWriter(this.ValuesMonitor.getAbsolutePath()));
        outMonitor.write("");  
        outMonitor.flush();
        outMonitor.close();
        }catch (IOException e){}
    }
    
    //monitorar
    public void monitoring(){
        WSFuncs service = new WSFuncs();
        service.fService(wMonitoring, this.ValuesMonitor.getAbsolutePath());
        alterAlert();
        
        //tempo até próxima leitura
        try {  
           Thread.sleep(time*1000);  //a cada time segundos 
        } catch (Exception e) {  
           e.printStackTrace();  
        }
    }
    
    //Métodos para Observer Objeto Observado
    public void alterAlert(){
		if(alertSonorous)
			alertSonorous = false;
		else
			alertSonorous = true;
		notifyAnalyzers();
    }

    public Boolean getAlert(){
		return alertSonorous;
    }

    @Override
    public void addAnalyzer(Analyzer a) {
            analyzers.add(a);
    }

    @Override
    public void removeAnalyzer(Analyzer a) {
            analyzers.remove(a);
    }

    private void notifyAnalyzers(){
            Iterator i = analyzers.iterator();
            while(i.hasNext()){
                    Analyzer a = (Analyzer) i.next();
                    a.update(this);
            }
    }
    
}
