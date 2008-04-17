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
public class TortillaPlayerTest {
    
    TortillaPlayer instance;

    public TortillaPlayerTest() {
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
        instance = new TortillaPlayer("Dave", 3, 30);
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of setName method, of class TortillaPlayer.
//     */
//    @Test
//    public void setName()
//    {
//        System.out.println("setName");
//        String newName = "";
//        TortillaPlayer instance = null;
//        instance.setName(newName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPlainName method, of class TortillaPlayer.
//     */
//    @Test
//    public void setPlainName()
//    {
//        System.out.println("setPlainName");
//        String newPlainName = "";
//        TortillaPlayer instance = null;
//        instance.setPlainName(newPlainName);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setScore method, of class TortillaPlayer.
//     */
//    @Test
//    public void setScore()
//    {
//        System.out.println("setScore");
//        short newScore = 0;
//        TortillaPlayer instance = null;
//        instance.setScore(newScore);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPing method, of class TortillaPlayer.
//     */
//    @Test
//    public void setPing()
//    {
//        System.out.println("setPing");
//        short newPing = 0;
//        TortillaPlayer instance = null;
//        instance.setPing(newPing);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getName method, of class TortillaPlayer.
//     */
//    @Test
//    public void getName()
//    {
//        System.out.println("getName");
//        TortillaPlayer instance = null;
//        String expResult = "";
//        String result = instance.getName();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getScore method, of class TortillaPlayer.
//     */
//    @Test
//    public void getScore()
//    {
//        System.out.println("getScore");
//        TortillaPlayer instance = null;
//        short expResult = 0;
//        short result = instance.getScore();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPing method, of class TortillaPlayer.
//     */
//    @Test
//    public void getPing()
//    {
//        System.out.println("getPing");
//        TortillaPlayer instance = null;
//        short expResult = 0;
//        short result = instance.getPing();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of isBot method, of class TortillaPlayer.
     */
    @Test
    public void isBot()
    {
        System.out.println("isBot");
        assertFalse(instance.isBot());
        instance.setPing(0);
        assertTrue(instance.isBot());
    }

    /**
     * Test of isSpec method, of class TortillaPlayer.
     */
    @Test
    public void isSpec()
    {
        System.out.println("isSpec");
        assertFalse(instance.isSpec());
        instance.setScore(-666);
        assertTrue(instance.isSpec());
    }

//    /**
//     * Test of getPlainName method, of class TortillaPlayer.
//     */
//    @Test
//    public void getPlainName()
//    {
//        System.out.println("getPlainName");
//        String expResult = "Dave";
//        instance.setPlainName();
//        String result = instance.getPlainName();
//        assertEquals(expResult, result);
//    }

}