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
    private static final int MESSAGE_START = 22;
    private static final String REQUEST = "xxxxgetservers Nexuiz 3 empty full";
    private static final String[] MASTER_ADDRESSES = {"ghdigital.com",
        "dpmaster.deathmask.net", "dpmaster.tchr.no"};

    /**
     * Retreives and saves the list of servers from the master server.
     * Note that servers containing the "\\" delimiter in their byte string
     * are probable.
     * @return An ArrayList of server query results
     */
    public ArrayList<String> getServerList() {
        ArrayList<String> serverList = null;
        String queryResult = null;

        long queryStartTime = System.currentTimeMillis();
        int queryTries = 0;
        while ((queryResult == null) && (queryTries < RETRIES)) {
            queryResult = getInfo(getMasterAddress(), DPMASTER_PORT, REQUEST);
            queryTries++;
        }

        if (queryResult != null) {
            serverList = new ArrayList<String>();
            int index = MESSAGE_START;
            String byteAddress;

            // 1 delimiter + 6 bytes
            while (index + 7 < queryResult.length()) {
                byteAddress = queryResult.substring(index + 1, index + 7);
                serverList.add(getAddressFromBytes(byteAddress));
                index += 7;
            }
        } else {
            // Notify the user since there may be a problem with their connection.
            long queryTime = (System.currentTimeMillis() - queryStartTime);
            if (queryTime < 2000) {
                JOptionPane.showMessageDialog(null, "Could not reach master server");
            } else {
                JOptionPane.showMessageDialog(null, "No reply from master server");
            }
        }
        return serverList;
    }

    /**
     * Choose a random master server to query, for load-balancing.
     * @return address of master server.
     */
    protected String getMasterAddress() {
        return MASTER_ADDRESSES[new Random().nextInt(3)];
    }

    /**
     * Translates bytes in strings to IP addresses and ports.
     * All numbers are big-endian oriented (most significant bytes first).
     * For instance, a server hosted at address 1.2.3.4 on port 2048 will
     * be sent as: "\x01\x02\x03\x04\x08\x00".
     * @param ip String containing the data.
     * @return  String with the decoded data.
     */
    protected String getAddressFromBytes(String ip) {
        String address = null;

        try {
            byte[] bytes = ip.getBytes("ISO-8859-1");
            int A = bytes[0] & 0xff;
            int B = bytes[1] & 0xff;
            int C = bytes[2] & 0xff;
            int D = bytes[3] & 0xff;
            int port = bytes[4] & 0xff;
            port <<= 8;
            port |= bytes[5] & 0xff;
            address = A + "." + B + "." + C + "." + D + ":" + port;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MasterQuery.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return address;
    }
}
