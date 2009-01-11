package tortilla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Query a particular game server using <code>getstatus</code>.
 * @author dmaz
 */
public class ServerQuery extends AbstractQuery {

    /**
     * Creates a Server containing all of the server info,
     * excluding player names, scores, and pings.
     * 
     * @param ipStr     String of IP address of this server
     * @return  Server containing the info of this server.
     */
    public Server getInfo(String ipStr) {
        String ip[] = ipStr.split(":");
        int port = Integer.parseInt(ip[1]);
        String queryResult = null;

        for (int i = 0; i < 3; i++) {
            queryResult = getInfo(ip[0], port,
                    "xxxxgetinfo tortilla");
            if (!queryResult.equals("0")) {
                break;
            }
        }

        Server tortillaServer = new Server();
        if (!queryResult.equals("0")) {
            tortillaServer = processServer(queryResult, ipStr);
        }
        return tortillaServer;
    }

    /**
     * Gets a tortillaserver including its list of tortillaplayers.
     *
     * @param ipStr     String of IP address of server
     * @return  Server including ArrayList of TortillaPlayers
     */
    public Server getStatus(String ipStr) {
        String ip[] = ipStr.split(":");
        int port = Integer.parseInt(ip[1]);
        Server tortillaServer = new Server();
        ArrayList<Player> tortillaPlayers =
                new ArrayList<Player>();
        String queryResult = null;

        for (int i = 0; i < 2; i++) {
            queryResult = getInfo(ip[0], port,
                    "xxxxgetstatus tortilla");
            if (!queryResult.equals("0")) {
                break;
            }
        }

        if (!queryResult.equals("0")) {
            queryResult = queryResult.substring(queryResult.indexOf("\\"));
            queryResult = queryResult.replaceAll(
                    "\\^([0-9a-wyzA-WYZ]|x[0-9a-fA-F]{6})", "");
            String input;

            try {
                BufferedReader in = new BufferedReader(
                        new StringReader(queryResult));
                if ((input = in.readLine()) != null) {
                    tortillaServer = processServer(input, ipStr);
                    while ((input = in.readLine()) != null) {
                        tortillaPlayers.add(processPlayer(input));
                    }
                    tortillaServer.setPlayerList(tortillaPlayers);
                }
            } catch (IOException ex) {
                Logger.getLogger(TortillaView.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return tortillaServer;
    }

    /**
     * From a line in the queryResult, retreive player info.
     * @param queryResult String of the line to process.
     * @return Player which is created.
     */
    protected Player processPlayer(String queryResult) {
        Scanner scanner = new Scanner(queryResult);
        int score = scanner.nextInt();
        int ping = scanner.nextInt();
        scanner.useDelimiter("\"");
        scanner.next();
        String name = scanner.next();
        Player player = new Player();
        player.setScore(score);
        // Some leading and trailing chars may be converted to whitespace.
        player.setName(name.trim());
        player.setPing(ping);
        return player;
    }

    /**
     * Take the server info from the queryResult and build a Server.
     * Response will look like this: <code>\gamename\Nexuiz\modname\data\gameversion\20000\sv_maxclients\18\clients\6\bots\0\mapname\ctctf6\hostname\Galt's Gulch - CTF - 2.4 - TX, USA\protocol\3\challenge\tortilla</code>
     * @param queryResult String of the server info.
     * @param ipStr The address of the server.
     * @return Server which is created.
     */
    protected Server processServer(String queryResult, String ipStr) {
        Server server = new Server();
        String[] serverData = queryResult.split("\\\\");

        server.setGame(serverData[2]);
        server.setModname(serverData[4]);
        server.setGameVersion(serverData[6]);
        server.setMaxPlayers(Integer.parseInt(serverData[8]));
        server.setPlayerCount(Integer.parseInt(serverData[10]));
        if (serverData[11].equals("bots")) {
            server.setBotCount(Integer.parseInt(serverData[12]));
            server.setMap(serverData[14]);
            server.setHostname(serverData[16]);
        } else {
            // queryResult does not contain bot count
            server.setMap(serverData[12]);
            server.setHostname(serverData[14]);
        }
        server.setPing(getPing());
        server.setIp(ipStr);
        return server;
    }
}
