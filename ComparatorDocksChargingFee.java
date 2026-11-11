import java.util.*; 

/**
 * Compares two {@link Dock} objects based on their **ChargingFee** float in **ascending** order.
 * This is typically used as a final tie-breaker in multi-criteria sorting.
 * @author DP Clasess
 * @version 2026.10.13
 */
public class ComparatorDocksChargingFee implements Comparator<Dock>
{
    /**
     * Compares its two {@link Dock} arguments for order.
     * The comparison is based on their charging fee value.
     * @param c1 The first dock's charging fee to be compared.
     * @param c2 The second dock's charging fee to be compared.
     * @return A negative integer, zero, or a positive integer as the first 
     * dock's charging fee is lexicographically less than, equal to, or greater than the second.
     */
    public int compare(Dock c1, Dock c2){  
        return Float.compare(c1.getChargingFee(), c2.getChargingFee());
    } 
}