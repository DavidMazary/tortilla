package tortilla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Query a particular game server using <code>getstatus</code>.
 * @author dmaz
 */
public class TortillaQueryServer {

    /**
     * Creates a TortillaServer containing all of the server info,
     * excluding player names, scores, and pings.
     * Response will look like this: <code>\gamename\Nexuiz\modname\data
     * \gameversion\20000\sv_maxclients\18\clients\6\bots\0\mapname\ctctf6
     * \hostname\Galt's Gulch - CTF - 2.4 - TX, USA\protocol\3\challenge\dd03ce05</code>
     *
     * @param ipStr     String of IP address of this server
     * @param port      Port this server is running on
     * @return  TortillaServer containing the info of this server.
     */
    public TortillaServer getInfo(String ipStr) {
        TortillaQuery.generateChallenge();
        String ip[] = ipStr.split(":");
        int port = Integer.parseInt(ip[1]);
        String queryResult = TortillaQuery.getInfo(ip[0], port,
                "xxxxgetinfo " + TortillaQuery.getChallenge());

        TortillaServer tortillaServer = new TortillaServer();
        if (queryResult != null && queryResult.length() > 0
                && !queryResult.equals("Request timed out.")) {
            queryResult = queryResult.substring(queryResult.indexOf("\\"));
            StringTokenizer tokens = new StringTokenizer(queryResult, "\\");
            if (tokens.nextToken().equals("gamename")) {
                tortillaServer.setGame(tokens.nextToken());
            }
            // Change this if we want to start storing modname
            if (tokens.nextToken().equals("modname") && tokens.nextToken().equals("data") && tokens.nextToken().equals("gameversion")) {
                tortillaServer.setGameVersion(tokens.nextToken());
            }
            if (tokens.nextToken().equals("sv_maxclients")) {
                tortillaServer.setMaxPlayers(tokens.nextToken());
            }
            if (tokens.nextToken().equals("clients")) {
                tortillaServer.setPlayerCount(tokens.nextToken());
            }
            if (tokens.nextToken().equals("bots")) {
                tokens.nextToken(); // not counting the bots
                if (tokens.nextToken().equals("mapname")) {
                    tortillaServer.setMap(tokens.nextToken());
                }
            }
            if (tokens.nextToken().equals("hostname")) {
                tortillaServer.setHostname(tokens.nextToken());
            }
            tortillaServer.setPing(TortillaQuery.getPing());
            tortillaServer.setIp(ipStr);
        }
        return tortillaServer;
    }

    /**
     * Gets an ArrayList of the players on this server.
     *
     * @param ipStr     String of IP address of server
     * @param port      Port server is listening on
     * @return  ArrayList of the TortillaPlayers on this server
     */
    public ArrayList<TortillaPlayer> getStatus(String ipStr) {
        TortillaQuery.generateChallenge();
        String ip[] = ipStr.split(":");
        int port = Integer.parseInt(ip[1]);
        ArrayList<TortillaPlayer> tortillaPlayer =
                new ArrayList<TortillaPlayer>();
        String queryResult = TortillaQuery.getInfo(ip[0], port,
                "xxxxgetstatus " + TortillaQuery.getChallenge());
        if (queryResult == null || queryResult.length() < 1 || TortillaQuery.checkChallenge(
                queryResult.substring(0, queryResult.indexOf('\n')))) {
            return tortillaPlayer;
        }

        queryResult = queryResult.substring(queryResult.indexOf("\\"));
        queryResult = queryResult.replaceAll(
                "\\^([0-9a-wyzA-WYZ]|x[0-9a-fA-F]{6})", "");

        if (queryResult != null || queryResult.length() > 0) {
            String input;
            try {
                BufferedReader in = new BufferedReader(
                        new StringReader(queryResult));
                in.readLine();
                while ((input = in.readLine()) != null) {
                    tortillaPlayer.add(processPlayer(input));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return tortillaPlayer;
    }

    /**
     * From a line in the queryResult, retreive player info.
     * @param queryResult String of the line to process.
     * @return TortillaPlayer which is created.
     */
    protected TortillaPlayer processPlayer(String queryResult) {
        Scanner scanner = new Scanner(queryResult);
        int score = scanner.nextInt();
        int ping = scanner.nextInt();
        scanner.useDelimiter("\"");
        scanner.next();
        String name = scanner.next();
        TortillaPlayer player = new TortillaPlayer();
        player.setScore(score);
        // Some leading and trailing chars may be converted to whitespace.
        player.setName(name.trim());
        player.setPing(ping);
        return player;
    }
}
