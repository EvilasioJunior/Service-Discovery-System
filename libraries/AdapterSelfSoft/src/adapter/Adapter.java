/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

//import adaptadorairportsystem.Util;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author junior
 */
public class Adapter {
    
    private List<Monitoring> monitors;
    private List<Analysis> analyzers;
    private List<Planning> planners;
    private List<Execution> executors;
    
    //Funtions of each system
    private Boolean proceed;
    private ArrayList adapterObservers  = new ArrayList();
    
    //Constructor
    public Adapter(){
    }
    
    public Adapter(List<Monitoring> monitors, List<Analysis> analyzers, List<Planning> designers){
        this.monitors = monitors;
        this.analyzers = analyzers;
        this.planners = designers;
    }
    
    public Adapter(List<Monitoring> monitors, List<Analysis> analyzers, List<Planning> planners, List<Execution> executors){
        this.monitors = monitors;
        this.analyzers = analyzers;
        this.planners = planners;
        this.executors = executors;
    }
    
    //gets
    public List<Monitoring> getlistMonitoring(){
        return this.monitors;
    }
    
    public List<Analysis> getlistAnalysis(){
        return this.analyzers;
    }
        
    public List<Planning> getlistPlanning(){
        return this.planners;
    }
    
    public List<Execution> getlistExecution(){
        return this.executors;
    }
    
    //sets    
    public void setlistMonitoring(List<Monitoring> monitors){
         this.monitors = monitors;
    }
    
    public void  setlistAnalysis(List<Analysis> analyzers){
         this.analyzers = analyzers;
    }
        
    public void setlistPlanning(List<Planning> planners){
         this.planners = planners;
    }
    
    public void setlistExecution(List<Planning> executors){
         this.planners = executors;
    }
        
    public void setObserverListAnalysis(List<Monitoring> lm){
        for(int i=0; i < lm.size(); i++)
            this.analyzers.get(i).setObserver(lm.get(i));   
    }
    
    public void setObserverListPlanning(List<Analysis> la){
        for(int i=0; i < la.size(); i++)
            this.planners.get(i).setObserver(la.get(i));   
    }
    
    public void setObserverListExecution(List<Planning> lp){
        for(int i=0; i < lp.size(); i++)
            this.executors.get(i).setObserver(lp.get(i));   
    }
    
    public void setStop(Boolean stop){
        this.proceed = stop;
    }
    
    //Special methods
      
    //Execute adaptation
    public void run(){
        updates();
        while(proceed){
            for(Monitoring maux: this.getlistMonitoring())
                maux.monitoring();
            updates();
        }
    }
    
    //Methods for use specific functions of the current system
    public void addAdapterObserver(AbstractAdapterObserver a) {
            adapterObservers.add(a);
    }

    public void removeAdapterObserver(AbstractAdapterObserver a) {
            adapterObservers.remove(a);
    }
    
        //Set value of the variable of stop of according to a specific method of the current system
    private void updates(){
            Iterator i = adapterObservers.iterator();
            while(i.hasNext()){
                    AbstractAdapterObserver a = (AbstractAdapterObserver) i.next();
                    a.update(this);
            }
    }
}
