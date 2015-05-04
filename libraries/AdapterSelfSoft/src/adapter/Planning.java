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
public abstract class Planning{

    private File ValuesAnalysis;
    private File ValuesPlanning;
    private Webservice wPlanning;
   
    //Atributtes for Observer
    private Analysis analyzerObserved;
    
    private Boolean alertSonorous = false;
    private ArrayList executors = new ArrayList();
    
    public File getValuesAnalysis(){
        return this.ValuesAnalysis;
    }
    
    public File getValuesPlanning(){
        return this.ValuesPlanning;
    }
    
    //Set new planner
    public void setPlanning(Webservice wPlanning){
        this.wPlanning = wPlanning;
    }
    
    //Set new object observer
    public void setObserver(Analysis a){
        this.analyzerObserved = a;
	analyzerObserved.addDesigner(this);
    }
    
    public void setValuesPlanning(String ValuesPlanning){
        this.ValuesPlanning = new File(ValuesPlanning);
        
        try{
        BufferedWriter outPlanning = new BufferedWriter(new FileWriter(this.ValuesPlanning.getAbsolutePath()));
        outPlanning.write("");  
        outPlanning.flush();
        outPlanning.close();
        }catch (IOException e){}
    }
    
    //Planning
    public void planWebservice(){
        WSFuncs service = new WSFuncs();
        service.fService(wPlanning,this.ValuesPlanning.getAbsolutePath(), this.ValuesAnalysis.getAbsolutePath());
    }
    
    //Method for Observer    
    public void update(Analysis m) {
		if(m == analyzerObserved){
                    this.ValuesAnalysis = analyzerObserved.getValueAnalysis();
                    plan();
		}
	}
    
    //Method for Observer Observed
    public void Notify(){
		if(alertSonorous)
			alertSonorous = false;
		else
			alertSonorous = true;
		notifyExecutions();
    }

    public Boolean getAlert(){
		return alertSonorous;
    }

    public void addExecutor(Execution a) {
            executors.add(a);
    }

    public void removeExecutor(Execution a) {
            executors.remove(a);
    }

    private void notifyExecutions(){
            Iterator i = executors.iterator();
            while(i.hasNext()){
                    Execution a = (Execution) i.next();
                    a.update(this);
            }
    }
    
    //----Abstract Method----//
    //planning
    public abstract void plan();

}
