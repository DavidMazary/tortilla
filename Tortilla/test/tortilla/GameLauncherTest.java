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
public class GameLauncherTest {

    public GameLauncherTest() {
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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of playGame method, of class TortillaGameLauncher.
     * This test will only pass if you put Nexuiz in your Tortilla project
     * folder.
     */
    @Test
    public void playGame()
    {
        System.out.println("playGame");
        GameLauncher instance = new GameLauncher();
        instance.setIp("private.optimalclan.com:26001");
        fail("Write this test");
    }
}