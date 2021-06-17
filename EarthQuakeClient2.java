package EarthquakeFilterStarterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        Filter f = new MinMagFilter(4.0); 
        
        Filter f1 = new MagnitudeFilter(3.5,4.5);
        ArrayList<QuakeEntry> qeAfterF1 = filter(list, f1);
        Filter f2 = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> qeAfterF2 = filter(qeAfterF1, f2);
        System.out.println("After using DepthFilter and MagnitudeFilter");
        
        for(QuakeEntry qe : qeAfterF2){
            System.out.println(qe);
        }
        System.out.println("Found "+qeAfterF2.size()+" that matches the criteria");
        /*ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) 
            System.out.println(qe);*/
            
        } 
    
    
    /*public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        //Location of Tokyo, Japan
        Location denver = new Location(39.7392, -104.9903);
        Filter f1 = new DistanceFilter(denver, 1000000);
        ArrayList<QuakeEntry> qeAfterF1 = filter(list, f1);
        Filter f2 = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> qeAfterF2 = filter(qeAfterF1, f2);
        for (QuakeEntry qe : qeAfterF2){
            //System.out.print(denver.distanceTo(qe.getLocation()) +"  ");
            System.out.println(qe);
        }
        System.out.println("Found "+qeAfterF2.size()+" earthquakes that matches the criteria");
    }*/

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
