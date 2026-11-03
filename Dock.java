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
     * //TODO complete the JavaDoc comments
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

    public String getIdDock(){
        return idDock;
    }
    
    public int getChargingSpeed(){
        return chargingSpeed;
    }
    
    public float getChargingFee(){
        return chargingFee;
    }
    
    public float getAmountCollected(){
        return amountCollected;
    }
    
    public void insertBike(ElectricBike eb){
        bikesRecharged.add(eb);
    }
    
    public void reloadAmountCollected(){
        amountCollected=getNumerEVRecharged()*chargingFee;
    }
    
    public boolean getIfFree(){
        return free;
    }
    
    /**
     * Returns a string representation of the dock, including its ID, speed, fee, and the number of EVs recharged.
     * @return A string representation of the dock.
     */
    public String toString()
    {
        String cad= "(Dock: "+ getIdDock() +
        ", " + getChargingSpeed() + "wh, " + getChargingFee() + "€, "
        + getNumerEVRecharged()+ ", " + getAmountCollected()+ ")";
        return cad;
    }    

    
    /**
     * @return The total number of {@link ElectricBike}s that have been recharged by this dock.
     */
    public int getNumerEVRecharged(){
       return bikesRecharged.size();
    }
    
    /**
     * Simulates the charging process for an {@link ElectricBike}.
     * Increases the amount collected and registers the bike as recharged.
     * //TODO complete the JavaDoc comments
     */
    public float recharge(ElectricBike EB){
        EB.recharge();
        insertBike(EB);
        reloadAmountCollected();
        return 0;
    }

    //TODO: Complete this code

}
