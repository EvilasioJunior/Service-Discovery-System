/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author junior
 */
public class Adapter {
    
    private Monitoring monitor;
    private Analysis analyzer;
    private Planning designer;
    private List<Monitoring> monitors;
    private List<Analysis> analyzers;
    private List<Planning> designers;
    private Util usefulfunctions;
    
    //Funcões de cada sistema
    private Boolean proceed;
    private ArrayList specificfunctions = new ArrayList();
    
    //Construtores
    public Adapter(){
        usefulfunctions = new Util();
    }
    
    public Adapter(Monitoring monitor, Analysis analyzer, Planning designer){
        this.monitor = monitor;
        this.analyzer = analyzer;
        this.designer = designer;
        usefulfunctions = new Util();
    }
    
    public Adapter(List<Monitoring> monitors, List<Analysis> analyzers, List<Planning> designers){
        this.monitors = monitors;
        this.analyzers = analyzers;
        this.designers = designers;
        usefulfunctions = new Util();
    }
    
    //gets
    public Monitoring getMonitoring(){
        return this.monitor;
    }
    
    public Analysis getAnalysis(){
        return this.analyzer;
    }
        
    public Planning getPlanning(){
        return this.designer;
    }
    
    public List<Monitoring> getlistMonitoring(){
        return this.monitors;
    }
    
    public List<Analysis> getlistAnalysis(){
        return this.analyzers;
    }
        
    public List<Planning> getlistPlanning(){
        return this.designers;
    }
    
    //sets
    public void setMonitoring(Monitoring monitor){
         this.monitor = monitor;
    }
    
    public void  setAnalysis(Analysis analyzer){
         this.analyzer = analyzer;
    }
        
    public void setPlanning(Planning designer){
         this.designer = designer;
    }
    
    public void setlistMonitoring(List<Monitoring> monitors){
         this.monitors = monitors;
    }
    
    public void  setlistAnalysis(List<Analysis> analyzers){
         this.analyzers = analyzers;
    }
        
    public void setlistPlanning(List<Planning> designers){
         this.designers = designers;
    }
    
    //seta observers
    public void setObserverAnalysis(Monitoring m){
        this.analyzer.setObserver(m);
    }
    
    public void setObserverPlanning(Analysis a){
        this.designer.setObserver(a);
    }
    
    public void setObserverListAnalysis(List<Monitoring> lm){
        for(int i=0; i < lm.size(); i++)
            this.analyzers.get(i).setObserver(lm.get(i));   
    }
    
    public void setObserverListPlanning(List<Analysis> la){
        for(int i=0; i < la.size(); i++)
            this.designers.get(i).setObserver(la.get(i));   
    }
    
    public void setStop(Boolean stop){
        this.proceed = stop;
    }
    
    //Métodos especiais
    
    //Executa adaptação
    public void run(){
        updates();
        while(proceed){
            this.monitor.monitoring();
            updates();
        }
    }
    
    //Executa adaptação usando lista de serviços
    public void runlist(){
        updates();
        while(proceed){
            for(Monitoring maux: this.getlistMonitoring())
                maux.monitoring();
            updates();
        }
    }
    
    //Executa adaptação usando arquivos
    public void runlist(File Monitors, File Analyzers, File Designers, int timeNextExecution){
        updates();
        while(proceed){           
        //obter e setar listas
        setlistMonitoring(usefulfunctions.FileXMLtoMonitors(Monitors));
        setlistAnalysis(usefulfunctions.FileXMLtoAnalyzers(Analyzers));
        setlistPlanning(usefulfunctions.FileXMLtoDesigners(Designers));
        
        //setar tempo monitoramento
        for(Monitoring maux: this.getlistMonitoring())
            maux.setTime(timeNextExecution);
            
        //setar observers
        setObserverListAnalysis(this.getlistMonitoring());
        setObserverListPlanning(this.getlistAnalysis());
        
        //executar adaptação
        //setar tempo monitoramento
        for(Monitoring maux: this.getlistMonitoring())
            maux.monitoring();
        updates();
        }
    }
    
    //Métodos para usar funções específicas do sistema atual
    public void addSpecificFunction(Specific a) {
            specificfunctions.add(a);
    }

    public void removeSpecificFunction(Specific a) {
            specificfunctions.remove(a);
    }
    
    // seta valor da vairável de parada de acordo com um método específico do sistema atual
    private void updates(){
            Iterator i = specificfunctions.iterator();
            while(i.hasNext()){
                    Specific a = (Specific) i.next();
                    a.update(this);
            }
    }
}
