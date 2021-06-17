package SearchingEarthquakeDataStarterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for ( QuakeEntry qe : quakeData){
            if( qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
            if (loc.distanceTo(from) < distMax){
                //System.out.println(loc.distanceTo(from));
                answer.add(qe);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth (ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            double curDepth = qe.getDepth();
            if (curDepth > minDepth && curDepth < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, 
    String where,String phrase ){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData){
            String title = qe.getInfo();
            if (where.equals("start")){
                if (title.startsWith(phrase)){
                    answer.add(qe);
                }
            }
             else if (where.equals("any")){
                 if (title.contains(phrase)){
                     answer.add(qe);
                }
            }
             else {
                 if (title.endsWith(phrase)){
                     answer.add(qe);
                    }
                }
            }
            return answer;
        }
        
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source ="data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
       
        // Testing the filterByPhrase method
        
        ArrayList<QuakeEntry> qeFilteredPhrase = filterByPhrase(list, "any", "Can");
        for(QuakeEntry qe : qeFilteredPhrase){
            System.out.println(qe);
        }
        System.out.println("Found "+qeFilteredPhrase.size()+" quakes that match the criteria");
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list =parser.read(source);
        //Testing filterByDepth method
        
        ArrayList<QuakeEntry> qeFilteredDepth = filterByDepth(list, -4000.0, -2000.0);
        System.out.println("ind quakes with depth between -4000.0 and -2000.0");
        
        for (QuakeEntry qe : qeFilteredDepth){
            System.out.println(qe);
        }
        System.out.println("Found "+qeFilteredDepth.size()+" quakes that match the criteria");
    }
    

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> qeList = filterByMagnitude(list, 5.0);
        //Printing out the quakes greater than the magnitude of 5.0
        
        for(QuakeEntry qe : qeList){
            System.out.println(qe);
        }
        
        
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> qeFilteredDist = filterByDistanceFrom(list, 1000*1000, city);
        for (QuakeEntry qe : qeFilteredDist){
            System.out.print(city.distanceTo(qe.getLocation())/1000 );
            System.out.println("  "+qe.getInfo());
        }
        System.out.println("Found "+qeFilteredDist.size()+" quakes that match that criteria");
        
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
