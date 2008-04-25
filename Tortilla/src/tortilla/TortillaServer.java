package tortilla;

/**
 * A Nexuiz server object.
 * For each server show ping, hostname, mapname, playercount, ip.
 * Later, this class will also store if a server is a favorite or is timed-out.
 * @author dmaz
 */
public class TortillaServer implements java.io.Serializable {

    private String ip;
    private String hostname;
    private String map;
    private String game;
    private String gameVersion;
    private int maxplayers;
    private int players;
    private int ping;
//    private boolean favorite;
//    private boolean timeout;
    /**
     * Constructor taking in no parameters.
     */
    public TortillaServer() {
    /* Empty constructor */
    }

//    /**
//     * This is used if a server is timing out.
//     * @return True if server has timed out.
//     */
//    public boolean getTimeout()
//    {
//        return isTimeout();
//    }
    /**
     * Whether server is full or not.
     * @return Status of server fullness.
     */
    public boolean isFull() {
        return (getPlayerCount() == getMaxPlayers());
    }

    /**
     * Whether server is empty or not.
     * @return Status of server emptiness.
     */
    public boolean isEmpty() {
        return (getPlayerCount() == 0);
    }

//    /**
//     * Set this server's favorite status.
//     * @param favorite True if server is to be favorite.
//     */
//    public void setFavorite(boolean favorite) {
//        this.favorite = favorite;
//    }
    /**
     * Hostname of this server.
     * @return Hostname.
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Map currently running on this server.
     * @return Map name.
     */
    public String getMap() {
        return map;
    }

    /**
     * Server's maximum number of players.
     * @return maxplayers
     */
    public int getMaxPlayers() {
        return maxplayers;
    }

    /**
     * Current number of players on server.
     * @return Number of current players on server.
     */
    public int getPlayerCount() {
        return players;
    }

    /**
     * Current ping from client to server.
     * @return Ping in ms
     */
    public int getPing() {
        return ping;
    }

    /**
     * Update hostname of server.
     * @param newHostName String to replace hostname with.
     */
    public void setHostname(String newHostName) {
        hostname = newHostName;
    }

    /**
     * Update current map on server.
     * @param newMap String of new map name.
     */
    public void setMap(String newMap) {
        map = newMap;
    }

    /**
     * Update current number of players.
     * @param newPlayers New player count on server.
     */
    public void setPlayerCount(int newPlayers) {
        players = newPlayers;
    }

    /**
     * Update current number of players.
     * Takes in a String parameter.
     * @param newPlayers New player count on server.
     */
    public void setPlayerCount(String newPlayers) {
        players = Integer.parseInt(newPlayers);
    }

    /**
     * Update ping to server.
     * @param newPing New ping from client to server.
     */
    public void setPing(int newPing) {
        ping = newPing;
    }

    /**
     * IP address of this server.
     * @return IP address in String form.
     */
    public String getIp() {
        return ip;
    }

    /**
     * New IP address of server.
     * @param ip address of server.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Update maximum number of players.
     * @param maxplayers New maximum player limit for server.
     */
    public void setMaxPlayers(int maxplayers) {
        this.maxplayers = maxplayers;
    }

    /**
     * Update maximum number of players.
     * Takes in a string parameter.
     * @param maxplayers New maximum player limit for server.
     */
    public void setMaxPlayers(String maxplayers) {
        this.maxplayers = Integer.parseInt(maxplayers);
    }

    /**
     * Get the name of the game this server is running.
     * @return  Name of the game :)
     */
    public String getGame() {
        return game;
    }

    /**
     * 
     * @param game
     */
    public void setGame(String game) {
        this.game = game;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

//    /**
//     * Whether server is favorite or not.
//     * @return True is server is favorite.
//     */
//    public boolean isFavorite()
//    {
//        return favorite;
//    }

//    /**
//     * This is used if a server is timing out.
//     * @return True if server has timed out.
//     */
//    public boolean isTimeout()
//    {
//        return timeout;
//    }
}
