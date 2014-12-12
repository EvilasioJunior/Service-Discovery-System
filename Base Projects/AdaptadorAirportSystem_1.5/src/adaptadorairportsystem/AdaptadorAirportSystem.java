/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adaptadorairportsystem;
import adapter.Adapter;
import adapter.Planning;
import adapter.Monitoring;
import adapter.Analysis;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import webservice.Webservice;
/**
 *
 * @author Genshiken
 */
public class AdaptadorAirportSystem{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
            //Serviços
            Webservice wMonitoramentor, wMonitoramentoa;
            Webservice wAnaliser, wAnalisea;
            Webservice wPlanejamentor, wPlanejamentoa;
            
            //Funções úteis
            SpecificFunction sf = new SpecificFunction();
            SpecificFunction sf1 = new SpecificFunction();
            SpecificFunction sf2 = new SpecificFunction();
            
            //---Retirando serviços indisponíveis----//
            wMonitoramentor = new Webservice("http://jr.com/","AirPortMonitorOneService","http://localhost:8080/AirPortServices/AirPortMonitorOne?wsdl", "AirPortMonitorOnePort");
            wAnaliser = new Webservice("http://jr.com/","AirPortAnalisadorOneService","http://localhost:8080/AirPortServices/AirPortAnalisadorOne?wsdl", "AirPortAnalisadorOnePort");
            wPlanejamentor = new Webservice("http://jr.com/","AirPortPlanejadorOneService","http://localhost:8080/AirPortServices/AirPortPlanejadorOne?wsdl", "AirPortPlanejadorOnePort");
            
            //--Adicionando novos serviços disponíveis--//
            wMonitoramentoa = new Webservice("http://jr.com/","AirPortMonitorTwoService","http://localhost:8080/AirPortServices/AirPortMonitorTwo?wsdl", "AirPortMonitorTwoPort");
            wAnalisea = new Webservice("http://jr.com/","AirPortAnalisadorTwoService","http://localhost:8080/AirPortServices/AirPortAnalisadorTwo?wsdl", "AirPortAnalisadorTwoPort");
            wPlanejamentoa = new Webservice("http://jr.com/","AirPortPlanejadorTwoService","http://localhost:8080/AirPortServices/AirPortPlanejadorTwo?wsdl", "AirPortPlanejadorTwoPort");
            
            //Setando Monitoring, Analysis e Planning
            Monitoring monitorr = new SpecificMonitor(wMonitoramentor);    
            Analysis analisadorr = new SpecificAnalyzer(wAnaliser);
            Planning planejadorr = new SpecificDesigner(wPlanejamentor);
            
            Monitoring monitora = new SpecificMonitor1(wMonitoramentoa);    
            Analysis analisadora = new SpecificAnalyzer1(wAnalisea);
            Planning planejadora = new SpecificDesigner1(wPlanejamentoa);
            
            //Configurando monitores
            monitorr.setValuesMonitor("ValoresMonitor.xml"); //Informando arquivo que contará com os valores de monitoramento
            monitora.setValuesMonitor("ValoresMonitora.xml"); //Informando arquivo que contará com os valores de monitoramento
            
            //Configurar analisadores
            analisadorr.setValuesAnalysis("ValoresAnalise.xml"); //Informando arquivo que contará com os valores de analise
            analisadorr.setObserver(monitorr);
            analisadora.setValuesAnalysis("ValoresAnalisea.xml"); //Informando arquivo que contará com os valores de analise
            analisadora.setObserver(monitora);            
            
            //Configurando planejadores
            planejadorr.setObserver(analisadorr);
            planejadora.setObserver(analisadora);
            
            
            //Files
            File SumarioPlanos = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AdaptadorAirportSystem_1.5\\SumarioPlanos.xml");
            File SumarioMonitor = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AdaptadorAirportSystem_1.5\\SumarioMonitor.xml");
            File SumarioAnalise = new File("C:\\Users\\Evilasio\\Documents\\NetBeansProjects\\AdaptadorAirportSystem_1.5\\SumarioAnalise.xml");
            
            //Set for Util
            Monitoring monitoraux = new SpecificMonitor2();    
            Analysis analisadoraux = new SpecificAnalyzer2();
            Planning planejadoraux = new SpecificDesigner2();
            
            //Set attributes of object of type SpecificFunction for use Files
            sf2.setAttributes(monitoraux, analisadoraux, planejadoraux);

            //Set list based to Files
            List<Monitoring> monitores = sf2.FileXMLtoMonitors(SumarioMonitor);
            List<Analysis> analisadores = sf2.FileXMLtoAnalyzers(SumarioAnalise);
            List<Planning> planejadores = sf2.FileXMLtoDesigners(SumarioPlanos);
            
            //Setando Adapters
            Adapter adaptador = new Adapter(monitorr, analisadorr, planejadorr);
            Adapter adaptador1 = new Adapter(monitora, analisadora, planejadora);
            Adapter adaptador2 = new Adapter(monitores, analisadores, planejadores);
            
            //Set list observer
            adaptador2.setObserverListAnalysis(monitores);
            adaptador2.setObserverListPlanning(analisadores);
            
            //Setando Funções Específicas dessa adaptação, como o critério de parada do adaptador
            //A execução dessas funções são disparadas pelo adaptador
            sf.setObserver(adaptador);
            sf1.setObserver(adaptador1);
            sf2.setObserver(adaptador2);
            
            adaptador.run();
            adaptador1.run();
            adaptador2.runlist();
    }
}
