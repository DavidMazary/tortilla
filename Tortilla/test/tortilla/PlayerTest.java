package tortilla;

import tortilla.xonotic.Player;
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
        String expResult = "DAVE";
        instance.setName("");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of sanitizeName method on Cyrillic string.
     */
    @Test
    public void testTranslateUnicodeName()
    {
        System.out.println("testTranslateUnicodeName");
        String expResult = "Дейв";
        instance.setName("Дейв");
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Tests ^0-^9 color codes for conversion.
     */
    @Test
    public void basicColoredName() {
        String expResult = "<span style='color:#FFFFFF'>[<span style='color:#FF9900'>Dave<span style='color:#FFFFFF'>]<span style='color:#FFFF00'>D<span style='color:#999999'></span></span></span></span></span>";
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
        assertEquals("<span style='color:#008899'>D<span style='color:#007788'>a<span style='color:#0055DD'>v<span style='color:#BB11FF'>e<span style='color:#3399FF'></span></span></span></span></span>", instance.getColoredName());
    }
}