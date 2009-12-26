package tortilla.nexuiz;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * AbstractQuery directly communicates with the master and
 * individual game servers. <br/>
 * The darkplaces dpmaster server is based on the Quake 3 Arena server
 * and therefore very similar. <br/>
 * Based on
 * <a href="ftp://ftp.idsoftware.com/idstuff/quake3/docs/server.txt"> id's quake
 * 3 server commands howto</a>
 * @author dmaz
 */
public abstract class AbstractQuery {

    private static final int TIMEOUT = 2000;          // Timeout used for the sockets
    private static final int PACKET_SIZE = 2048;      // Receive packet size
    private static final int TYPE_OF_SERVICE = 0x10;  // Type of Service octet
    private static final String CHARSET = "ISO-8859-1";
    protected static final int RETRIES = 2;
    protected int ping;

    /**
     * Sends a request to a game server and returns the output.
     * @param address of the server to query
     * @param port of the server to query
     * @param request message to send to server
     * @return  String of the server's reply
     */
    protected String getInfo(String address, int port, String request) {
        String queryInfo;
        long sendTime, receiveTime;
        byte[] requestBytes = request.getBytes();
        requestBytes[0] |= 0xff;  // Change first 4 chars to 0xff
        requestBytes[1] |= 0xff;
        requestBytes[2] |= 0xff;
        requestBytes[3] |= 0xff;
        DatagramPacket sendPacket = new DatagramPacket(requestBytes, requestBytes.length);
        DatagramPacket receivePacket = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(TIMEOUT);
            socket.setTrafficClass(TYPE_OF_SERVICE);
            socket.connect(InetAddress.getByName(address), port);
            socket.send(sendPacket);
            sendTime = System.currentTimeMillis();  // ping timer
            socket.receive(receivePacket);
            receiveTime = System.currentTimeMillis();
            socket.close();
            ping = (int) (receiveTime - sendTime);
            queryInfo = new String(receivePacket.getData(), 0, receivePacket.getLength(), CHARSET);
        } catch (IOException ex) {
            queryInfo = null;
            if (socket != null) {
                socket.close();
            }
        }

        return queryInfo;
    }
}
