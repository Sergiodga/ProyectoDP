import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.*;

/**
 * Models an Electric Vehicle Charging Station.
 * A charging station contains multiple {@link Dock} units.
 * @author DP classes 
 * @version 2026.10.13
 */
public class BikeStation
{
    private String idStation;
    private Location location;
    private List<Dock> docks;

    /**
     * Constructor for objects of class BikeStation.
     * //TODO complete the JavaDoc comments
     */
    public BikeStation(  )
    {
        docks=new ArrayList<Dock>();
    }

    /**
     * Constructor for objects of class BikeStation.
     * //TODO complete the JavaDoc comments
     */
    public BikeStation(String id, Location location)
    {
        idStation=id;
        this.location=location;
        docks=new ArrayList<Dock>();
    }
    
    /**
     * Retrieves the first free {@link Dock} found at the station.
     * @return A free {@link Dock}, or possibly throws an exception if none are found (depending on stream implementation details).
     * **Note: The implementation assumes at least one free dock exists if called.**
     */
    public Dock getFreeDock()
    {
       Iterator<Dock> it = docks.iterator();
       Dock found = null;
       while( found == null && it.hasNext()){
           Dock dock = it.next();
           if(dock.getIfFree()){
               found = dock;
           }
       }
       return found;
    }

    /**
     * @return The unique identifier of the charging station.
     */
    public String getId()
    {  
        return idStation;
    }
    
    /*
     * @return The location of the bike station
     */
    public Location getLocation(){
        return location;
    }
    /**
     * Adds a new {@link Dock} to the station.
     * @param dock The new Dock unit.
     */
    public void addDock(Dock dock)
    {
       if( dock != null){
           docks.add(dock);
           //Collections.sort(docks, new ComparatorDockChargingSpeed());
           
        }
    }
    
    /**
     * Set the current location of the charging station.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
       
        if(location == null){
            throw new NullPointerException(" Location no puede ser null");
        }
        this.location= location;
       
    }
    
    public int getNumberOfBikesRecharged(){
        int total =0;
        for(Dock dock : docks) {
            total+= dock.getNumerEVRecharged();
        }
        return total;
        
    }
    
    /**
     * @return 
     */
    public String toString(){
        return "(BikeStation: " + idStation + ", " + getNumberOfBikesRecharged() + "," + location + ")";
    }
    //TODO complete the rest of the code

}
