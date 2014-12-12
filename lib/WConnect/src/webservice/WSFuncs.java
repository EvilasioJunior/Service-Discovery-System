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
     Nome: funcService (Sobrecarga)
     Parâmetros: file (informa file que contém as informaçãoes do webservice)
     Descrição: Seleciona o Webservice, os dados a serem enviados estão no rquivo com informações do webservice
     */
    public static String funcService(String file) {
        
        String targetName = "", Name= "", url="", Port = "";
        String[] arg0=null;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        //Obter dados do webservice
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
            arg0 = ins.readLine().split(";");
        } catch(IOException e){}
        
        //Se o file está vazio retorna nulo
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(targetName, Name);
         
        //Acionando o serviço
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
     Nome: funcService (Sobrecarga)
     Parâmetros: arg0 (string para werbservice),  file (informa file que contém as informaçãoes do webservice)
     Descrição: Seleciona o Webservice
     */
    public static String funcService(java.lang.String arg0, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        //Obter dados do webservice
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        //Se o file está vazio retorna nulo
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(targetName, Name);
         
        //Acionando o serviço
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
     Nome: funcService (Sobrecarga)
     Parâmetros: arg0, arg1 (strings para werbservice),  file (informa file que contém as informaçãoes do webservice)
     Descrição: Seleciona o Webservice
     */
    public static String funcService(java.lang.String arg0, java.lang.String arg1, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        //Obter dados do webservice
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        //Se o file está vazio retorna nulo
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
         qname = new QName(targetName, Name);
         
        //Acionando o serviço
        try{
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0,arg1);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }
    
    public static void funcServiceXML(File file) {
        Webservice waux = ServiceToWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String arg0="";
           
        //Adicionando dados do serviço solicitado ao file 
        try{
            wsdl = new URL(waux.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        qname = new QName(waux.gettargetName(), waux.getName());

        //Executando webservices
        try{
        WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
        WSInterface port = service.getWSPort();
        port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
        }
    }   
   
    public static void funcServiceXML(String arg0, File file) {
        Webservice waux = ServiceToWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        //Adicionando dados do serviço solicitado ao file 
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         //Executando webservices
         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
   
    public static void funcServiceXML(String arg0, String arg1, File file) {
        Webservice waux = ServiceToWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        //Adicionando dados do serviço solicitado ao file 
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         //Executando webservices
         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0,arg1);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
    
    /* 
     Nome: funcUniqueService
     Parâmetros: id, file (informa file de serviços diferente do padrão)
     Descrição: Seleciona o Webservice de acordo com o id do sumario de webservices
     */
    public static String funcUniqueService(int id, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        String idaux = "", aux="";
        String[] arg0=null;
        
        //Obter dados do webservice
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            //Procurar webservice
            do{
            aux = ins.readLine();
            if(aux.split(" ").length > 1)
                idaux= aux.split(" ")[1];
            }while(!idaux.equals(Integer.toString(id)));
            
            //Obter dados do webservice
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
            //Obter argumentos
            arg0 = ins.readLine().split(";");
        } catch(IOException e){}
        
        //Se o file está vazio retorna nulo
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
        qname = new QName(targetName, Name);
        //Acionando o serviço
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
     Nome: funcUniqueService
     Parâmetros: arg0 (string para werbservice), idservice, file (informa file de serviços diferente do padrão)
     Descrição: Seleciona o Webservice de acordo com o id do sumario de webservices
     */
    public static String funcUniqueService(java.lang.String arg0, int id, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        String idaux = "", aux="";
        
        //Obter dados do webservice
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            //Procurar webservice
            do{
            aux = ins.readLine();
            if(aux.split(" ").length > 1)
                idaux= aux.split(" ")[1];
            }while(!idaux.equals(Integer.toString(id)));
            
            //Obter dados do webservice
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        //Se o file está vazio retorna nulo
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
        qname = new QName(targetName, Name);
        //Acionando o serviço
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
     Nome: funcUniqueService (Sobrecarga)
     Parâmetros: arg0,arg1 (strings para werbservice), idservice, file (informa file de serviços diferente do padrão)
     Descrição: Seleciona o Webservice de acordo com o id do sumario de webservices
     */
    public static String funcUniqueService(java.lang.String arg0, java.lang.String arg1, int id, String file) {
        String targetName = "", Name= "", url="", Port = "";
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        String idaux = "", aux="";
        
        //Obter dados do webservice
        try{
            BufferedReader ins = new BufferedReader(new FileReader(file));
            //Procurar webservice
            do{
            aux = ins.readLine();
            if(aux.split(" ").length > 1)
                idaux= aux.split(" ")[1];
            }while(!idaux.equals(Integer.toString(id)));
            
            //Obter dados do webservice
            targetName = ins.readLine();
            Name = ins.readLine();
            url = ins.readLine();
            Port = ins.readLine();
        } catch(IOException e){}
        
        //Se o file está vazio retorna nulo
        if(targetName==null)
            return null;
        
        try{
            wsdl = new URL(url);
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        
        qname = new QName(targetName, Name);
        //Acionando o serviço
        try{
        WSService service = new WSService(wsdl,qname,targetName,Port);
        WSInterface port = service.getWSPort();
        return port.funcService(arg0,arg1);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + targetName +".\n");
            return null;
        }
    }
    
    public static void funcUniqueServiceXML(int id, File file) {
        Webservice waux = SearchServiceByIDXML(Integer.toString(id), file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String arg0="";
           
        //Adicionando dados do serviço solicitado ao file 
        try{
            wsdl = new URL(waux.geturl());
            } 
        catch(MalformedURLException e){
                ex = new WebServiceException(e);
        }
        qname = new QName(waux.gettargetName(), waux.getName());

        //Executando webservices
        try{
        WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
        WSInterface port = service.getWSPort();
        port.funcService(arg0);
        }catch(Exception e){
            System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
        }
    }   
   
    public static void funcUniqueServiceXML(String arg0, int id, File file) {
        Webservice waux = SearchServiceByIDXML(Integer.toString(id), file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        //Adicionando dados do serviço solicitado ao file 
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         //Executando webservices
         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
   
    public static void funcUniqueServiceXML(String arg0, String arg1, int id, File file) {
        Webservice waux = SearchServiceByIDXML(Integer.toString(id), file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        //Adicionando dados do serviço solicitado ao file 
        try{
             wsdl = new URL(waux.geturl());
             } 
         catch(MalformedURLException e){
                 ex = new WebServiceException(e);
         }
         qname = new QName(waux.gettargetName(), waux.getName());

         //Executando webservices
         try{
         WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
         WSInterface port = service.getWSPort();
         port.funcService(arg0,arg1);
         }catch(Exception e){
             System.out.println("Unable to run the webservice " + waux.gettargetName() +".\n");
         }
    }
    
    /* 
     Nome: funcAllService (Sobrecarga)
     Parâmetros: arg0 (string para werbservice), file (informa file de serviços diferente do padrão)
     Descrição: Executa todos os webservices  do sumario de webservices
    */ 
    public static void funcAllServices(String file) {
        String aux;
        String targetName, Name, url, Port;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String[] arg0=null;
        
        //Adicionando dados do serviço solicitado ao file
        try{
            
        BufferedReader ins = new BufferedReader(new FileReader(file)); 
        aux = ins.readLine(); //Pirmeiro id
        
        //verifica se já está no final do file
        if(aux!=null){
            //Executando webservices
            do{
                //Obter dados do webservice
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
                
                //Acionando o serviço
                try{
                WSService service = new WSService(wsdl,qname,targetName,Port);
                WSInterface port = service.getWSPort();
                port.funcService(arg0);
                }catch(Exception e){
                    System.out.println("Unable to run the webservice " + targetName +".\n");
                }

                aux=ins.readLine();//próximo id
            }while(aux!=null);
        }
        ins.close();
        } catch(IOException e){}
    }
   
    /* 
     Nome: funcAllService (Sobrecarga)
     Parâmetros: arg0 (string para werbservice), file (informa file de serviços diferente do padrão)
     Descrição: Executa todos os webservices  do sumario de webservices
    */ 
    public static void funcAllServices(String arg0, String file) {
        String aux;
        String targetName, Name, url, Port;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        //Adicionando dados do serviço solicitado ao file
        try{
            
        BufferedReader ins = new BufferedReader(new FileReader(file)); 
        aux = ins.readLine(); //Pirmeiro id
        
        //verifica se já está no final do file
        if(aux!=null){
            //Executando webservices
            do{
                //Obter dados do webservice
                targetName = ins.readLine();
                Name = ins.readLine();
                url = ins.readLine();
                Port = ins.readLine();
                ins.readLine();//Linha dos argumentos (não utilizados nesse método)
                try{
                    wsdl = new URL(url);
                    } 
                catch(MalformedURLException e){
                        ex = new WebServiceException(e);
                }

                qname = new QName(targetName, Name);
                
                //Acionando o serviço
                try{
                WSService service = new WSService(wsdl,qname,targetName,Port);
                WSInterface port = service.getWSPort();
                port.funcService(arg0);
                }catch(Exception e){
                    System.out.println("Unable to run the webservice " + targetName +".\n");
                }

                aux=ins.readLine();//próximo id
            }while(aux!=null);
        }
        ins.close();
        } catch(IOException e){}
    }
   
   /* 
     Nome: funcAllService (Sobrecarga)
     Parâmetros: arg0 (string para werbservice), file (informa file de serviços diferente do padrão)
     Descrição: Executa todos os webservices  do sumario de webservices
    */ 
    public static void funcAllServices(String arg0, String arg1, String file) {
        String aux;
        String targetName, Name, url, Port;
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        //Adicionando dados do serviço solicitado ao file
        try{
            
        BufferedReader ins = new BufferedReader(new FileReader(file)); 
        aux = ins.readLine(); //Pirmeiro id
        
        //verifica se já está no final do file
        if(aux!=null){
            //Executando webservices
            do{
                //Obter dados do webservice
                targetName = ins.readLine();
                Name = ins.readLine();
                url = ins.readLine();
                Port = ins.readLine();
                ins.readLine();//Linha dos argumentos (não utilizados nesse método)
                try{
                    wsdl = new URL(url);
                    } 
                catch(MalformedURLException e){
                        ex = new WebServiceException(e);
                }

                qname = new QName(targetName, Name);
                
                //Acionando o serviço
                try{
                WSService service = new WSService(wsdl,qname,targetName,Port);
                WSInterface port = service.getWSPort();
                port.funcService(arg0,arg1);
                }catch(Exception e){
                    System.out.println("Unable to run the webservice " + targetName +".\n");
                }

                aux=ins.readLine();//próximo id
            }while(aux!=null);
        }
        ins.close();
        } catch(IOException e){}
    }
   
    public static void funcAllServicesXML(File file) {
        List<Webservice> lWaux = ServiceToListWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        String arg0="";
        
        for(Webservice waux: lWaux){
           //Adicionando dados do serviço solicitado ao file 
           try{
                wsdl = new URL(waux.geturl());
                } 
            catch(MalformedURLException e){
                    ex = new WebServiceException(e);
            }
            qname = new QName(waux.gettargetName(), waux.getName());

            //Executando webservices
            try{
            WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
            WSInterface port = service.getWSPort();
            port.funcService(arg0);
            }catch(Exception e){
                System.out.println("Unable to run the webservice " + waux.getName() +".\n");
            }
        }
    }
   
    public static void funcAllServicesXML(String arg0, File file) {
        List<Webservice> lWaux = ServiceToListWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname = null;
        
        for(Webservice waux: lWaux){
           //Adicionando dados do serviço solicitado ao file 
           try{
                wsdl = new URL(waux.geturl());
                } 
            catch(MalformedURLException e){
                    ex = new WebServiceException(e);
            }
            qname = new QName(waux.gettargetName(), waux.getName());

            //Executando webservices
            try{
            WSService service = new WSService(wsdl,qname,waux.gettargetName(),waux.getPort());
            WSInterface port = service.getWSPort();
            port.funcService(arg0);
            }catch(Exception e){
                System.out.println("Unable to run the webservice " + waux.getName() +".\n");
            }
        }
    }
   
    public static void funcAllServicesXML(String arg0, String arg1, File file) {
        List<Webservice> lWaux = ServiceToListWebservice(file);
        URL wsdl = null;
        WebServiceException ex = null;
        QName qname;
        
        for(Webservice waux: lWaux){
           //Adicionando dados do serviço solicitado ao file 
           try{
                wsdl = new URL(waux.geturl());
                } 
            catch(MalformedURLException e){
                    ex = new WebServiceException(e);
            }
            qname = new QName(waux.gettargetName(), waux.getName());

            //Executando webservices
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
     Nome: fService (Sobrecarga)
     Parâmetros: Inforções de acesso ao webervice e argumentos para o webservice
     Descrição: Chama o webservice informado passando os parâmetros indicados
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
         
        //Acionando o serviço
        //try{
        WSService service = new WSService(wsdl,qname,wService.gettargetName(),wService.getPort());
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
        //}catch(Exception e){
        //    System.out.println("Não é possível executar o webservice" + wService.getName() +".\n");
        //    return null;
        //}
    }
    
     /* 
     Nome: fService (Sobrecarga)
     Parâmetros: Inforções de acesso ao webervice e argumentos para o webservice
     Descrição: Chama o webservice informado passando os parâmetros indicados
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
         
        //Acionando o serviço
       // try{
        WSService service = new WSService(wsdl,qname,wService.gettargetName(),wService.getPort());
        WSInterface port = service.getWSPort();
        return port.funcService(arg0);
        //}catch(Exception e){
        //    System.out.println("Não é possível executar o webservice" + wService.getName() +".\n");
        //    return null;
        //}
    }
     
     /* 
     Nome: fService (Sobrecarga)
     Parâmetros: Inforções de acesso ao webervice e argumentos para o webservice
     Descrição: Chama o webservice informado passando os parâmetros indicados
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
         
        //Acionando o serviço
        //try{
        WSService service = new WSService(wsdl,qname,wService.gettargetName(),wService.getPort());
        WSInterface port = service.getWSPort();
        return port.funcService(arg0, arg1);
        //}catch(Exception e){
        //   System.out.println("Não é possível executar o webservice" + wService.getName() +".\n");
        //   return null;
       // }
    }
     
    /* 
     Nome: SearchServiceByID 
     Parâmetros: id e Arquivo
     Descrição: Busca uma serviço dentro de um file
     */
     public static Webservice SearchServiceByID (String id, File Services){
        
        Webservice Waux = new Webservice();
        String aux, aux2, idaux  ="";
        
        try{  
        BufferedReader in = new BufferedReader(new FileReader(Services.getAbsolutePath()));        
        //Procurar webservice
        do{
        aux = in.readLine();
        if(aux!=null){ 
            if(aux.split(" ").length > 1)
                idaux = aux.split(" ")[1];
        }
        else
           break;
        }while(!idaux.equals(id));

        //Verifica se id informado é válido
        if(aux==null){
            Waux = null;
        }
        else {
            //Informações do serviço escolhido
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
     Nome: SearchServiceByIDXML 
     Parâmetros: id e Arquivo
     Descrição: Busca uma serviço dentro de um file XML
     */
     public static Webservice SearchServiceByIDXML (String id, File Services){   
        int aux=0, idaux=Integer.parseInt(id);
        
        //Configurações para uso do xml
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
     Parâmetros: Arquivo
     Descrição: Retorna um serviço de um file XML
     */
     public static Webservice ServiceToWebservice (File Services){   
        //Configurações para uso do xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(Services);
        Webservice Waux = lWaux.get(0);
        
        return Waux;
    } 
     
     /* 
     Nome: ServiceToWebservice
     Parâmetros: Arquivo
     Descrição: Retorna uma lista de serviços de um file XML
     */
     public static List<Webservice> ServiceToListWebservice (File Services){   
        //Configurações para uso do xml
        XStream parser = new XStream(new DomDriver());
        parser.setMode(XStream.NO_REFERENCES);
        List<Webservice> lWaux = (List<Webservice>)parser.fromXML(Services);
        
        return lWaux;
    } 
}