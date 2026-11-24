/**
 * Enumeration that defines the types of pre-configured demonstration scenarios 
 * for the simulation. Each type specifies the number of bikes, stations, 
 * and docks to be created.
 * @author DP classes
 * @version 2026.11.10
 */
public enum DemoType
{
    /** Nano scenario: 2 bikes, 2 stations, 3 docks per station. */
    NANO("NANO DEMO", 2, 2, 3),
    /** Simple scenario: 4 bikes, 5 stations, 4 docks per station. */
    SIMPLE ("SIMPLE DEMO", 4, 5, 4),
    /** Medium scenario: 7 bikes, 5 stations, 4 docks per station. */
    MEDIUM ("MEDIUM DEMO", 7, 5, 4),
    /** Advanced scenario: 10 bikes, 5 stations, 4 docks per station. */
    ADVANCED ("ADVANCED DEMO", 10, 5, 4);
    
    
    /** Descriptive name of the scenario. */
    private String name;
    /** Number of {@link ElectricBike}s to create. */
    private int numBikesToCreate;
    /** Number of {@link BikeStation}s to create. */
    private int numStationsToCreate;
    /** Number of {@link Dock}s to create per station. */
    private int numDocksToCreate;
    
    /**
     * Constructor for the demo types.
     * @param name Descriptive name.
     * @param numBikesToCreate Number of bikes.
     * @param numStationsToCreate Number of stations.
     * @param numDocksToCreate Number of docks per station.
     */
    DemoType(String name, int numBikesToCreate, int numStationsToCreate, int numDocksToCreate){
        this.name = name;
        this.numBikesToCreate = numBikesToCreate;
        this.numStationsToCreate = numStationsToCreate;
        this.numDocksToCreate = numDocksToCreate;
    }
    
    /**
     * @return The descriptive name of the demo.
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return The number of bikes that will be created for this scenario.
     */
    public int getNumBikesToCreate(){
        return numBikesToCreate;
    }
    
    /**
     * @return The number of bike stations that will be created for this scenario.
     */
    public int getNumStationsToCreate(){
        return numStationsToCreate;
    }
    
    /**
     * @return The number of docks that will be created in each station.
     */
    public int getNumDocksToCreate(){
        return numDocksToCreate;
    }
}