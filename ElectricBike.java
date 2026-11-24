import java.util.*;
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

    private boolean blocked; //Añadido para bloquear bicicleta si es necesario
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
        if (location==null || company==null || targetLocation==null){
            throw new NullPointerException("Location, company, targetLocation cannot be null");
        }
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
        blocked=false;
    }

    public List<Dock> getDocks() {
        return docks;
    }

    /**
     * @return The id of the electric bike.
     */
    public String getIdBike()
    {
        return idBike;    
    }

    /**
     * @return The capacity of the electric bike in wh
     */
    public int getBatteryCapacity(){
        return batteryCapacity;
    }

    /**
     * @return The battery level of the electric bike in wh
     */
    public int getBatteryLevel(){
        return batteryLevel;
    }

    /**
     * @return The number of time the electric bike has been charged
     */
    public int getChargesCount(){
        return chargesCount;
    }

    /**
     * @return The total cost of the charges that the bike has received
     */
    public float getChargesCost(){
        return chargesCost;
    }

    /**
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
     * @return {@code true} if the bike is blocked, {@code false} otherwise.
     */
    public boolean isBlocked() {
        return blocked;
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
            throw new NullPointerException("Location cannot be null");
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
            throw new NullPointerException("TargetLocation cannot be null");
        }
        this.targetLocation=location;
        calculateRoute();
    }

    /**
     * Sets the list of docks where the electric bike has recharged.
     * @param docks The new list of {@link Dock} units.
     */
    public void setDocks(List<Dock> docks) {
        this.docks = docks;
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
     * @return if the battery level is enough to reach the target, {@code false} otherwise.
     */
    public boolean enoughBattery(   )
    {
        boolean enough=false;
        if(batteryLevel >= distanceToTheTargetLocation()*EBCompany.MOVINGCOST){
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
        List<BikeStation> allStations = company.getStations();
        BikeStation bestStation= null;
        int minTotalDistance = 0;
        int minDistanceToTarget= 0;
        int DistToTarget = location.distance(targetLocation);

        for(int i=0;i< allStations.size();i++){
            BikeStation station = allStations.get(i);
            Location stationLoc = station.getLocation();
            int distanceToStation = location.distance(stationLoc); //Distancia de bici a estación
            int distanceStationToTarget = stationLoc.distance(targetLocation);
            if (distanceToStation > 0 && batteryLevel >= distanceToStation * EBCompany.MOVINGCOST && distanceStationToTarget < DistToTarget) {
                int totalDistance = distanceToStation + distanceStationToTarget;

                if (bestStation == null || distanceToStation < minTotalDistance) {
                    minTotalDistance = totalDistance;
                    minDistanceToTarget=distanceStationToTarget;
                    bestStation = station;
                }
                else if (totalDistance == minTotalDistance) {
                    if (distanceStationToTarget < minDistanceToTarget) {
                        minDistanceToTarget = distanceStationToTarget;
                        bestStation = station;
                    }
                }
            }
        }

        if (bestStation != null) {
            rechargingLocation = bestStation.getLocation();
        } else {
            // Si no hay ninguna estación a la que pueda llegar, la bici se quedará parada 
            blocked=true;
        }
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
     * @param step The step number the bike is on.
     */
    public void recharge(int step)
    {
        BikeStation rechargingStation = company.getBikeStation(rechargingLocation);
        Dock chargingDock = rechargingStation.getFreeDock();
        if (chargingDock != null) {
            int whReloaded = batteryCapacity - batteryLevel;
            float cost = chargingDock.recharge(this);
            chargingDock.setFree(false);
            docks.add(chargingDock);
            wsCharged += (whReloaded);
            incrementChargesCost(cost);
            incrementCharges();
            batteryLevel = batteryCapacity;
            rechargingLocation = null;
            calculateRoute();
            chargingDock.setFree(true);

            System.out.println("(step: " + step + " - ElectricBike: " + idBike + 
                " recharges: " + whReloaded + "wh at dock: " + 
                chargingDock.getIdDock() + " with cost: " + cost + "€ ********)");
        } else {
            // Si no hay dock, se queda bloqueada para siempre quitando el destino y la estación.
            blocked=true;
        }
    } 

    /**
     * Increments the count of recharges performed by this bike.
     */
    public void incrementCharges()
    {
        chargesCount++;
    }

    /**
     * Adds a cost amount to the total charges cost.
     * @param cost The cost of the current recharge to be added, in euros.
     */
    public void incrementChargesCost(float cost)
    {
        chargesCost+= cost;
    }   

    /**
     * Carries out a single step of the bike's actions.
     * Moves one step towards the target (recharging or final) or stays idle.
     * @param step The step number the bike is on.
     */
    public void act(int step)
    {
        if(!blocked){
            if(location.equals(targetLocation)){
                idleCount += 1;
            }
            else {
                Location next = null;
                if(rechargingLocation != null){
                    next = location.nextLocation(rechargingLocation);
                    if(next.equals(rechargingLocation)){
                        location = next;
                        reduceBatteryLevel();
                        recharge(step);
                        return;
                    }
                }
                else{
                    next = location.nextLocation(targetLocation);
                    if(next.equals(targetLocation)){
                        System.out.println("(step: " + step + " - ElectricBike: " + idBike + " at target destination" + " ********)");
                    }
                }
                location = next;
                reduceBatteryLevel();
            }
        } else {
            idleCount += 1;
        }
    }

    /**
     * Reduces the battery level by the cost of one movement step (defined in {@link EBCompany#MOVINGCOST}).
     * Ensures the battery level does not go below zero.
     */
    public void reduceBatteryLevel(){
        if(batteryLevel>=EBCompany.MOVINGCOST){
            batteryLevel-=EBCompany.MOVINGCOST;
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

    /**
     * Returns a detailed string representation of the electric bike with the list of docks it has recharged on.
     * @return A string containing the bike's name, plate, battery info, charge counts, costs, idle count, and route
     * and the id of the docks.
     */
    public String toStringWithDocks(){
        String cad= this.toString();
        for(Dock dock : this.getDocks()){
            cad+="\n" +"(Dock: " + dock.getIdDock() + ")";
        }
        return cad;
    }

    /**
     * Compara esta bicicleta eléctrica con otro objeto para determinar si representan
     * la misma bicicleta. Dos objetos de tipo ElectricBike se consideran iguales si
     * coinciden en su identificador y su capacidad de batería.
     *
     * @param obj El objeto con el que se desea comparar esta bicicleta eléctrica.
     * @return true si ambos objetos son instancias de ElectricBike y tienen el mismo
     *         idBike y batteryCapacity; false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElectricBike)) {
            return false;
        }
        ElectricBike other = (ElectricBike) obj;

        return this.batteryCapacity == other.batteryCapacity
        && this.idBike == other.idBike;
    }

    /**
     * Calcula y devuelve el código hash de esta bicicleta eléctrica a partir de sus campos
     * significativos (idBike y batteryCapacity) para garantizar la coherencia
     * con el método equals.
     *
     * @return El valor entero del código hash generado para esta bicicleta eléctrica.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idBike, batteryCapacity);
    }

}
