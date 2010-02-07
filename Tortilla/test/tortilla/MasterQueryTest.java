package tortilla;

import tortilla.nexuiz.MasterQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests MasterQuery.
 * @author dmaz
 */
public class MasterQueryTest {

    MasterQuery instance;

    public MasterQueryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new MasterQuery();
    }

    @After
    public void tearDown() {
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