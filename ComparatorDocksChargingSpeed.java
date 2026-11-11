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
     * @param d1 The first dock to be compared.
     * @param d2 The second dock to be compared.
     * @return A negative integer, zero, or a positive integer as the first dock goes
     * before, is equivalent to, or goes after the second one.
     */
    public int compare(Dock d1, Dock d2){
        int result = Integer.compare(d2.getChargingSpeed(), d1.getChargingSpeed());
        if(result == 0) {
            result = Float.compare(d1.getChargingFee(), d2.getChargingFee());
        }
        if(result == 0) {
            result = new ComparatorDocksId().compare(d1, d2);
        }
        return result;
    }
}
