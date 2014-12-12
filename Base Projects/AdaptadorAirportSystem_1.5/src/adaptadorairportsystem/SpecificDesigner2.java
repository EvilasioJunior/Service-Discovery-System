/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;

import adapter.Planning;
import webservice.Webservice;

/**
 *
 * @author Evilasio
 */
public class SpecificDesigner2 extends Planning{
    
    public SpecificDesigner2(Webservice wDesigner){
        this.setPlanning(wDesigner);
    }
    
    public SpecificDesigner2(){
    }
    
    public void plan(){
        this.planWebservice();
    }
}
