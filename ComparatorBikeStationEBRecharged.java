import java.util.*;  

/**
 * Compares two {@link BikeStation} objects based on their number of electric bikes their docks have recharged in descending order.
 * This can be used for sorting lists of Bike stations.
 * @author DP Clasess
 * @version 2026.10.13
 */
public class ComparatorBikeStationEBRecharged implements Comparator<BikeStation>
{
    /**
     * Compares its two {@link BikeStation} arguments for order.
     * The comparison is based on the number of electric bikes their docks have recharged
     * @param st1 The first bike station to be compared.
     * @param st2 The second bike station to be compared.
     * @return A negative integer, zero, or a positive integer as the first
     * station's number of EB recharged is less than, equal to, or greater than the second.
     */
    public int compare(BikeStation st1, BikeStation st2){    
        int result=0;
        int number1=st1.getNumberOfBikesRecharged();
        int number2=st2.getNumberOfBikesRecharged();
        if(number1==number2){
            ComparatorBikeStationId  idComparator=new ComparatorBikeStationId();
            result=idComparator.compare(st1, st2);
        }
        else {if(number1 > number2){
                result=-1;
            }
            else if(number1 < number2){
                result=1;
            }
        }
        return result;
    } 
}