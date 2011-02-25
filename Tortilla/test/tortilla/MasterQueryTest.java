package tortilla;

import tortilla.xonotic.query.MasterQuery;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests MasterQuery.
 * @author dmaz
 */
public class MasterQueryTest {

    protected transient MasterQuery instance;

    @Before
    public void setUp() {
        instance = MasterQuery.getInstance();
    }
    
    /**
     * Test of getAddressFromBytes method.
     */
    @Test
    public void testGetAddressFromBytes() {
        System.out.println("testGetValue");
        String expResult = "47.47.47.47:12079";
        String result = instance.getAddressFromBytes("//////");
        assertEquals(result, expResult);
    }
}