/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterservicediiscoverysystem;

import adapter.AbstractAdapterObserver;
import adapter.Adapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Evilasio
 */
public class AdapterObserver implements AbstractAdapterObserver{
    
    //Attributes for Observer
    private Adapter adapterObserved;
    
    public AdapterObserver(){}
     
    //Set new observed object
    public void setObserver(Adapter a){
        this.adapterObserved = a;
	adapterObserved.addAdapterObserver(this);
    }
    
    //Update Observer    
    @Override
    public void update(Adapter a) {
		if(a == adapterObserved){
                    adapterObserved.setStop(verify());
		}
    }
    
    //-----Specific Functions-------//
    public Boolean verify(){
        File sf = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AirportSystem\\StopFile.txt");
        String sbaux = "";
        Boolean baux = true;
        //Read the file of information about stop criteria of Adapter
        try{       
        BufferedReader in = new BufferedReader(new FileReader(sf));
        sbaux = in.readLine();
        in.close();
        }catch (IOException e){}
        
        if(sbaux!=null){
            if (!sbaux.equals(""))
                baux = false;
        }
        
        //Write in the file of information about stop criteria of Adapter
        try{       
        BufferedWriter out = new BufferedWriter(new FileWriter(sf));
        out.write(""); 
        out.close();
        }catch (IOException e){}
        
        return baux;
    }
    
}
