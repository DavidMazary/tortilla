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
        Server server = queryS.getInfo("private.optimalclan.com:26001");
        System.out.println("Ping: " + server.getPing());
        assertEquals("private.optimalclan.com:26001", server.getIp());
        assertEquals(64, server.getMaxPlayers());
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
        Server server = queryS.getStatus("74.52.14.98:26000");
        for (Player player : server.getPlayerList()) {
            System.out.println(player.getScore() + "\t" + player.getName());
        }
        assertEquals(server.getPlayerCount(), server.getPlayerList().size());
    }
}
