/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.io.File;
import webservice.WSFuncs;
import webservice.Webservice;
/**
 *
 * @author Evilasio
 */
public abstract class Execution {
    private File ValuesPlanning;
    private Webservice wExecution;
   
    //Atributtes for Observer
    private Planning plannerObserved; 
    
    public File getValuesPlanning(){
        return this.ValuesPlanning;
    }
    
    //Set new planner
    public void setExecution(Webservice wExecution){
        this.wExecution = wExecution;
    }
    
    //Set new object observer
    public void setObserver(Planning a){
        this.plannerObserved = a;
	plannerObserved.addExecutor(this);
    }
    
    //Execution
    public void executionWebservice(){
        WSFuncs service = new WSFuncs();
        service.fService(wExecution, this.ValuesPlanning.getAbsolutePath());
    }
    
    //Method for Observer    
    public void update(Planning m) {
		if(m == plannerObserved){
                    this.ValuesPlanning = plannerObserved.getValuesPlanning();
                    execute();
		}
	}
    
    //----Abstract Method----//
    //execution
    public abstract void execute();
}
