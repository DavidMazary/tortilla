package tortilla;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Server.
 * @author dmaz
 */
public class ServerTest {
    
    Server instance;

    public ServerTest() {
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
        instance = new Server();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isFull method, of class Server.
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
     * Test of isEmpty method, of class Server.
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
}