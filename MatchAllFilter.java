package EarthquakeFilterStarterProgram;


/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for (Filter f : filters){
            if (! f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String filterNames = "Filters used are:";
        for (Filter f : filters){
            filterNames=filterNames+" "+f.getName();
        }
        return filterNames;
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        EarthQuakeClient2 client = new EarthQuakeClient2();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read a total data of "+list.size()+" earthquakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0);
        Filter f2 = new DepthFilter(-180000, -30000);
        Filter f3 = new PhraseFilter("any", "o");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> qeList=client.filter(list, maf);
        
        for (QuakeEntry qe : qeList){
            System.out.println(qe);
        }
        System.out.println(maf.getName());
        System.out.print("Found "+qeList.size()+" that matches the criteria");
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        EarthQuakeClient2 client = new EarthQuakeClient2();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("Read a total data of "+list.size()+" earthquakes");
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0);
        Location billund = new Location(55.7308, 9.1153);//55.7308, 9.1153
        Filter f2 = new DistanceFilter(billund,3000000 );
        Filter f3 = new PhraseFilter("any", "e");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> qeList=client.filter(list, maf);
        
        for (QuakeEntry qe : qeList){
            System.out.println(qe);
        }
        System.out.println("Found "+ qeList.size()+ " that matches the criteria");
    }
    
}
