/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author junior
 */
public class Webservice {
    
    private int id;
    private String Description;
    private String targetName;
    private String Name;
    private String url; 
    private String Port;
    private ArrayList<String> arguments;
    
    public Webservice(String wtargetName, String wName, String wurl, String wPort){
        this.targetName = wtargetName;
        this.Name = wName;
        this.url = wurl;
        this.Port = wPort;
        arguments = new ArrayList<String> ();
        id = 0;
        Description = "";
    }
    
    public Webservice(){
        arguments = new ArrayList<String> ();
        id = 0;
        Description = "";
    }
    
    public String gettargetName(){
        return this.targetName;
    }
    
    public String getName(){
        return this.Name;
    }
        
    public String geturl(){
        return this.url;
    }
            
    public String getPort(){
        return this.Port;
    }
    
    public int getid(){
        return this.id;
    }
    
    public String getDescription(){
        return this.Description;
    }
    
    public ArrayList<String> getarguments(){
        return this.arguments;
    }
    
    public void settargetName(String targetName){
        this.targetName = targetName;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }
        
    public void seturl(String url){
        this.url = url;
    }
            
    public void setPort(String Port){
        this.Port = Port;
    }
    
    public void setid(int id){
        this.id = id;
    }
    
    public void setDescription(String Description){
        this.Description = Description;
    }
    
    public void setarguments(ArrayList<String> Arguments){
        this.arguments = Arguments;
    }
    
    public void addarguments(String Argument){
       this.arguments.add(Argument);
    }
    
    public void initializerBySocket(int porta) throws IOException{
        ServerSocket servidor = new ServerSocket(porta);
        Socket cliente = servidor.accept();
        Scanner s = new Scanner(cliente.getInputStream());
        String []aux = s.nextLine().split(";");
        
        settargetName(aux[0]);
        setName(aux[1]); 
        seturl(aux[2]); 
        setPort(aux[3]);
        if (aux.length >4)
            addarguments(aux[4]);
        
        s.close();
        cliente.close();
        servidor.close();
        
    }
}
