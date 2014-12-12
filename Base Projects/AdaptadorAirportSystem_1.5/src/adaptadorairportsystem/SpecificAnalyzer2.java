/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

import adapter.Analysis;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificAnalyzer2 extends Analysis{

    public SpecificAnalyzer2(Webservice wAnalyzer){
        this.setAnalysis(wAnalyzer);
    }
    
    public SpecificAnalyzer2(){
    }

    public void analyze(){
        this.analyzeWebservice();
        this.alterAlert();
    }
     
}
