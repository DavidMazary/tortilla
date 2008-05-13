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

    private TortillaQuery query = new TortillaQuery();

    /**
     * Creates a TortillaServer containing all of the server info,
     * excluding player names, scores, and pings.
     * Response will look like this: <code>\gamename\Nexuiz\modname\data
     * \gameversion\20000\sv_maxclients\18\clients\6\bots\0\mapname\ctctf6
     * \hostname\Galt's Gulch - CTF - 2.4 - TX, USA\protocol\3\challenge\tortilla</code>
     *
     * @param ipStr     String of IP address of this server
     * @return  TortillaServer containing the info of this server.
     */
    public TortillaServer getInfo(String ipStr) {
        String ip[] = ipStr.split(":");
        int port = Integer.parseInt(ip[1]);
        String queryResult = null;

        for (int i = 0; i < 3; i++) {
            queryResult = query.getInfo(ip[0], port,
                    "xxxxgetinfo tortilla");
            if (queryResult != null) {
                break;
            }
        }

        TortillaServer tortillaServer = new TortillaServer();
        if (queryResult != null && queryResult.length() > 0 && !queryResult.equals("Request timed out.")) {
            tortillaServer = processServer(queryResult, ipStr);
        }
        return tortillaServer;
    }

    /**
     * Gets a tortillaserver including its list of tortillaplayers.
     *
     * @param ipStr     String of IP address of server
     * @return  TortillaServer including ArrayList of TortillaPlayers
     */
    public TortillaServer getStatus(String ipStr) {
        String ip[] = ipStr.split(":");
        int port = Integer.parseInt(ip[1]);
        TortillaServer tortillaServer = new TortillaServer();
        ArrayList<TortillaPlayer> tortillaPlayers =
                new ArrayList<TortillaPlayer>();
        String queryResult = null;        
        
        for (int i = 0; i < 3; i++) {
            queryResult = query.getInfo(ip[0], port,
                    "xxxxgetstatus tortilla");
            if (queryResult != null) {
                break;
            }
        }
        queryResult = queryResult.substring(queryResult.indexOf("\\"));
        queryResult = queryResult.replaceAll(
                "\\^([0-9a-wyzA-WYZ]|x[0-9a-fA-F]{6})", "");

        if (queryResult != null || queryResult.length() > 0) {
            String input;
            try {
                BufferedReader in = new BufferedReader(
                        new StringReader(queryResult));
                tortillaServer = processServer(in.readLine(), ipStr);
                while ((input = in.readLine()) != null) {
                    tortillaPlayers.add(processPlayer(input));
                }
                tortillaServer.setPlayerList(tortillaPlayers);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return tortillaServer;
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

    /**
     * Take the server info from the queryResult and build a TortillaServer.
     * @param queryResult String of the server info.
     * @return TortillaServer which is created.
     */
    protected TortillaServer processServer(String queryResult, String ipStr) {
        TortillaServer server = new TortillaServer();
        queryResult = queryResult.substring(queryResult.indexOf("\\"));
        System.out.println(queryResult);
        StringTokenizer tokens = new StringTokenizer(queryResult, "\\");
        if (tokens.nextToken().equals("gamename")) {
            server.setGame(tokens.nextToken());
        }
        if (tokens.nextToken().equals("modname")) {
            tokens.nextToken();
            if (tokens.nextToken().equals("gameversion")) {
                server.setGameVersion(tokens.nextToken());
            }
        }
        if (tokens.nextToken().equals("sv_maxclients")) {
            server.setMaxPlayers(tokens.nextToken());
        }
        if (tokens.nextToken().equals("clients")) {
            server.setPlayerCount(tokens.nextToken());
        }
        if (tokens.nextToken().equals("bots")) {
            server.setBots(tokens.nextToken());
            if (tokens.nextToken().equals("mapname")) {
                server.setMap(tokens.nextToken());
            }
        } else {
            server.setMap(tokens.nextToken());
        }
        if (tokens.nextToken().equals("hostname")) {
            server.setHostname(tokens.nextToken());
        }
        server.setPing(query.getPing());
        server.setIp(ipStr);
        return server;
    }
}
