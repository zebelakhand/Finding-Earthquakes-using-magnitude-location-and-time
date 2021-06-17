package SearchingEarthquakeDataStarterProgram;


/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIndex=0;
        double maxMag=data.get(maxIndex).getMagnitude();
        for (int j =1; j<data.size() ; j=j+1){
            double curMag = data.get(j).getMagnitude();
            if (curMag > maxMag){
                maxIndex=j;
                maxMag=curMag;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int curMaxIndex=0;
        for (int i =0; i<howMany; i=i+1){
            curMaxIndex = indexOfLargest(copy);
            answer.add(copy.get(curMaxIndex));
            copy.remove(curMaxIndex);
        }
        return answer;
    }
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source="data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        //printing all the earthquakes
        /*for (QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        System.out.println("Read data for "+list.size()+" earthquakes");
        
        //int maxMagIndex = indexOfLargest(list);
        //System.out.println(maxMagIndex);
        
        //Testing getLargest method
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 50);
        for (QuakeEntry qe : largestQuakes){
            System.out.println(qe);
        }
    }
}
