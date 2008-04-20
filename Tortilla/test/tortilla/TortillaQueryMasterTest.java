package tortilla;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests TortillaQueryMaster.
 * @author David
 */
public class TortillaQueryMasterTest {

    TortillaQueryMaster instance;

    public TortillaQueryMasterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new TortillaQueryMaster();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMaster method, of class TortillaQueryMaster.
     */
    @Test
    public void testGetMaster() {
        System.out.println("testGetMaster");
        String result = instance.getMaster();
        System.out.println("Queried " + result);
        assertTrue(result.contains("ghdigital.com") ||
                result.contains("dpmaster.deathmask.net") ||
                result.contains("dpmaster.tchr.no"));
    }
    
    /**
     * Test of getServers method.
     */
    @Test
    public void testGetServers() {
        System.out.println("testGetServers");
        ArrayList < String > result = 
                instance.getServers();
        for (String ip : result) {
            System.out.println(ip);
        }
    }
    
//    /**
//     * Test of getServers method.
//     * This is an unreliable and useless test atm.
//     */
//    @Test
//    public void testGetValue() {
//        System.out.println("testGetValue");
//        String expResult = "75.126.234.42:26002";
//        String result = instance.getValue("K~ê*e?");
//        assertEquals(result, expResult);
//    }
}