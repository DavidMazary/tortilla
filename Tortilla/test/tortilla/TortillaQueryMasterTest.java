package tortilla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests TortillaQueryMaster.
 * @author dmaz
 */
public class TortillaQueryMasterTest {

    TortillaQueryMaster instance;

    public TortillaQueryMasterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new TortillaQueryMaster();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMaster method, of class TortillaQueryMaster.
     */
    @Test
    public void testGetMaster() {
        System.out.println("testGetMaster");
        String result = instance.getMaster();
        System.out.println("Queried " + result);
        assertTrue(result.contains("ghdigital.com") ||
                result.contains("dpmaster.deathmask.net") ||
                result.contains("dpmaster.tchr.no"));
    }
    
    /**
     * Test of getServers method.
     */
    @Test
    public void testGetServers() {
//        BufferedReader input = null;
//        try {
            System.out.println("testGetServers");
//            input = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/servercache"));
            instance.saveServerList();
//            System.out.println(input.toString());
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TortillaQueryMasterTest.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                input.close();
//            } catch (IOException ex) {
//                Logger.getLogger(TortillaQueryMasterTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    
    /**
     * Test of getServers method.
     * This is an unreliable and useless test atm.
     */
    @Test
    public void testGetValue() {
        System.out.println("testGetValue");
//        String expResult = "75.126.234.42:26002";
        String result = instance.getValue("//////");
        System.out.println(result);
//        assertEquals(result, expResult);
    }
}