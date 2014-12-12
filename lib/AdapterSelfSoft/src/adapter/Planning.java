/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import java.io.File;
import webservice.WSFuncs;
import webservice.Webservice;

/**
 *
 * @author junior
 */
public class Planning implements Designer{

    private File ValuesAnalysis;
    private Webservice wPlanning;
   
    //Atributos para Observer Observador
    private Analysis analyzerObserved; 
    
    //Inicilizador
    public Planning(Webservice wPlanning){
        //Inicializa arquivo necessário
        this.wPlanning = wPlanning; 
    }
    
    //Setar novo planjedaor
    public void setPlanning(Webservice wPlanning){
        this.wPlanning = wPlanning;
    }
    
    //Setar novo objeto observado
    public void setObserver(Analysis a){
        this.analyzerObserved = a;
	analyzerObserved.addDesigner(this);
    }
    
    //Planejar
    public void plan(){
        WSFuncs service = new WSFuncs();
        service.fService(wPlanning, this.ValuesAnalysis.getAbsolutePath());
    }
    
    //Métodos para Observer Observador   
    @Override
    public void update(Analyzer m) {
		if(m == analyzerObserved){
                    this.ValuesAnalysis = analyzerObserved.getValueAnalysis();
                    plan();
		}
	}

}
