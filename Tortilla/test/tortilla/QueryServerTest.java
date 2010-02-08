package tortilla;

import tortilla.nexuiz.query.ServerQuery;
import tortilla.nexuiz.Server;
import tortilla.nexuiz.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dmaz
 */
public class QueryServerTest {

    protected transient ServerQuery queryS;

    @Before
    public void setUp() {
        queryS = ServerQuery.getInstance();
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
        Server server = queryS.getStatus("private.optimalclan.com:26001");
        for (Player player : server.getPlayerList()) {
            System.out.println(player.getScore() + "\t" + player.getName());
        }
        assertEquals(server.getPlayerCount(), server.getPlayerList().size());
    }
}
