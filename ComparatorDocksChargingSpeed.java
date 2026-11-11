import java.util.*;

/**
 * Compares two {@link Dock} objects by charging speed in descending order. Ties are broken
 * by charging fee in ascending order and, if they still tie, by id in ascending order
 * (see {@link ComparatorDocksId}).
 * This is the criterion used to keep the docks sorted inside a {@link BikeStation}.
 * @author DP Clasess
 * @version 2026.10.13
 */
public class ComparatorDocksChargingSpeed implements Comparator<Dock>
{
    /**
     * Compares its two {@link Dock} arguments for order.
     * @param d1 The first dock's charging speed to be compared.
     * @param d2 The second dock's charging speed to be compared.
     * @return A negative integer, zero, or a positive integer as the first dock's charging speed goes
     * before, is equivalent to, or goes after the second one.
     */
    public int compare(Dock d1, Dock d2){
        int result=0;
        if(d1.getChargingSpeed()==d2.getChargingSpeed()){
            ComparatorDocksChargingFee chargingFeeComparator= new ComparatorDocksChargingFee();
            result= chargingFeeComparator.compare(d1, d2);
        }
        else if (d1.getChargingSpeed() > d2.getChargingSpeed()){
            result=-1;
        }
        else{
            result=1;
        }
        return result;
    }
}
