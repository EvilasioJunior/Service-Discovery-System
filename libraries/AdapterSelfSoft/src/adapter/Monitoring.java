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
public abstract class Monitoring{
    
    //Atributtes
    private File ValuesMonitor;
    private Webservice wMonitoring;
    private String sOutMonitoring = "";
    private int time = 0;
    
    //Atributtes for Observer
    private Boolean alertSonorous = false;
    private ArrayList analyzers = new ArrayList();
    
    //gets
    public File getValuesMonitor(){
        return this.ValuesMonitor;
    }
    
    public int getTime(){
        return this.time;
    }
    
    public String getOutMonitoring(){
        return this.sOutMonitoring;
    }
    
    //Set new Monitor
    public void setMonitoring(Webservice wMonitoring){
        this.wMonitoring = wMonitoring;
    }
    
    public void setTime(int t){
        this.time = t;
    }
    
    public void setValuesMonitor(String ValuesMonitor){
        this.ValuesMonitor = new File(ValuesMonitor);
        
        try{
        BufferedWriter outMonitor = new BufferedWriter(new FileWriter(this.ValuesMonitor.getAbsolutePath()));
        outMonitor.write("");  
        outMonitor.flush();
        outMonitor.close();
        }catch (IOException e){}
    }
    
    public void setOutMonitoring (String sOutMonitoring){
        this.sOutMonitoring = sOutMonitoring;
    }
    
    public void monitoringWebservice(){
        WSFuncs service = new WSFuncs();
        service.fService(wMonitoring, this.ValuesMonitor.getAbsolutePath());
    }
    
    //Method for Observer Observed
    public void Notify(){
		if(alertSonorous)
			alertSonorous = false;
		else
			alertSonorous = true;
		notifyAnalyzers();
    }

    public Boolean getAlert(){
		return alertSonorous;
    }

    public void addAnalyzer(Analysis a) {
            analyzers.add(a);
    }

    public void removeAnalyzer(Analysis a) {
            analyzers.remove(a);
    }

    private void notifyAnalyzers(){
            Iterator i = analyzers.iterator();
            while(i.hasNext()){
                    Analysis a = (Analysis) i.next();
                    a.update(this);
            }
    }
    
    //----Abstract Method----//
    //monitoring
    public abstract void monitoring();
    
}
