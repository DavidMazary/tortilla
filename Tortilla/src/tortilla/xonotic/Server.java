package tortilla.xonotic;

import java.util.List;

/**
 * A xonotic server object.
 * For each server show ping, hostname, mapname, playercount, ip.
 * Later, this class will also store if a server is a favorite or is timed-out.
 * @author dmaz
 */
public class Server {

    private String ip;
    private String hostname;
    private String map;
    private String game;
    private String gameVersion;
    private String modname;
    private String gameType;
    private int maxplayers;
    private int players;
    private int ping;
    private int botCount;
    private List<Player> playerList;
    private boolean favorite = false;

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
    public void setHostname(final String newHostName) {
        hostname = newHostName;
    }

    /**
     * Update current map on server.
     * @param newMap String of new map name.
     */
    public void setMap(final String newMap) {
        map = newMap;
    }

    /**
     * Update current number of players.
     * @param newPlayers New player count on server.
     */
    public void setPlayerCount(final String newPlayers) {
        players = Integer.parseInt(newPlayers);
    }

        /**
     * Update current number of players.
     * @param newPlayers New player count on server.
     */
    public void setPlayerCount(final int newPlayers) {
        players = newPlayers;
    }

    /**
     * Update ping to server.
     * @param newPing New ping from client to server.
     */
    public void setPing(final int newPing) {
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
    public void setIp(final String ip) {
        this.ip = ip;
    }

    /**
     * Update maximum number of players.
     * @param maxplayers New maximum player limit for server.
     */
    public void setMaxPlayers(final String maxplayers) {
        this.maxplayers = Integer.parseInt(maxplayers);
    }

    /**
     * Update maximum number of players.
     * @param maxplayers New maximum player limit for server.
     */
    public void setMaxPlayers(final int maxplayers) {
        this.maxplayers = maxplayers;
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
    public void setGame(final String game) {
        this.game = game;
    }

    /**
     * 
     * @return
     */
    public String getGameVersion() {
        return gameVersion;
    }

    /**
     * 
     * @param gameVersion
     */
    public void setGameVersion(final String gameVersion) {
        this.gameVersion = gameVersion;
    }

    /**
     * 
     * @return
     */
    public int getBotCount() {
        return botCount;
    }

    /**
     * Store bot_number used on server.
     * Bot count is deducted from player count.
     * @param botCount Number of bots running on a server.
     */
    public void setBotCount(final int botCount) {
        this.botCount = botCount;
        this.players -= this.botCount;
    }

    /**
     * 
     * @return
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Assigns an ArrayList of TortillaPlayers to this server.
     * @param playerList ArrayList of TortillaPlayers to assign to this server.
     */
    public void setPlayerList(final List<Player> playerList) {
        this.playerList = playerList;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(final String modname) {
        this.modname = modname;
    }

    /**
     * @return the favorite
     */
    public boolean isFavorite() {
        return favorite;
    }

    /**
     * @param favorite the favorite to set
     */
    public void setFavorite(final boolean favorite) {
        this.favorite = favorite;
    }

    /**
     * Handles info from qcstatus string.
     * TODO: Handle more than gametype.
     * @param string of qcstatus reply
     */
    public void setQcstatus(final String string) {
        final String[] qcstatus = string.split(":");
        this.gameType = qcstatus[0];
        if (gameType.equals("tortilla")) {
            gameType = "dm";
        }
    }

    public String getType() {
        return this.gameType;
    }

    public void setType(final String type) {
        this.gameType = type;
    }
}
