package EarthquakeFilterStarterProgram;


/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;
    private String name  = "Distance";
    public DistanceFilter(Location loc, double max){
        location=loc;
        maxDistance=max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        Location loc = qe.getLocation();
        return loc.distanceTo(location) <= maxDistance;
    }
    
    public String getName(){
        return name;
    }
}
