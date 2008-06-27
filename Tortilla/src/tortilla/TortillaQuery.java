package tortilla;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
 * @author DeadEd, dmaz
 */
public class TortillaQuery {
    // Timeout used for the sockets

    private static final int TIMEOUT = 2000;
    private static final int PACKET_SIZE = 14400;
    private int ping;

    /**
     * Builds the DatagramPacket we'll be using to query the server.
     * @param request   either getstatus, getinfo, or getservers
     * @param inet  IP address of server to query
     * @param port  Port to query server at
     * @return  DatagramPacket built by this method
     */
    public DatagramPacket getDatagramPacket(String request,
            InetAddress inet, int port) {
        byte[] buff = request.getBytes();
        buff[0] = (byte) 0xff;
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
    public String getInfo(String ipStr, int port,
            String request) {
        DatagramSocket socket = null;
        try {

            StringBuffer recStr = new StringBuffer("");
            // Use port range 25000 - 30000
            Random r = new Random();
            int localPort = r.nextInt(5000) + 25000;
            socket = new DatagramSocket(localPort);
            InetAddress address = InetAddress.getByName(ipStr);
            byte[] data = new byte[PACKET_SIZE];
            DatagramPacket inPacket = new DatagramPacket(data, PACKET_SIZE);

            socket.connect(address, port);

            DatagramPacket out = getDatagramPacket(request, address, port);
            socket.send(out);
            long sendTime = System.currentTimeMillis(); // ping timer

            socket.setSoTimeout(TIMEOUT);
            // get the response
            socket.receive(inPacket);
            long receiveTime = System.currentTimeMillis();
            ping = (int) (receiveTime - sendTime);
            recStr.append(new String(inPacket.getData(), 0, inPacket.getLength(),
                    "ISO-8859-1"));
            return recStr.toString();
        } catch (IOException ex) {
            return "0";
        }
    }

    /**
     * Returns the time elapsed in the communication.
     * This seems the best way to simulate <code>ping</code>
     * since Java doesn't do ICMP.
     * @return Int of ping-time to server in milliseconds.
     */
    public int getPing() {
        return ping;
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
    public boolean checkChallenge(String in) {
        StringTokenizer tokens = new StringTokenizer(in, "\\");
        while (tokens.hasMoreTokens()) {
            String cmd = tokens.nextToken();
            if (!tokens.hasMoreTokens()) {
                return false;
            }
            // in case someone tries to send through false package
            String val = tokens.nextToken();
            if (cmd.compareTo("challenge") == 0) {
                if (val.compareTo("tortilla") == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
