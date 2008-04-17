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
 * @author David
 */
public class TortillaGameLauncherTest {

    public TortillaGameLauncherTest() {
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
//
//    /**
//     * Test of isSdl method, of class TortillaGameLauncher.
//     */
//    @Test
//    public void isSdl()
//    {
//        System.out.println("isSdl");
//        TortillaGameLauncher instance = new TortillaGameLauncher();
//        boolean expResult = false;
//        boolean result = instance.isSdl();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSdl method, of class TortillaGameLauncher.
//     */
//    @Test
//    public void setSdl()
//    {
//        System.out.println("setSdl");
//        boolean sdl = false;
//        TortillaGameLauncher instance = new TortillaGameLauncher();
//        instance.setSdl(sdl);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of playGame method, of class TortillaGameLauncher.
     */
    @Test
    public void playGame()
    {
        System.out.println("playGame");
        TortillaGameLauncher instance = new TortillaGameLauncher();
        instance.setIp("private.optimalclan.com:26001");
        instance.playGame();
    }

//    /**
//     * Test of getIp method, of class TortillaGameLauncher.
//     */
//    @Test
//    public void getIp()
//    {
//        System.out.println("getIp");
//        TortillaGameLauncher instance = new TortillaGameLauncher();
//        String expResult = "";
//        String result = instance.getIp();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setIp method, of class TortillaGameLauncher.
//     */
//    @Test
//    public void setIp()
//    {
//        System.out.println("setIp");
//        String ip = "";
//        TortillaGameLauncher instance = new TortillaGameLauncher();
//        instance.setIp(ip);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}