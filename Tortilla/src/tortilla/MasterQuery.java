package tortilla;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Queries the master server for the list of servers.
 *
 * @author dmaz
 */
public class MasterQuery extends AbstractQuery {

    private static final int DPMASTER_PORT = 27950;
    private static final String REQUEST = "xxxxgetservers Nexuiz 3 empty full";
//    private boolean done = false;
    /**
     * Retreives and saves the list of servers from the master server.
     * Note that servers containing the "\\" delimiter in their byte string
     * are probable.
     */
    public ArrayList<String> getServerList() {
        String queryResult = null;
        ArrayList<String> serverList = null;

        queryResult = getInfo(getMaster(), DPMASTER_PORT, REQUEST);

        if (queryResult != null) {
            serverList = new ArrayList<String>();
            int index = queryResult.indexOf("\\");
            String address;

            // Each IP address + delimiter is 7 characters
            while (index + 7 < queryResult.length()) {
                address = queryResult.substring(index + 1, index + 7);
                serverList.add(getValue(address));
                index += 7;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Could not reach master server");
        }
        return serverList;
    }

    /**
     * Choose a random master server to query, for load-balancing.
     * @return URL of master server.
     */
    protected String getMaster() {
        String[] master = {"ghdigital.com", "dpmaster.deathmask.net",
            "dpmaster.tchr.no"
        };
        Random r = new Random();
        return master[r.nextInt(3)];
    }

    /**
     * Translates bytes in strings to IP addresses and ports.
     * All numbers are big-endian oriented (most significant bytes first).
     * For instance, a server hosted at address 1.2.3.4 on port 2048 will
     * be sent as: "\x01\x02\x03\x04\x08\x00".
     * @param ip String containing the data.
     * @return  String with the decoded data.
     */
    protected String getValue(String ip) {
        String value = null;
        
        try {
            byte[] b = ip.getBytes("ISO-8859-1");
            int A = 0;
            int B = 0;
            int C = 0;
            int D = 0;
            int port = 0;
            A |= b[0] & 0xff;
            B |= b[1] & 0xff;
            C |= b[2] & 0xff;
            D |= b[3] & 0xff;
            port |= b[4] & 0xff;
            port <<= 8;
            port |= b[5] & 0xff;
            value = A + "." + B + "." + C + "." + D + ":" + port;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MasterQuery.class.getName()).log(
                    Level.SEVERE, null, ex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(MasterQuery.class.getName()).log(
                    Level.SEVERE, null, ex);
            System.err.println(ip);
        }
        return value;
    }
}
