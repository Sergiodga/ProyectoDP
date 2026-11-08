    import java.util.List;
import java.util.ArrayList;

/**
 * Model the common elements of an Electric bike (EB) that operates 
 * within the simulation, moving towards a target and potentially recharging.
 * * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class ElectricBike 
{
    //Prueba actualizar repositorio a ver si cambia colega
    //dadadada
    //Taqui tak
    private String idBike;
    private EBCompany company;
    private Location location;
    private Location targetLocation;
    private int idleCount;
    private int batteryCapacity;
    private int batteryLevel;
    private int wsCharged;
    private int chargesCount;
    private float chargesCost;
    private List<Dock> docks;
    private Location rechargingLocation;

    /**
     * Constructor of class ElectricBike.
     * @param company The EB's operating company. Must not be null.
     * @param location The EB's starting {@link Location}. Must not be null.
     * @param targetLocation The EB's final destination {@link Location}. Must not be null.
     * @param id The identificator of the bike.
     * @param batteryCapacity The maximum capacity of the battery.
     * @throws NullPointerException If company, location, or targetLocation is null.
     */
    public ElectricBike(EBCompany company, Location location, Location targetLocation, String id, int batteryCapacity)
    {
        this.company=company;
        this.location=location;
        this.targetLocation=targetLocation;
        this.idBike=id;
        this.batteryCapacity=batteryCapacity;
        idleCount=0;
        batteryLevel= batteryCapacity;
        wsCharged=0;
        chargesCount=0;
        chargesCost=0;
        docks =new ArrayList<Dock>();
        rechargingLocation=null;

    }

    /**
     * @return The id of the electric bike.
     */
    public String getIdBike()
    {
        return idBike;    
    }

    /*
     * @return The capacity of the electric bike in wh
     */
    public int getBatteryCapacity(){
        return batteryCapacity;
    }

    /*
     * @return The battery level of the electric bike in wh
     */
    public int getBatteryLevel(){
        return batteryLevel;
    }

    /*
     * @return The number of time the electric bike has been charged
     */
    public int getChargesCount(){
        return chargesCount;
    }

    /*
     * @return The total cost of the charges that the bike has received
     */
    public float getChargesCost(){
        return chargesCost;
    }

    /*
     * @return The time that the bike is not moving
     */
    public int getIdleCount(){
        return idleCount;
    }

    /**
     * Get the current location.
     * @return Where this bike is.
     */
    public Location getLocation()
    {
        return location;    
    }
    
    /**
     * Set the id of the bike
     * @param idBike The new ID string for the electric bike.
     */
    public void setIdBike(String idBike)
    {
        this.idBike=idBike;  
    }
    
    /**
     * Set the company the bike belongs to
     * @param company The EBCompany that manages this bike.
     */
    public void setCompany(EBCompany company)
    {
        this.company=company;  
    }
    
    /**
     * Sets the number of turns the bike has remained idle at its destination.
     * @param idleCount The new idle turn count.
     */
    public void setIdleCount(int idleCount)
    {
        this.idleCount=idleCount;
    }
    
    /**
     * Set the bike's battery capacity
     * @param batteryCapacity The maximum capacity in wh.
     */
    public void setBatteryCapacity(int batteryCapacity)
    {
        this.batteryCapacity=batteryCapacity;
    }
    
    /**
     * Set the bike's battery level
     * @param batteryLevel The current level in wh.
     */
    public void setBatteryLevel(int batteryLevel)
    {
        this.batteryLevel=batteryLevel;
    }
    
    /**
     * Set the Ws charged
     * @param wsCharged The total wh charged.
     */
    public void setWsCharged(int wsCharged)
    {
        this.wsCharged=wsCharged;
    }
    
    /**
     * Sets the number of times the electric bike has been charged.
     * @param chargesCount The number of charges.
     */
    public void setChargesCount(int chargesCount) {
        this.chargesCount = chargesCount;
    }

    /**
     * Sets the total cost of the charges that the bike has received.
     * @param chargesCost The total cost in euros.
     */
    public void setChargesCost(float chargesCost) {
        this.chargesCost = chargesCost;
    }

    /**
     * Sets the recharging location for the bike.
     * @param rechargingLocation The Location of the BikeStation to visit.
     */
    public void setRechargingLocation(Location rechargingLocation) {
        this.rechargingLocation = rechargingLocation;
    }
    
    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        if (location==null){
            throw new NullPointerException("La location no puede ser null");
        }
        this.location=location;   
    }

    /**
     * Get the final target location.
     * @return Where this bike is ultimately headed.
     */
    public Location getTargetLocation()
    {
        return targetLocation;    
    }

    /**
     * Get the temporary recharging location.
     * @return The {@link Location} of the next {@link BikeStation} to visit, or null if no recharge is planned.
     */
    public Location getRechargingLocation()
    {
        return rechargingLocation;
    }

    /**
     * Set the required final target location.
     * @param location Where to go. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setTargetLocation(Location location)
    {
        if (location==null){
            throw new NullPointerException("La location no puede ser null");
        }
        this.targetLocation=location;
        calculateRoute();
    }

    /**
     * Calculates the optimal route for the bike. 
     * If there isn't enough battery to reach the target, it attempts to find an intermediate 
     * {@link BikeStation} and sets it as the {@code rechargingLocation}.
     */
    public void calculateRoute()
    {
        if(enoughBattery()){
            rechargingLocation=null;
        }else{
            calculateRechargingPosition();
        }
    }

    /**
     * Gets a string representation of the planned route, including the recharging stop if one exists.
     * @return A string showing the route: {@code currentLocation -> [rechargingLocation ->] targetLocation}.
     */
    public String getStringRoute()
    {
        calculateRoute(); //PUEDE SER QUE SE PONGA EN CONSTRUCTOR Y ASI NADA MAS EMPEZAR SABEMOS TODO YA Y ESTO HABRIA QUE QUITARLO
        String finalRoute;
        if(rechargingLocation!=null){
            finalRoute= location.toString() + ", " + rechargingLocation.toString() + ", " + targetLocation.toString();
        }
        else{
            finalRoute= location.toString() + ", " + targetLocation.toString();
        }
        return finalRoute;
    }

    /**
     * Checks if the current battery level is sufficient to cover a given distance.
     * //TODO complete the JavaDoc comments
     */
    public boolean enoughBattery(   )
    {
        boolean enough=false;
        if(batteryLevel >= distanceToTheTargetLocation()*5){
            enough=true;
        }
        return enough;
    }

    /**
     * Determines the optimal intermediate {@link BikeStation} to visit for recharging
     * if the bike cannot reach the final target directly.
     * Sets {@code rechargingLocation} to the chosen station's location.
     */
    public void calculateRechargingPosition(){
        //FALTA TODO
    }

    /**
     * Get the Manhattan-like distance to the final target location from the current location.
     * @return The distance to the target location.
     */
    public int distanceToTheTargetLocation()
    { 
        return location.distance(targetLocation);
    }

        /**
     * Simulates the recharging process when the bike arrives at a {@code rechargingLocation}.
     * The battery is charged to full capacity, the cost is calculated, and the route is recalculated.
     * //TODO complete the JavaDoc comments
     */
    public void recharge(   )
    {
        BikeStation rechargingStation = company.getBikeStation(rechargingLocation);
        Dock chargingDock = rechargingStation.getFreeDock();
        docks.add(chargingDock);
        wsCharged=(batteryCapacity-batteryLevel);
        incrementChargesCost(chargingDock.recharge(this));
        incrementCharges();
        batteryLevel=batteryCapacity;
        calculateRoute();
    } 

            /**
     * Increments the count of recharges performed by this bike.
     * //TODO complete the JavaDoc comments
     */
    public void incrementCharges()
    {
        chargesCount++;
    }

    /**
     * Adds a cost amount to the total charges cost.
     * //TODO complete the JavaDoc comments
     */
    public void incrementChargesCost(float cost)
    {
        chargesCost+= cost;
    }   

    /**
     * Carries out a single step of the bike's actions.
     * Moves one step towards the target (recharging or final) or stays idle.
     * //TODO complete the JavaDoc comments
     */
    public void act(   )
    {
        if(location.equals(targetLocation)){
            idleCount+=1;
        }
        else {
            Location next=null;
            if(rechargingLocation!=null){
                next= location.nextLocation(rechargingLocation);
                location=next;
                reduceBatteryLevel();
                if(location.equals(rechargingLocation)){
                    // EN EL CASO EN EL QUE NO HAYA NINGUN DOCK LIBRE DEBE QUEDARSE AHI SIN MOVERSE HASTA EL FINAL DE LA SIMULACION
                    recharge();
                }
            }
            else{
                next=location.nextLocation(targetLocation);
                location=next;
                reduceBatteryLevel();
                if(location.equals(targetLocation)){
                    //REGISTRA QUE HA LLEGADO A SU DESTINO
                }
            }
        }
        
    }

    /**
     * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
     * Ensures the battery level does not go below zero.
     */
    public void reduceBatteryLevel(){
        if(batteryLevel>=5){
            batteryLevel-=5;
        }
    }

    /**
     * Returns a detailed string representation of the electric bike.
     * @return A string containing the bike's name, plate, battery info, charge counts, costs, idle count, and route.
     */
    @Override
    public String toString(){
        String cad= "(ElectricBike: " + getIdBike() + ", "+
            getBatteryCapacity() + "wh, "+ getBatteryLevel() +
            "wh, "+ getChargesCount() +", " + 
            getChargesCost()+ "€, " + getIdleCount() + 
            ", " +  getStringRoute() + ")";
        return cad;
    }

}
