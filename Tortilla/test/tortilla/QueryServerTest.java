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
 * @author dmaz
 */
public class QueryServerTest {

    ServerQuery queryS;

    public QueryServerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        queryS = new ServerQuery();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test if server details are correctly queried.
     */
    @Test
    public void testQueryServer() {
        System.out.println("testQueryServer");
        Server server = queryS.getInfo("75.126.234.42:26000");
        System.out.println("Ping: " + server.getPing());
        assertEquals("75.126.234.42:26000", server.getIp());
        assertEquals(24, server.getMaxPlayers());
        assertEquals("Nexuiz", server.getGame());
        assertEquals("20000", server.getGameVersion());
    }

    /**
     * Test if server players are correctly queried.
     * Eyeball this one for now.
     */
    @Test
    public void testQueryServerPlayers() {
        System.out.println("testQueryServerPlayers");
        Server server =
                queryS.getStatus("75.126.234.42:26000");
        for (Player player : server.getPlayerList()) {
            System.out.println(player.getScore() + "\t" + player.getName());
        }
        fail("Write a proper test for this.");
    }
//    /**
//     * Test if timed-out server is correctly handled.
//     */
//    @Test
//    public void testQueryTimeoutServer()
//    {
//        System.out.println("testQueryTimeoutServer");
//        Server server = queryS.getInfo("www.vt.edu", 26000);
//        assertTrue(server.getTimeout());
//    }
}
