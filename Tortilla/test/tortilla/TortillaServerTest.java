package tortilla;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests TortillaServer.
 * @author dmaz
 */
public class TortillaServerTest {
    
    TortillaServer instance;

    public TortillaServerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() {
        instance = new TortillaServer();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isFull method, of class TortillaServer.
     */
    @Test
    public void isFull()
    {
        System.out.println("isFull");
        instance.setPlayerCount(2);
        instance.setMaxPlayers(2);
        boolean expResult = true;
        boolean result = instance.isFull();
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class TortillaServer.
     */
    @Test
    public void isEmpty()
    {
        System.out.println("isEmpty");
        instance.setPlayerCount(0);
        instance.setMaxPlayers(2);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of isFav method, of class TortillaServer.
//     */
//    @Test
//    public void isFav()
//    {
//        System.out.println("isFav");
//        TortillaServer instance = null;
//        boolean expResult = false;
//        boolean result = instance.isFav();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getIP method, of class TortillaServer.
//     */
//    @Test
//    public void getIP()
//    {
//        System.out.println("getIP");
//        TortillaServer instance = null;
//        String expResult = "";
//        String result = instance.getIP();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFav method, of class TortillaServer.
//     */
//    @Test
//    public void setFav()
//    {
//        System.out.println("setFav");
//        boolean newFav = false;
//        TortillaServer instance = null;
//        instance.setFav(newFav);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getHostname method, of class TortillaServer.
//     */
//    @Test
//    public void getHostname()
//    {
//        System.out.println("getHostname");
//        TortillaServer instance = null;
//        String expResult = "";
//        String result = instance.getHostname();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMap method, of class TortillaServer.
//     */
//    @Test
//    public void getMap()
//    {
//        System.out.println("getMap");
//        TortillaServer instance = null;
//        String expResult = "";
//        String result = instance.getMap();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMaxplayers method, of class TortillaServer.
//     */
//    @Test
//    public void getMaxplayers()
//    {
//        System.out.println("getMaxplayers");
//        TortillaServer instance = null;
//        int expResult = 0;
//        int result = instance.getMaxplayers();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPlayerCount method, of class TortillaServer.
//     */
//    @Test
//    public void getPlayerCount()
//    {
//        System.out.println("getPlayerCount");
//        TortillaServer instance = null;
//        int expResult = 0;
//        int result = instance.getPlayerCount();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPing method, of class TortillaServer.
//     */
//    @Test
//    public void getPing()
//    {
//        System.out.println("getPing");
//        TortillaServer instance = null;
//        int expResult = 0;
//        int result = instance.getPing();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setHostname method, of class TortillaServer.
//     */
//    @Test
//    public void setHostname()
//    {
//        System.out.println("setHostname");
//        String newHostName = "";
//        TortillaServer instance = null;
//        instance.setHostname(newHostName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMap method, of class TortillaServer.
//     */
//    @Test
//    public void setMap()
//    {
//        System.out.println("setMap");
//        String newMap = "";
//        TortillaServer instance = null;
//        instance.setMap(newMap);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMaxPlayers method, of class TortillaServer.
//     */
//    @Test
//    public void setMaxPlayers()
//    {
//        System.out.println("setMaxPlayers");
//        int newMax = 0;
//        TortillaServer instance = null;
//        instance.setMaxPlayers(newMax);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPlayerCount method, of class TortillaServer.
//     */
//    @Test
//    public void setPlayerCount()
//    {
//        System.out.println("setPlayerCount");
//        int newPlayers = 0;
//        TortillaServer instance = null;
//        instance.setPlayerCount(newPlayers);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPing method, of class TortillaServer.
//     */
//    @Test
//    public void setPing()
//    {
//        System.out.println("setPing");
//        int newPing = 0;
//        TortillaServer instance = null;
//        instance.setPing(newPing);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}