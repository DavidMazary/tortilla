package tortilla;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * TortillaQuery directly communicates with the master and
 * individual game servers. <br/>
 * The darkplaces dpmaster server is based on the Quake 3 Arena server
 * and therefore very similar. <br/>
 * Documentation of these protocols are:
 * <a href="ftp://ftp.idsoftware.com/idstuff/quake3/docs/server.txt">id's quake
 * 3 server commands howto</a>, 
 * <a href="http://svn.icculus.org/twilight/trunk/dpmaster/doc/techinfo.txt?view=markup">
 * dpmaster's Technical Information document</a> G. Armitage's
 * <a href="http://caia.swin.edu.au/reports/070730A/CAIA-TR-070730A.pdf">
 * Server Discovery for Quake III Arena, Wolfenstein Enemy Territory and Quake 4</a>
 * @author DeadEd
 */
public class TortillaQuery {
    // Timeout used for the sockets
    private static final int TIMEOUT = 2000;
    private static final int PACKET_SIZE = 12288;
    private static final int LOCAL_PORT = 5401;
    private static String challenge;

    /**
     * Builds the DatagramPacket we'll be using to query the server.
     * @param request   either getstatus, getinfo, or getservers
     * @param inet  IP address of server to query
     * @param port  Port to query server at
     * @return  DatagramPacket built by this method
     */
    public static DatagramPacket getDatagramPacket(String request,
            InetAddress inet, int port) {
        byte[] buff = request.getBytes();
        buff[0] = (byte) 0xff;		// indicates out-of-band command
        buff[1] = (byte) 0xff;
        buff[2] = (byte) 0xff;
        buff[3] = (byte) 0xff;
        return new DatagramPacket(buff, buff.length, inet, port);
    }

    /**
     * Sends a request to a game server and returns the output.
     * @param ipStr IP address of the server to query
     * @param port  Port of the server to query
     * @param request   Request message to send to server
     * @return  String of the server's reply
     */
    public static String getInfo(String ipStr, int port,
            String request) {

        StringBuffer recStr = new StringBuffer("");
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(LOCAL_PORT);
            InetAddress address = InetAddress.getByName(ipStr);

            socket.connect(address, port);

            DatagramPacket out = getDatagramPacket(request, address, port);
            socket.send(out);

            byte[] data = new byte[PACKET_SIZE];
            DatagramPacket inPacket = new DatagramPacket(data, PACKET_SIZE);
            socket.setSoTimeout(TIMEOUT);
            // get the response
            socket.receive(inPacket);
            recStr.append(new String(inPacket.getData(), 0,
                    inPacket.getLength(), "ISO-8859-1"));
        } catch (SocketTimeoutException ex) {
            recStr.append(new String("Request timed out"));
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
        return recStr.toString();
    }

    /**
     * Takes in an infoString and a part of that string, 
     * and outputs the value of that part, as a string.
     * For example, an infoString containing "/name/dave/" will return "dave"
     * when the given part is "name".
     * @param queryString   infoString we are searching
     * @param part  a command from the infoString
     * @return  value of the given part from the queryString
     */
    public static String getPart(String queryString, String part) {
        if ((queryString == null) || (queryString.length() <= 0)) {
            return "Bad queryString";
        }

        int start = queryString.toLowerCase().indexOf(
                "\\" + part.toLowerCase() + "\\") + 2 + part.length();
        if (start < (2 + part.length())) {
            return "ERROR: " + part + " not found in query string";
        }
        String tempStr = queryString.substring(start);
        int end = tempStr.indexOf("\\") + start;
        if (end <= start) {
            // check for a newline
            end = tempStr.indexOf("\n") + start;
            if (end <= start) {
                return "ERROR: " + part + " not found in query string";
            }
        }
        String tmp = queryString.substring(start, end);
        return tmp;
    }

    /**
     * Returns the String of our random challenge.
     * @return Randomly generated string to use as challenge.
     */
    public static String getChallenge() {
        return challenge;
    }

    /**
     * Randomly generates a challenge string to validate a server.
     */
    public static void generateChallenge() {
        Random random = new Random();
        int r1 = random.nextInt();
        challenge = Integer.toHexString(r1);
    }

    /**
     * Checks that the challenge response is correct.
     * This protects against fake servers.
     * This is from
     * <a href="ftp://ftp.idsoftware.com/idstuff/quake3/docs/server.txt">
     * id's quake 3 server commands howto</a>
     * @param in    String of output from querying server
     * @return true if challenge response passes
     */
    public static boolean checkChallenge(String in) {
        StringTokenizer tokens = new StringTokenizer(in, "\\");
        while (tokens.hasMoreTokens()) {
            String cmd = tokens.nextToken();
            if (!tokens.hasMoreTokens()) {
                return false;
            }
// in case someone tries to send through false package
            String val = tokens.nextToken();
            if (cmd.compareTo("challenge") == 0) {
                if (val.compareTo(getChallenge()) == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
