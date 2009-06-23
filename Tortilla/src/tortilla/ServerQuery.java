package tortilla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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

        querySuccess = false;
        int queryTries = 0;
        while (!querySuccess && (queryTries < 3)) {
            queryResult = getInfo(ip[0], port,
                    "xxxxgetinfo tortilla");
            queryTries++;
        }

        Server server = null;
        if (querySuccess) {
            server = getServerFromResponse(queryResult, ipStr);
        }
        return server;
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
        Server server = null;
        ArrayList<Player> players = new ArrayList<Player>();
        String queryResult = null;

        querySuccess = false;
        int queryTries = 0;
        while (!querySuccess && (queryTries < 3)) {
            queryResult = getInfo(ip[0], port,
                    "xxxxgetstatus tortilla");
            queryTries++;
        }

        if (querySuccess) {
            queryResult = queryResult.substring(queryResult.indexOf("\\"));
            String input;

            try {
                BufferedReader in = new BufferedReader(
                        new StringReader(queryResult));
                if ((input = in.readLine()) != null) {
                    server = getServerFromResponse(input, ipStr);
                    while ((input = in.readLine()) != null) {
                        players.add(getPlayerFromResponse(input));
                    }
                    server.setPlayerList(players);
                }
            } catch (IOException ex) {
                Logger.getLogger(TortillaView.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return server;
    }

    /**
     * From a line in the queryResult, retreive player info.
     * @param queryResult String of the line to process.
     * @return Player which is created.
     */
    protected Player getPlayerFromResponse(String queryResult) {
        String[] playerName = queryResult.split("\"");
        String[] playerData = playerName[0].split(" ");
        Player player = new Player();
        player.setScore(playerData[0]);
        player.setPing(playerData[1]);
        if (playerData.length == 3) {
            player.setTeam(playerData[2]);
        }
        player.setName(playerName[1]);
        return player;
    }

    /**
     * Take the server info from the queryResult and build a Server.
     * Response will look like this: <code>\gamename\Nexuiz\modname\data\gameversion\20000\sv_maxclients\24\clients\4\bots\2\mapname\dance\hostname\[o8] Nexican v2.5.1\protocol\3\qcstatus\ctf:2.5.1::score!!:score!!,caps!:5:22,1:14:73,3\challenge\tortilla</code>
     * TODO: Handle qcstatus info
     * @param queryResult String of the server info.
     * @param ipStr The address of the server.
     * @return Server which is created.
     */
    protected Server getServerFromResponse(String queryResult, String ipStr) {
        Server server = new Server();
        String[] serverData = queryResult.split("\\\\");

        server.setGame(serverData[2]);
        server.setModname(serverData[4]);
        server.setGameVersion(serverData[6]);
        server.setMaxPlayers(serverData[8]);
        server.setPlayerCount(serverData[10]);
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
