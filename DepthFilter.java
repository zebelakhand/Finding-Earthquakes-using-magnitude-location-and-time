package EarthquakeFilterStarterProgram;


/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private String name = "Depth";
    private double minDepth = 0.0;
    private double maxDepth = 0.0;
    
    public DepthFilter(double min, double max){
        minDepth=min;
        maxDepth=max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
    }
    
    public String getName(){
        return name;
    }
}
