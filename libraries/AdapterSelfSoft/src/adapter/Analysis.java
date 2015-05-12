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
public abstract class Analysis{
    
    private File ValuesMonitor;
    private File ValuesAnalysis;
    private Webservice wAnalysis;
    private String sOutMonitoring = "";
    private String sOutAnalysis = "";
    
    //Atributtes for Observer 
    private Monitoring monitorObserved;
    
    private Boolean alertSonorous = false;
    private ArrayList planners = new ArrayList();
    
    //gets
    public File getValueAnalysis(){
        return this.ValuesAnalysis;
    }
    
    public File getValueMonitoring(){
        return this.ValuesMonitor;
    }
    
    public String getOutMonitoring(){
        return this.sOutMonitoring;
    }
    
    public String getOutAnalysis(){
        return this.sOutAnalysis;
    }
    //Set new Analyzer
    public void setAnalysis(Webservice wAnalysis){
        this.wAnalysis = wAnalysis;
    } 
    
    //Set new object observer
    public void setObserver(Monitoring m){
        this.monitorObserved = m;
	monitorObserved.addAnalyzer(this);
    }
    
    public void setValuesAnalysis(String ValuesAnalysis){
        this.ValuesAnalysis = new File(ValuesAnalysis);
        
        try{
        BufferedWriter outAnalysis = new BufferedWriter(new FileWriter(this.ValuesAnalysis.getAbsolutePath()));
        outAnalysis.write("");  
        outAnalysis.flush();
        outAnalysis.close();
        }catch (IOException e){}
    }
    
    public void setOutAnalysis(String sOutAnalysis){
        this.sOutAnalysis = sOutAnalysis;
    }
    
    public void analyzeWebservice(){
        WSFuncs service = new WSFuncs();
        service.fService(wAnalysis, this.ValuesAnalysis.getAbsolutePath(), this.ValuesMonitor.getAbsolutePath());
    } 
    
    //Method for Observer 
    public void update(Monitoring m) {
		if(m == monitorObserved){
                    this.ValuesMonitor = monitorObserved.getValuesMonitor();
                    if (monitorObserved.getOutMonitoring().equals(""))
                        this.sOutMonitoring = monitorObserved.getOutMonitoring();
                    analyze();
		}
	}
 
    //Method for Observer Observed
    public void Notify(){
		if(alertSonorous)
			alertSonorous = false;
		else
			alertSonorous = true;
		notifyPlans();
    }

    public Boolean getAlert(){
		return alertSonorous;
    }

    public void addDesigner(Planning a) {
            planners.add(a);
    }

    public void removeDesigner(Planning a) {
            planners.remove(a);
    }

    private void notifyPlans(){
            Iterator i = planners.iterator();
            while(i.hasNext()){
                    Planning a = (Planning) i.next();
                    a.update(this);
            }
    }
    
    //----Abstract Method----//
    //analyzing
    public abstract void analyze();

    
}
