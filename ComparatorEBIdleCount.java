import java.util.*; 

/**
 * Compares two {@link ElectricBike} objects based on idleCount in descending order. Ties are
 * broken by BikeId in ascending order (see {@link ComparatorEBIdBike}).
 * This can be used for sorting lists of electric bikes.
 * @author DP Clasess
 * @version 2026.10.13
 */
public class ComparatorEBIdleCount implements Comparator<ElectricBike>
{
    /**
     * Compares its two {@link ElectricBike} arguments for order.
     * The comparison is based on their idleCount value (turns they have been on their destination).
     * @param eb1 The first electric bike to be compared.
     * @param eb2 The second electric bike to be compared.
     * @return A negative integer, zero, or a positive integer as the
     * first bike reached its destination before, at the same time, or after the second.
     */
    public int compare(ElectricBike eb1, ElectricBike eb2){    
        int result=0;
        int idle1=eb1.getIdleCount();
        int idle2=eb2.getIdleCount();
        if(idle1==idle2){
            ComparatorEBIdBike idComparator=new ComparatorEBIdBike();
            result=idComparator.compare(eb1, eb2);
        }
        else{if(idle1 > idle2){
                result=-1;
            }
            else if(idle1 < idle2){
                result=1;
            }
        }
        return result;
    } 
}