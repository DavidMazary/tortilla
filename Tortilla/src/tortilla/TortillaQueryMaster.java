package tortilla;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Queries the master server for the list of servers.
 *
 * @author dmaz
 */
public class TortillaQueryMaster {

    private TortillaQuery query = new TortillaQuery();
    private static final int DPMASTER_PORT = 27950;
    private static final String REQUEST = "xxxxgetservers Nexuiz 3 empty full";

    /**
     * Retreives and saves the list of servers from the master server.
     * @todo Modify while to handle strings containing the "\\" delimiter.
     */
    public void saveServerList() {
        String queryResult = null;
        BufferedWriter writer = null;

        for (int i = 0; i < 3; i++) {
            queryResult = query.getInfo(getMaster(), DPMASTER_PORT, REQUEST);
            if (queryResult != null) {
                break;
            }
        }
        if (queryResult != null) {
            try {
                File serverCache = new File("servercache");
                writer = new BufferedWriter(new FileWriter(serverCache));
                StringTokenizer tokens = new StringTokenizer(queryResult, "\\");
                tokens.nextToken(); // first token is the response text

                while (tokens.hasMoreTokens()) {
                    String address = tokens.nextToken();
                    if (address.contains("EOT")) {
                        break;
//                    } else if (address.length() == 5) {
//                        writer.write(getValue("\\" + address) + "\n");
                    } else if (address.length() == 6) {
                        writer.write(getValue(address) + "\n");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(TortillaQueryMaster.class.getName()).log(
                        Level.SEVERE, null, ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(TortillaQueryMaster.class.getName()).log(
                            Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Could not reach master server");
        }
    }

    /**
     * Choose a random master server to query, for load-balancing.
     * @return URL of master server.
     */
    protected String getMaster() {
        String[] master = {"ghdigital.com", "dpmaster.deathmask.net",
            "dpmaster.tchr.no"
        };
        return master[(int) (Math.random() * 3)];
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
            port |= b[4] & 0xFF;
            port <<= 8;
            port |= b[5] & 0xFF;
            return A + "." + B + "." + C + "." + D + ":" + port;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TortillaQueryMaster.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        } catch (ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(TortillaQueryMaster.class.getName()).log(
                    Level.SEVERE, null, ex);
            System.err.println(ip);
            return null;
        }
    }
}
