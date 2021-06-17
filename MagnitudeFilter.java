package EarthquakeFilterStarterProgram;


/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double maxMagnitude=0.0;
    private double minMagnitude=0.0;
    private String name = "Magnitude";
    
    public MagnitudeFilter(double min, double max){
        minMagnitude=min;
        maxMagnitude=max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return minMagnitude <= qe.getMagnitude() 
        && maxMagnitude >= qe.getMagnitude(); 
    }
    
    public String getName(){
        return name;
    }
}
