/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author junior
 */
public class WSFuncs {
    
     //Constutor
     public WSFuncs(){}
    
    /* 
     Name: funcService
     Description: Select the Webservice, the data to be sent are on file with webservice information
     */
    public static String funcService(String file) {
        
        String targetName = "", Name= "", url="", Port = "";
        String[] arg0=null;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
            arg0 = ins.readLine().split(";");
        } catch(IOException e){}
        
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(targetName, Name);
         
        try{ 
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
        }
        catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }  
     
    /* 
     Name: funcService (overload)
     */
    public static String funcService(java.lang.String arg0, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(targetName, Name);
         
        try{  
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    } 
    
    /* 
     Name: funcService (overload)
     */
    public static String funcService(java.lang.String arg0, java.lang.String arg1, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(targetName, Name);
         
        try{
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0,arg1);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }
    
     /* 
     Name: funcServiceXML
     Description: Select the Webservice, the data to be sent are on file xml with webservice information
     */
    public static void funcServiceXML(File file) {
        Webservice waux = ServiceToWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String arg0="";
           
        try{
            wsdl = new URL(waux.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        qname = new QName(waux.gettargetName(), waux.getName());

        try{
        WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
        WSInterface port = service.getWSPort();
        port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
        }
    }   
    
    /*
    Name: funcService (overload)
    */
    public static void funcServiceXML(String arg0, File file) {
        Webservice waux = ServiceToWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }

    /*
    Name: funcService (overload)
    */
    public static void funcServiceXML(String arg0, String arg1, File file) {
        Webservice waux = ServiceToWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0,arg1);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
    
    /* 
     Name: funcUniqueService
     Description: Select the Webservice according to the id of the summary of webservices
     */
    public static String funcUniqueService(int id, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        String idaux = "", aux="";
        String[] arg0=null;
        
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            do{
            aux = ins.readLine();
            if(aux.split(" ").length > 1)
                idaux= aux.split(" ")[1];
            }while(!idaux.equals(Integer.toString(id)));
            
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
            
            arg0 = ins.readLine().split(";");
        } catch(IOException e){}
        
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
        qname = new QName(targetName, Name);

        try{
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }
    
    /* 
     Name: funcUniqueService (overload)
     */
    public static String funcUniqueService(java.lang.String arg0, int id, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        String idaux = "", aux="";
        
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));

            do{
            aux = ins.readLine();
            if(aux.split(" ").length > 1)
                idaux= aux.split(" ")[1];
            }while(!idaux.equals(Integer.toString(id)));
            
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
        qname = new QName(targetName, Name);

        try{
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }
    
    /* 
     Name: funcUniqueService (overload)
     */
    public static String funcUniqueService(java.lang.String arg0, java.lang.String arg1, int id, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        String idaux = "", aux="";
        
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));

            do{
            aux = ins.readLine();
            if(aux.split(" ").length > 1)
                idaux= aux.split(" ")[1];
            }while(!idaux.equals(Integer.toString(id)));
            
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
        qname = new QName(targetName, Name);

        try{
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0,arg1);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }
   
     /* 
     Name: funcUniqueServiceXML
     Description: Select the Webservice according to the id of the file xml of webservices
     */
    public static void funcUniqueServiceXML(int id, File file) {
        Webservice waux = SearchServiceByIDXML(Integer.toString(id), file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String arg0="";
           
        try{
            wsdl = new URL(waux.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        qname = new QName(waux.gettargetName(), waux.getName());

        try{
        WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
        WSInterface port = service.getWSPort();
        port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
        }
    }   
   
    /* 
    Name: funcUniqueServiceXML (overload)
    */ 
    public static void funcUniqueServiceXML(String arg0, int id, File file) {
        Webservice waux = SearchServiceByIDXML(Integer.toString(id), file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
   
    /* 
    Name: funcUniqueServiceXML (overload)
    */
    public static void funcUniqueServiceXML(String arg0, String arg1, int id, File file) {
        Webservice waux = SearchServiceByIDXML(Integer.toString(id), file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0,arg1);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
    
    /* 
     Name: funcAllServices
     Description: Execute all webservices file of webservices
    */ 
    public static void funcAllServices(String file) {
        String aux;
        String targetName, Name, url, Port;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String[] arg0=null;
        
        try{
            
        BufferedReader ins = new BufferedReader(new FileReader(file)); 
        aux = ins.readLine(); 
        
        if(aux!=null){
            do{
                targetName = ins.readLine();
                Name = ins.readLine();
                url = ins.readLine();
                Port = ins.readLine();
                arg0 = ins.readLine().split(";");
                try{
                    wsdl = new URL(url);
                    } 
                catch(MalformedURLException e){
                        ex = new WebServiceException(e);
                }

                qname = new QName(targetName, Name);
                
                try{
                WSService service = new WSService(wsdl,qname,targetName,Port);
                WSInterface port = service.getWSPort();
                port.funcService(arg0);
                }catch(Exception e){
                    System.out.println("Unable to run the webservice " + targetName +".\n");
                }

                aux=ins.readLine();
            }while(aux!=null);
        }
        ins.close();
        } catch(IOException e){}
    }
   
    /* 
     Name: funcAllServices (overload)
    */
    public static void funcAllServices(String arg0, String file) {
        String aux;
        String targetName, Name, url, Port;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
            
        BufferedReader ins = new BufferedReader(new FileReader(file)); 
        aux = ins.readLine(); 
        
        if(aux!=null){

            do{
                targetName = ins.readLine();
                Name = ins.readLine();
                url = ins.readLine();
                Port = ins.readLine();
                ins.readLine();
                try{
                    wsdl = new URL(url);
                    } 
                catch(MalformedURLException e){
                        ex = new WebServiceException(e);
                }

                qname = new QName(targetName, Name);
                
                try{
                WSService service = new WSService(wsdl,qname,targetName,Port);
                WSInterface port = service.getWSPort();
                port.funcService(arg0);
                }catch(Exception e){
                    System.out.println("Unable to run the webservice " + targetName +".\n");
                }

                aux=ins.readLine();
            }while(aux!=null);
        }
        ins.close();
        } catch(IOException e){}
    }
   
    /* 
     Name: funcAllServices (overload)
    */ 
    public static void funcAllServices(String arg0, String arg1, String file) {
        String aux;
        String targetName, Name, url, Port;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
            
        BufferedReader ins = new BufferedReader(new FileReader(file)); 
        aux = ins.readLine(); 
        
        if(aux!=null){
            
            do{
                targetName = ins.readLine();
                Name = ins.readLine();
                url = ins.readLine();
                Port = ins.readLine();
                ins.readLine();
                try{
                    wsdl = new URL(url);
                    } 
                catch(MalformedURLException e){
                        ex = new WebServiceException(e);
                }

                qname = new QName(targetName, Name);
                
                try{
                WSService service = new WSService(wsdl,qname,targetName,Port);
                WSInterface port = service.getWSPort();
                port.funcService(arg0,arg1);
                }catch(Exception e){
                    System.out.println("Unable to run the webservice " + targetName +".\n");
                }

                aux=ins.readLine();
            }while(aux!=null);
        }
        ins.close();
        } catch(IOException e){}
    }
    
     /* 
     Name: funcAllServicesXML
     Description: Execute all webservices file xml of webservices
    */
    public static void funcAllServicesXML(File file) {
        List<Webservice> lWaux = ServiceToListWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String arg0="";
        
        for(Webservice waux: lWaux){

           try{
                wsdl = new URL(waux.geturl());
                } 
            catch(MalformedURLException e){
                    ex = new WebServiceException(e);
            }
            qname = new QName(waux.gettargetName(), waux.getName());

            try{
            WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
            WSInterface port = service.getWSPort();
            port.funcService(arg0);
            }catch(Exception e){
                System.out.println("Unable to run the webservice " + waux.getName() +".\n");
            }
        }
    }

    /* 
     Name: funcAllServicesXML
    */
    public static void funcAllServicesXML(String arg0, File file) {
        List<Webservice> lWaux = ServiceToListWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        for(Webservice waux: lWaux){
 
           try{
                wsdl = new URL(waux.geturl());
                } 
            catch(MalformedURLException e){
                    ex = new WebServiceException(e);
            }
            qname = new QName(waux.gettargetName(), waux.getName());

            try{
            WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
            WSInterface port = service.getWSPort();
            port.funcService(arg0);
            }catch(Exception e){
                System.out.println("Unable to run the webservice " + waux.getName() +".\n");
            }
        }
    }

    /* 
     Name: funcAllServicesXML
    */
    public static void funcAllServicesXML(String arg0, String arg1, File file) {
        List<Webservice> lWaux = ServiceToListWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        for(Webservice waux: lWaux){
           try{
                wsdl = new URL(waux.geturl());
                } 
            catch(MalformedURLException e){
                    ex = new WebServiceException(e);
            }
            qname = new QName(waux.gettargetName(), waux.getName());

            try{
            WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
            WSInterface port = service.getWSPort();
            port.funcService(arg0,arg1);
            }catch(Exception e){
                System.out.println("Unable to run the webservice " + waux.getName() +".\n");
            }
        }
    }
   
     /* 
     Name: fService
     Descrição: Calls the webservice informed through the indicated parameters
     */
    public static String fService(Webservice wService, String[] arg0) {
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        try{
            wsdl = new URL(wService.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(wService.gettargetName(), wService.getName());
         
        WSService service = new WSService(wsdl,qname,wService.gettargetName(),wService.getPort());
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
    }
    
     /* 
     Name: fService (overload)
     */
     public static String fService(Webservice wService, String arg0)  {   
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        try{
            wsdl = new URL(wService.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(wService.gettargetName(), wService.getName());
         
        WSService service = new WSService(wsdl,qname,wService.gettargetName(),wService.getPort());
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
    }
     
     /* 
     Name: fService (overload)
     */
     public static String fService(Webservice wService, String arg0, String arg1) {
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        try{
            wsdl = new URL(wService.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(wService.gettargetName(), wService.getName());
         
        WSService service = new WSService(wsdl,qname,wService.gettargetName(),wService.getPort());
        WSInterface port = service.getWSPort();
        return port.funcService(arg0, arg1);
    }
     
     /* 
     Name: SearchServiceByID 
     Description: Search a service within of a file
     */
     public static Webservice SearchServiceByID (String id, File Services){
        
        Webservice Waux = new Webservice();
        String aux, aux2, idaux  ="";
        
        try{  
        BufferedReader in = new BufferedReader(new FileReader(Services.getAbsolutePath()));        
        do{
        aux = in.readLine();
        if(aux!=null){ 
            if(aux.split(" ").length > 1)
                idaux = aux.split(" ")[1];
        }
        else
           break;
        }while(!idaux.equals(id));

        if(aux==null){
            Waux = null;
        }
        else {
            Waux.settargetName(in.readLine());
            Waux.setName(in.readLine());
            Waux.seturl(in.readLine());
            Waux.setPort(in.readLine());
            
            aux2 = in.readLine();
            if (!(aux2 == null || aux2.equals("")))
                Waux.addarguments(aux2);
        }
      }catch(IOException e){}
       
      return Waux;
    }
    
     /* 
     Name: SearchServiceByIDXML 
     Description: Search a service within of a file XML
     */
     public static Webservice SearchServiceByIDXML (String id, File Services){   
        int aux=0, idaux=Integer.parseInt(id);
        
        //Configuring for use of xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(Services);
        while(lWaux.get(aux).getid()!=idaux)
            aux++;
        Webservice Waux = lWaux.get(aux);
        
        return Waux;
    }
    
     /* 
     Nome: ServiceToWebservice
     Description: Retorn a services of a file XML
     */
     public static Webservice ServiceToWebservice (File Services){   
        //Configuring for use of xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(Services);
        Webservice Waux = lWaux.get(0);
        
        return Waux;
    } 
     
     /* 
     Name: ServiceToWebservice
     Description: Retorn a list of services of a file XML
     */
     public static List<Webservice> ServiceToListWebservice (File Services){   
        //Configuring for use of xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(Services);
        
        return lWaux;
    } 
}