package EarthquakeFilterStarterProgram;


/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where = "";
    private String phrase = "";
    private String name = "Phrase";
    public PhraseFilter(String where, String phrase){
        this.where=where;
        this.phrase=phrase;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if ( where.equals("any") ){
            return title.contains(phrase);
        }
        else if ( where.equals("start") ){
            return title.startsWith(phrase);
        }
        else{
            return title.endsWith(phrase);
        }
      
    }
    
    public String getName(){
        return name;
        }
}
