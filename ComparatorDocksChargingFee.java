import java.util.*; 

/**
 * Compares two {@link Dock} objects based on their ChargingFee in ascending order.
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
     * dock's charging fee is less than, equal to, or greater than the second.
     */
    public int compare(Dock c1, Dock c2){  
        int result=0;
        float fee1=c1.getChargingFee();
        float fee2=c2.getChargingFee();
        if(fee1==fee2){
            ComparatorDocksId IdComparator= new ComparatorDocksId();
            result= IdComparator.compare(c1, c2);
        }
        else if (fee1 < fee2){
            result=-1;
        }
        else{
            result=1;
        }
        return result;
    } 
}