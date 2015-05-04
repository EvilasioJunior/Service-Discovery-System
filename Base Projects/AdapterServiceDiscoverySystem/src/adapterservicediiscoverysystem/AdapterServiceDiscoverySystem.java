/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapterservicediiscoverysystem;
import adapter.Adapter;
import adapter.Planning;
import adapter.Monitoring;
import adapter.Analysis;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Genshiken
 */
public class AdapterServiceDiscoverySystem{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
            //Useful Functions
            AdapterObserver sf = new AdapterObserver();

            //Set Monitoring, Analysis e Planning
            //---List monitors, analyzers and planners----//
            List<Monitoring> lstmonitor = new ArrayList<Monitoring>();
            List<Analysis> lstanalyzers = new ArrayList<Analysis>();
            List<Planning> lstdesigners = new ArrayList<Planning>();
            
            //---Remove unavailable services----//
            Monitoring monitorr = new SpecificMonitorRemove();    
            Analysis analyzerr = new SpecificAnalyzerRemove();
            Planning plannerr = new SpecificPlannerRemove();
            
            //--Add new available services--//
            Monitoring monitora = new SpecificMonitorAdd();    
            Analysis analyzera = new SpecificAnalyzerAdd();
            Planning plannera = new SpecificPlannerAdd();
            
            //Configure monitors
            monitorr.setValuesMonitor("ValuesMonitor.xml"); 
            monitora.setValuesMonitor("ValuesMonitora.xml");
            
            //Configure analyzers
            analyzerr.setValuesAnalysis("ValuesAnalysis.xml"); 
            analyzerr.setObserver(monitorr);
            analyzera.setValuesAnalysis("ValuesAnalysisa.xml");
            analyzera.setObserver(monitora);            
            
            //Configure planners
            plannerr.setObserver(analyzerr);
            plannera.setObserver(analyzera);
            
            //Add monitors, analyzers and planners to list
            lstmonitor.add(monitorr); lstmonitor.add(monitora);
            lstanalyzers.add(analyzerr); lstanalyzers.add(analyzera);
            lstdesigners.add(plannerr); lstdesigners.add(plannera);
  
            //Set Adapter
            Adapter adapter = new Adapter(lstmonitor, lstanalyzers, lstdesigners);
            
            //Set Specific Functions Observer
            sf.setObserver(adapter);
            
            adapter.run();
    }
}
