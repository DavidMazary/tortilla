package tortilla.xonotic.query;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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
    private static final String REQUEST = "xxxxgetservers Xonotic 3 empty full";
    private static final String[] MASTER_ADDRESSES = {"ghdigital.com", "dpmaster.deathmask.net", "dpmaster.tchr.no"};

    /**
     * Singleton
     */
    private MasterQuery() {
        super();
    }

    private static class SingletonHolder {

        private static final MasterQuery INSTANCE = new MasterQuery();
    }

    public static MasterQuery getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Retreives and saves the list of servers from the master server.
     * Note that servers containing the "\\" delimiter in their byte string
     * are probable.
     * @return An ArrayList of server query results
     */
    public static List<String> getServerList() {
        List<String> serverList = null;
        byte[] response = null;

        final long queryStartTime = System.currentTimeMillis();
        int queryTries = 0;
        while ((response == null) && (queryTries < RETRIES)) {
            response = getInfo(getMasterAddress(), DPMASTER_PORT, REQUEST);
            queryTries++;
        }

        if (response == null) {
            // Notify the user since there may be a problem with their connection.
            if ((System.currentTimeMillis() - queryStartTime) < 2000) {
                JOptionPane.showMessageDialog(null, "Could not reach master server");
            } else {
                JOptionPane.showMessageDialog(null, "No reply from master server");
            }
        } else {
            serverList = new ArrayList<String>();
            int index = MESSAGE_START;
            int OFFSET = 7;  // 1 delimiter + 6 bytes
            byte[] byteAddress = new byte[OFFSET];
            while (index + OFFSET < response.length) {
                System.arraycopy(response, index + 1, byteAddress, 0, OFFSET);
                serverList.add(getAddressFromBytes(byteAddress));
                index += OFFSET;
            }
        }
        return serverList;
    }

    /**
     * Choose a random master server to query, for load-balancing.
     * @return address of master server.
     */
    private static String getMasterAddress() {
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
    public static String getAddressFromBytes(final byte[] bytes) {
        String address = null;
        final int A = bytes[0] & 0xff;
        final int B = bytes[1] & 0xff;
        final int C = bytes[2] & 0xff;
        final int D = bytes[3] & 0xff;
        int port = bytes[4] & 0xff;
        port <<= 8;
        port |= bytes[5] & 0xff;
        address = A + "." + B + "." + C + "." + D + ":" + port;
        return address;
    }
}
