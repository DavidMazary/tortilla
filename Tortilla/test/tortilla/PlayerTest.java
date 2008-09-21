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
public class PlayerTest {
    
    Player instance;

    public PlayerTest() {
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
        instance = new Player();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isBot method, of class Player.
     */
    @Test
    public void isBot()
    {        
        System.out.println("isBot");
        instance.setPing(64);
        assertFalse(instance.isBot());
        instance.setPing(0);
        assertTrue(instance.isBot());
    }

    /**
     * Test of isSpec method, of class Player.
     */
    @Test
    public void isSpec()
    {
        System.out.println("isSpec");
        instance.setScore(64);
        assertFalse(instance.isSpec());
        instance.setScore(-666);
        assertTrue(instance.isSpec());
    }

    /**
     * Test of translateName method, of class Player.
     */
    @Test
    public void testTranslateName()
    {
        System.out.println("testTranslateName");
        String expResult = "COUICOUI";
        instance.setName("ÃÏÕÉÃÏÕÉ");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

}