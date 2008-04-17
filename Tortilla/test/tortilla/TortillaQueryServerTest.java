/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tortilla;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class TortillaQueryServerTest {    
    
    TortillaQueryServer queryS;

    public TortillaQueryServerTest() {
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
        queryS = new TortillaQueryServer();
        queryS.setIp("75.126.234.42:26000"); // Galt's Gulch CTF
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test if server is correctly queried.
     */
    @Test
    public void testQueryServer()
    {
        System.out.println("testQueryServer");
        queryS.qstat();
        assertEquals(queryS.getServer().getMaxplayers(), 18);
        System.out.println(queryS.getServer().getHostname());
    }
    
//    /**
//     * Test if server's player count matches number of player objects
//     * contained in this server object.
//     */
//    @Test
//    public void testQueryServerPlayers()
//    {
//        System.out.println("testQueryServerPlayers");
//        queryS.qstat();
//        System.out.println(queryS.getServer().getPlayers() + ":" +
//                queryS.getServer().getPlayerList().size());
//        assertEquals(queryS.getServer().getPlayers(),
//                queryS.getServer().getPlayerList().size());
//    }
        
    
//    /**
//     * Test if timed-out server is correctly handled.
//     */
//    @Test
//    public void testQueryTimeoutServer()
//    {
//        System.out.println("testQueryTimeoutServer");
//        queryS.setIp("www.vt.edu");
//        queryS.qstat();
//        assertTrue(queryS.getServer().getTimeout());
//    }

}