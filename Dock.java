import java.util.ArrayList;
import java.util.List;
/**
 * Model a dock unit within a {@link BikeStation}.
 * It tracks its charging capabilities, fee, and the electric bikes it has recharged.
 * @author David J. Barnes and Michael Kölling
 * @author DP classes 
 * @version 2026.10.13
 */
public class Dock
{
    private String idDock;
    private int chargingSpeed;
    private float chargingFee;
    private List<ElectricBike> bikesRecharged;
    private float amountCollected;
    private boolean free;
    /**
     * Constructor for objects of class Dock.
     * @param id the unique identifier of the dock.
     * @param speed the charging speed of the dock.
     * @param fee the price of the charge.
     */
    public Dock(String id, int speed, float fee)
    {
        idDock=id;
        chargingSpeed=speed;
        chargingFee=fee;
        bikesRecharged= new ArrayList<ElectricBike>();
        amountCollected=0.0f;
        free=true;
    }

    /**
     * @return The id of the dock.
     */
    public String getIdDock(){
        return idDock;
    }

    /**
     * @return The id of the charging speed dock.
     */
    public int getChargingSpeed(){
        return chargingSpeed;
    }

    /**
     * @return The charging fee of the dock.
     */
    public float getChargingFee(){
        return chargingFee;
    }

    /**
     * @return The total amount collected by the dock.
     */
    public float getAmountCollected(){
        return amountCollected;
    }

    /**
     * Inserts a new electric bike to the list of bikes recharged by the dock.
     * @param eb the electrike bike that has just been recharged.
     */
    public void insertBike(ElectricBike eb){
        bikesRecharged.add(eb);
    }

    /**
     * Updates the amount collected by the dock.
     * @param whReloadCost the cost of recharge(in euros) to add to the amount collected.
     */
    public void reloadAmountCollected(float whReloadCost){
        amountCollected+=whReloadCost;
    }

    /**
     * @return If the dock is free (true) or not (false)
     */
    public boolean getIfFree(){
        return free;
    }

    /**
     * Sets the id of the dock
     * @param idDock the new id of the dock.
     */
    public void setIdDock(String idDock){
        this.idDock=idDock;
    }

    /**
     * Sets the charging speed of the dock
     * @param chargingSpeed the new charngingSpeed.
     */
    public void setChargingSpeed(int chargingSpeed){
        this.chargingSpeed=chargingSpeed;
    }

    /**
     * Sets the charging fee of the dock
     * @param chargingFee the new chargingfee.
     */
    public void setChargingFee(float chargingFee){
        this.chargingFee=chargingFee;
    }

    /**
     * Sets if the dock is free or not
     * @param {@code true} if dock is free, {@code false} if dock it not free.
     */
    public void setFree(boolean free){
        this.free=free;
    }

    /**
     * Sets the amount collected by the dock in case we want to use it
     * @param amountCollected The amount collected to set
     */
    public void setAmountCollected(float amountCollected){
        this.amountCollected=amountCollected;
    }

    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EVs recharged.
     * @return A string representation of the dock.
     */
    public String toString()
    {
        String cad= "(Dock: "+ getIdDock() +
            ", " + getChargingSpeed() + "wh, " + getChargingFee() + "€, "
            + getNumerEVRecharged()+ ", " + getAmountCollected()+ "€)";
        return cad;
    }    

    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumerEVRecharged(){
        return bikesRecharged.size();
    }

    /**
     * @return The list of electric bikes recharged by this dock.
     */
    public List<ElectricBike> getBikesRecharged() {
        return bikesRecharged;
    }

    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     * @param eb the {@link ElectricBike} taht is been recharged.
     * @return the cost of the recharged
     */
    public float recharge(ElectricBike eb){
        int whReload= eb.getBatteryCapacity() - eb.getBatteryLevel();
        float whReloadCost= whReload * chargingFee;
        reloadAmountCollected(whReloadCost);
        insertBike(eb);
        return whReloadCost;
    }

}
