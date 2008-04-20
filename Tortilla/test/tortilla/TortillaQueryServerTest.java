/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tortilla;

import java.util.ArrayList;
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
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test if server details are correctly queried.
     */
    @Test
    public void testQueryServer()
    {
        System.out.println("testQueryServer");
        TortillaServer server = queryS.getDetails("75.126.234.42", 26000);
        assertEquals("75.126.234.42", server.getIp());
        assertEquals(18, server.getMaxPlayers());
        assertEquals("Nexuiz", server.getGame());
        assertEquals("20000", server.getGameVersion());
    }
    
    /**
     * Test if server players are correctly queried.
     * Eyeball this one for now.
     */
    @Test
    public void testQueryServerPlayers()
    {
        System.out.println("testQueryServerPlayers");
        ArrayList < TortillaPlayer > players = 
                queryS.getPlayers("75.126.234.42", 26000);
        for (TortillaPlayer player : players) {
            System.out.println(player.getScore() + "\t" + player.getName());
        }
    }
    
//    /**
//     * Test if timed-out server is correctly handled.
//     */
//    @Test
//    public void testQueryTimeoutServer()
//    {
//        System.out.println("testQueryTimeoutServer");
//        TortillaServer server = queryS.getDetails("www.vt.edu", 26000);
//        assertTrue(server.getTimeout());
//    }
}