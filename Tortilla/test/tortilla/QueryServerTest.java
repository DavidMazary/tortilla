package tortilla;

import tortilla.xonotic.query.ServerQuery;
import tortilla.xonotic.Server;
import tortilla.xonotic.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dmaz
 */
public class QueryServerTest {

    /**
     * Test if server details are correctly queried.
     */
    @Test
    public void testQueryServer() {
        System.out.println("testQueryServer");
        Server server = ServerQuery.getInfo("94.23.21.40:26000");
        System.out.println("Ping: " + server.getPing());
        assertEquals("[R×M]Xonotic Beckstein vs Stoiber {X}", server.getHostname());
        assertEquals("94.23.21.40:26000", server.getIp());
        assertEquals(16, server.getMaxPlayers());
        assertEquals("Xonotic", server.getGame());
        assertEquals("500", server.getGameVersion());
    }

    /**
     * Test if server players are correctly queried.
     * Eyeball this one for now.
     */
    @Test
    public void testQueryServerPlayers() {
        System.out.println("testQueryServerPlayers");
        Server server = ServerQuery.getStatus("94.23.21.40:26000");
        for (Player player : server.getPlayerList()) {
            System.out.println(player.getScore() + "\t" + player.getName());
        }
        assertEquals(server.getPlayerCount(), server.getPlayerList().size());
    }
}
