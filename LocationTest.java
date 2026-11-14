import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the {@link Location} class.
 * Provides unit tests for methods like {@code distance()} and {@code nextLocation()}.
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2026.10.13 DP classes (adaptado a Java 8+)
 */
public class LocationTest
{
    /**
     * Default constructor for test class LocationTest.
     */
    public LocationTest()
    {
    }

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        // No setup required for Location tests
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        // No teardown required for Location tests
    }
    
    /**
     * Test the {@code distance} method of the {@link Location} class.
     * Checks distances from a central location to surrounding points.
     */
    @Test
    public void testDistance()
    {
        Location location1 = new Location(3, 3);
        Location location2 = new Location(3, 4);
        Location location3 = new Location(2, 3);
        assertEquals(1, location1.distance(location2));
        assertEquals(1, location1.distance(location3));
        Location location4 = new Location(1, 1);
        Location location5 = new Location(5, 1);
        assertEquals(2, location1.distance(location4));
        assertEquals(2, location1.distance(location5));
        Location location6 = new Location(1, 6);
        Location location7 = new Location(4, 6);
        assertEquals(3, location1.distance(location6));
        assertEquals(3, location1.distance(location7));
        Location location8 = new Location(3, 3);
        assertEquals(0, location1.distance(location8));
    }
    
    /**
     * Test the {@code nextLocation} method when the destination is adjacent 
     * (one step away in any direction).
     */
    @Test
    public void testAdjacentLocations()
    {
        //TODO: Complete this code
    }
    
    /**
     * Test the {@code nextLocation} method when the destination is not adjacent 
     * (more than one step away).
     */
    @Test
    public void testNonAdjacentLocations()
    {
        //TODO: Complete this code
    }

    
}
