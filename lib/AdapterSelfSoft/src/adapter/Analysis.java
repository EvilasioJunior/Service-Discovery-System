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
public class Analysis implements Analyzer{
    
    private File ValuesMonitor;
    private File ValuesAnalysis;
    private Webservice wAnalysis;
    
    //Atributos para Observer Observador
    private Monitoring monitorObserved;
    
    //Atributos para Observer Objeto Observado
    private Boolean alertSonorous = false;
    private ArrayList designers = new ArrayList();
    
    //Inicilizador
    public Analysis(Webservice wAnalysis){
        //inicializa arquivos necessários
        this.wAnalysis = wAnalysis;
    }
    
    //gets
    public File getValueAnalysis(){
        return this.ValuesAnalysis;
    }
    
    //Setar novo Analyzer
    public void setAnalysis(Webservice wAnalysis){
        this.wAnalysis = wAnalysis;
    } 
    
    //Setar novo objeto observado
    public void setObserver(Monitoring m){
        this.monitorObserved = m;
	monitorObserved.addAnalyzer(this);
    }
    
    public void setValuesAnalysis(String ValuesAnalysis){
        this.ValuesAnalysis = new File(ValuesAnalysis);
        
        //Esvazia arquivo com informações trazidas pelo analise
        try{
        BufferedWriter outAnalysis = new BufferedWriter(new FileWriter(this.ValuesAnalysis.getAbsolutePath()));
        outAnalysis.write("");  
        outAnalysis.flush();
        outAnalysis.close();
        }catch (IOException e){}
    }
    
    //Analisar
    public void analyze(){
        WSFuncs service = new WSFuncs();
        service.fService(wAnalysis, this.ValuesAnalysis.getAbsolutePath(), this.ValuesMonitor.getAbsolutePath());
        alterAlert();
    } 
    
    //Métodos para Observer Observador   
    @Override
    public void update(Monitor m) {
		if(m == monitorObserved){
                    this.ValuesMonitor = monitorObserved.getValuesMonitor();
                    analyze();
		}
	}
 
    //Métodos para Observer Objeto Observado
    public void alterAlert(){
		if(alertSonorous)
			alertSonorous = false;
		else
			alertSonorous = true;
		notifyPlans();
    }

    public Boolean getAlert(){
		return alertSonorous;
    }

    @Override
    public void addDesigner(Designer a) {
            designers.add(a);
    }

    @Override
    public void removeDesigner(Designer a) {
            designers.remove(a);
    }

    private void notifyPlans(){
            Iterator i = designers.iterator();
            while(i.hasNext()){
                    Designer a = (Designer) i.next();
                    a.update(this);
            }
    }
    
}
