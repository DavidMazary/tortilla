/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tortilla;

import tortilla.nexuiz.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dmaz
 */
public class PlayerTest {
    
    protected transient Player instance;

    @Before
    public void setUp() {
        instance = new Player();
    }

    /**
     * Test of isBot method, of class Player.
     */
    @Test
    public void isBot()
    {        
        System.out.println("isBot");
        instance.setPing("64");
        assertFalse(instance.isBot());
        instance.setPing("0");
        assertTrue(instance.isBot());
    }

    /**
     * Test of isSpec method, of class Player.
     */
    @Test
    public void isSpec()
    {
        System.out.println("isSpec");
        instance.setScore("64");
        assertFalse(instance.isSpec());
        instance.setScore("-666");
        assertTrue(instance.isSpec());
    }

    /**
     * Test of sanitizeName method, of class Player.
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

    /**
     * Tests ^0-^9 color codes for conversion.
     */
    @Test
    public void basicColoredName() {
        String expResult = "<font color=\"white\">[</font><font color=\"red\">Dave</font><font color=\"white\">]</font><font color=\"yellow\">D</font><font color=\"DimGray\">ave</font>";
        instance.setName("^7[^1Dave^7]^3D^8ave");
        assertEquals("[Dave]Dave", instance.getName());
        assertEquals(expResult, instance.getColoredName());
    }

    /**
     * Tests ^xXXX  color codes for conversion.
     */
    @Test
    public void hexColoredName() {
        instance.setName("^x089D^x078a^x05Dv^xB1Fe^x39F");
        assertEquals("Dave", instance.getName());
        assertEquals("<font color=\"#008899\">D</font><font color=\"#007788\">a</font><font color=\"#0055DD\">v</font><font color=\"#BB11FF\">e</font><font color=\"#3399FF\"></font>", instance.getColoredName());
    }
}