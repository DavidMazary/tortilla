package tortilla;

import java.util.ArrayList;

/**
 * A Nexuiz server object.
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
    private int maxplayers;
    private int players;
    private int ping;
    private int botCount;
    private ArrayList<Player> playerList;
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
    public void setPlayerCount(String newPlayers) {
        players = Integer.parseInt(newPlayers);
    }

        /**
     * Update current number of players.
     * @param newPlayers New player count on server.
     */
    public void setPlayerCount(int newPlayers) {
        players = newPlayers;
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
    public void setMaxPlayers(String maxplayers) {
        this.maxplayers = Integer.parseInt(maxplayers);
    }

    /**
     * Update maximum number of players.
     * @param maxplayers New maximum player limit for server.
     */
    public void setMaxPlayers(int maxplayers) {
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
    public void setGame(String game) {
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
    public void setGameVersion(String gameVersion) {
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
    public void setBotCount(int botCount) {
        this.botCount = botCount;
        this.players -= this.botCount;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Assigns an ArrayList of TortillaPlayers to this server.
     * @param playerList ArrayList of TortillaPlayers to assign to this server.
     */
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public String getModname() {
        return modname;
    }

    public void setModname(String modname) {
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
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
