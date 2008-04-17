package tortilla;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests TortillaQueryMaster and TortillaQueryServer.
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
                result.contains("excalibur.nvg.ntnu.no"));
    }

    /**
     * Test if servercache is created.
     */
    @Test
    public void testQueryMaster()
    {
        System.out.println("testQueryMaster");
        long startTime = System.currentTimeMillis();
        instance.qstat();
        long endTime = System.currentTimeMillis();
        System.out.println("qstat() executed in " + (endTime - startTime)
                + "ms");
        instance.parseInputStream();
        System.out.println("parseInputStream() complete");
        System.out.println(instance.getServerList().keySet());
        System.out.println(instance.getServerList().size() + " servers");
        assertFalse(instance.getServerList().isEmpty());
    }

}