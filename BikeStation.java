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
     * @param id the unique identifier of the station.
     * @param location the{@link Location} of the station in the city.
     */
    public BikeStation(String id, Location location)
    {
        idStation=id;
        this.location=location;
        docks=new ArrayList<Dock>();
    }

    /**
     * Gets the docks of the station.
     * @return a view of the docks of the station, sorted by charging speed(descending), charging fee(ascending) and identifier (ascending).
     */
    public List<Dock> getDocks(){
        return docks;
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
     * @param idDock the identificador of the docks to look for
     * @return {@link Dock} with that identificator, or {@code null} if the station has no such dock. {
     */
    public Dock getDock(String idDock){
        Iterator<Dock> it = docks.iterator();
        Dock found = null;
        while(found==null && it.hasNext()){
            Dock dock = it.next();
            if(dock.getIdDock().equals(idDock)){
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

    /**
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
        if(dock != null){
            docks.add(dock);
            Collections.sort(docks, new ComparatorDocksChargingSpeed()); //INTERNAMENTE YA ORDENA EN CASCADA POR EL RESTO DE CONDICIONES, NO HACEN FALTA MAS LLAMADAS

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
            throw new NullPointerException("Location cannot be null");
        }
        this.location= location;
    }

    /**
     * Sets the unique identifier of the charging station.
     * @param idStation The new ID string for the station.
     */
    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    /**
     * Sets the list of {@link Dock} units available at the station.
     * @param docks The new list of docks.
     */
    public void setDocks(List<Dock> docks) {
        this.docks = docks;
    }

    /**
     * @return the number recharged performed in the docks of the station
     */public int getNumberOfBikesRecharged(){
        int total =0;
        for(Dock dock : docks) {
            total+= dock.getNumerEVRecharged();
        }
        return total;
    }

    /**
     * @return a string representation of bikeStation
     */
    @Override
    public String toString(){
        String cad= "(BikeStation: " + idStation + ", " + getNumberOfBikesRecharged() + ", " + location + ")";
        for(Dock dock: this.getDocks()){
            cad+= "\n" + dock;
        }
        return cad;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(!(obj instanceof BikeStation)){
            return false;
        }
        BikeStation other= (BikeStation) obj;
        return this.idStation == other.idStation
        && this.location == other.location;
    }

    /**
     * Calcula y devuelve el código hash de esta estación de bicicletas a partir de sus
     * campos significativos (idStation y location) para garantizar la coherencia con
     * el método equals.
     *
     * @return El valor entero del código hash generado para esta estación de bicicletas.
     */
    @Override
    public int hashCode() {
        // Genera el código hash combinando exactamente los mismos campos utilizados en equals
        return Objects.hash(idStation, location);
    }
}
