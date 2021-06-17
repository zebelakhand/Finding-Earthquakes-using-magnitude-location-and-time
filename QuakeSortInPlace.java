package EarthquakeSortStarterProgram;


/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int maxIndx= from;
        for (int i=from+1; i<quakes.size(); i++){
            if(quakes.get(i).getDepth() > quakes.get(maxIndx).getDepth()){
                maxIndx=i;
            }
        }
        return maxIndx;
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        int curMax;
        for (int i=0; i<50 ; i=i+1){
            curMax = getLargestDepth(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(curMax);
            in.set(i,qmax);
            in.set(curMax, qi);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        //System.out.println("Printing Quakes after pass "+ numSorted);
        for (int j=0 ; j<quakeData.size()-1 ; j+=1){
            if ( quakeData.get(j).getMagnitude()> quakeData.get(j+1).getMagnitude()){
                quakeData.set(j, quakeData.get(j+1));
                quakeData.set(j+1, quakeData.get(j));
            }
        
    }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
   
        for (int i=0; i<in.size(); i+=1){
            onePassBubbleSort(in, i);
        /*    for (QuakeEntry qe : in){
            System.out.println(qe);
        }*/
            //System.out.println(i);
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        boolean sorted=true;
        for (int i=0; i<quakes.size()-1;i=i+1){
            if( quakes.get(i).getMagnitude()>quakes.get(i+1).getMagnitude() ){
                sorted=false;
                break;
            }
        }
        return sorted;
    }
    
    public void sortByMagnitudeWithBubleSortWithCheck(ArrayList<QuakeEntry> in){
        int timesSorted=0;
        boolean isSorted = false;
        for (int i=0; i<in.size(); i=i+1){
            onePassBubbleSort(in, i);
            isSorted=checkInSortedOrder(in);
            if (isSorted == true) break;
            timesSorted++;
        }
        System.out.println("Sorted after "+timesSorted+" passes");
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int timesSorted=0;
        boolean isSorted = false;
        for (int i=0; i<in.size(); i=i+1){
            int minIndex = getSmallestMagnitude(in, i);
            QuakeEntry qe = in.get(i);
            QuakeEntry qmin = in.get(minIndex);
            in.set(minIndex, qe);
            in.set(i, qmin);
            isSorted=checkInSortedOrder(in);
            if (isSorted == true) break;
            timesSorted++;
        }
        System.out.println("Sorted after "+timesSorted+" passes");
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithCheck(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithBubleSortWithCheck(list);
        //System.out.println("Earthquakes in Sorted manner");
        /*for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } */
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
