package tortilla;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Queries the master server for the list of servers.
 * Creates TortillaServer objects.
 * Puts those into a ConcurrentHashMap accessible with getServers();
 *
 * @author David
 */
public class TortillaQueryMaster {

    private static final int DPMASTER_PORT = 27950;
    private static final String REQUEST = "xxxxgetservers Nexuiz 3";

    /**
     * The serverList ConcurrentHashMap.
     * @return ConcurrentHashMap of serverList.
     */
    public ArrayList < String > getServers() {
        ArrayList < String > serverList =
                new ArrayList < String >();
        TortillaServer server;

        String queryResult = TortillaQuery.getInfo(getMaster(),
                DPMASTER_PORT, REQUEST);
        System.out.println(queryResult);
        StringTokenizer tokens = new StringTokenizer(queryResult, "\\");
        tokens.nextToken(); // first token is the response text
        while (tokens.hasMoreTokens()) {
            String address = tokens.nextToken();
            if (address.equals("EOT")) {
                break;
            } else {
                String ip = getValue(address);
                serverList.add(ip);
            }
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
        return master[(int) (Math.random() * 3)];
    }

    /**
     * Translates bytes in strings to IP addresses and ports.
     * All numbers are big-endian oriented (most significant bytes first). 
     * For instance, a server hosted at address 1.2.3.4 on port 2048 will 
     * be sent as: "\x01\x02\x03\x04\x08\x00".
     * @param address String containing the data.
     * @return  String with the decoded data.
     */
    protected String getValue(String ip) {
        try {
            byte b[] = ip.getBytes("ISO-8859-1");
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
