package tortilla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Query a particular game server using <code>getstatus</code>.
 * @author david
 */
public class TortillaQueryServer {

    /**
     * Creates a TortillaServer containing all of the server info,
     * excluding player names, scores, and pings.
     *
     * @param ipStr     String of IP address of this server
     * @param port      Port this server is running on
     * @return  TortillaServer containing the info of this server.
     */
    public TortillaServer getDetails(String ipStr) {
        TortillaQuery.generateChallenge();
        int port = Integer.getInteger(ipStr.substring((ipStr.length() - 5)));
        String queryResult = TortillaQuery.getInfo(ipStr, port,
                "xxxxgetinfo " + TortillaQuery.getChallenge());

        TortillaServer tortillaServer = null;
        if (queryResult != null && queryResult.length() > 0) {
            queryResult = queryResult.substring(queryResult.indexOf("\\"));
            queryResult = queryResult.replaceAll("\\^([0-9a-wyzA-WYZ]|x[0-9a-fA-F]{6})", "");
            if (TortillaQuery.checkChallenge(queryResult)) {
                tortillaServer = new TortillaServer();
                tortillaServer.setGame(
                        TortillaQuery.getPart(queryResult, "gamename"));
                tortillaServer.setIp(ipStr);
                tortillaServer.setHostname(
                        TortillaQuery.getPart(queryResult, "hostname"));
                tortillaServer.setPlayerCount(
                        TortillaQuery.getPart(queryResult, "clients"));
                tortillaServer.setMaxPlayers(
                        TortillaQuery.getPart(queryResult, "sv_maxclients"));
                tortillaServer.setMap(
                        TortillaQuery.getPart(queryResult, "mapname"));
                tortillaServer.setGameVersion(
                        TortillaQuery.getPart(queryResult, "gameversion"));
            }
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
    public ArrayList<TortillaPlayer> getPlayers(String ipStr, int port) {
        TortillaQuery.generateChallenge();
        ArrayList<TortillaPlayer> tortillaPlayer =
                new ArrayList<TortillaPlayer>();
        String queryResult = TortillaQuery.getInfo(ipStr, port,
                "xxxxgetstatus " + TortillaQuery.getChallenge());
        if (queryResult == null || queryResult.length() < 1 || TortillaQuery.checkChallenge(queryResult.substring(0,
                queryResult.indexOf('\n')))) {
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
        player.setName(name.trim());
        player.setPing(ping);
        return player;
    }
}
