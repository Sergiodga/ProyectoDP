/**
 * Defines tiers/types of electric bikes for dock access rules.
 */
public enum BikeTier {
    PRIORITY, COMMON, URBAN, PREMIUM;
    
    public static int numTiers(){
        return BikeTier.values().length;
    }
    

}
