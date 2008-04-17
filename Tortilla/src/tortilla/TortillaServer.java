package tortilla;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A Nexuiz server object.
 * For each server show ping, hostname, mapname, playercount, ip,
 * and whether server is favorite.
 * @author David
 */
public class TortillaServer {

    private String ip;
    private String hostname;
    private String map;
    private int maxplayers;
    private int players;
    private int ping;
    private boolean favorite;
//    private boolean timeout;
    private ConcurrentMap < String, TortillaPlayer > playerList =
        new ConcurrentHashMap < String, TortillaPlayer >();

    /**
     * Creates a new Tortilla Server with the following parameters.
     * @param aip   IP address of server.
     * @param ahostname Hostname of server.
     * @param amap Current map running on server.
     * @param amaxplayers Maximum player limit on server.
     * @param aplayers Current player count on server.
     * @param aping Ping from client to server.
     */
    public TortillaServer(String aip, String ahostname, String amap,
            int amaxplayers, int aplayers, int aping)
    {
        ip = aip;
        hostname = ahostname;
        map = amap;
        maxplayers = amaxplayers;
        players = aplayers;
        ping = aping;
    }
    
    /**
     * Constructor taking in no parameters, used to set up a timed-out server.
     */
    public TortillaServer()
    {
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
     * Adds player to playerList.
     * @param name IP address of player to add
     * @param newPlayer TortillaPlayer to add
     */
    public void addPlayer(String name, TortillaPlayer newPlayer)
    {
        playerList.put(name, newPlayer);
    }
    
    /**
     * Returns the playerList.
     * @return ConcurrentHashMap of playerList.
     */
    public Map < String, TortillaPlayer > getPlayerList()
    {
        return playerList;
    }
    
    /**
     * Whether server is full or not.
     * @return Status of server fullness.
     */
    public boolean isFull()
    {
        return (getPlayers() == getMaxplayers());
    }
    
    /**
     * Whether server is empty or not.
     * @return Status of server emptiness.
     */
    public boolean isEmpty()
    {
        return (getPlayers() == 0);
    }
    
    /**
     * Whether server is favorite or not.
     * @return True is server is favorite.
     */
    public boolean isFav()
    {
        return isFavorite();
    }
    
    /**
     * Set this server's favorite status.
     * @param favorite True if server is to be favorite.
     */
    public void setFavorite(boolean favorite) 
    {
        this.favorite = favorite;
    }
    
    /**
     * Hostname of this server.
     * @return Hostname.
     */
    public String getHostname()
    {
        return hostname;
    }
    
    /**
     * Map currently running on this server.
     * @return Map name.
     */
    public String getMap()
    {
        return map;
    }
    
    /**
     * Server's maximum number of players.
     * @return maxplayers
     */
    public int getMaxplayers()
    {
        return maxplayers;
    }
    
    /**
     * Current number of players on server.
     * @return Number of current players on server.
     */
    public int getPlayers()
    {
        return players;
    }
    
    /**
     * Current ping from client to server.
     * @return Ping in ms
     */
    public int getPing()
    {
        return ping;
    }

    /**
     * Update hostname of server.
     * @param newHostName String to replace hostname with.
     */
    public void setHostname(String newHostName)
    {
        hostname = newHostName;
    }

    /**
     * Update current map on server.
     * @param newMap String of new map name.
     */
    public void setMap(String newMap)
    {
        map = newMap;
    }

    /**
     * Update current number of players.
     * @param newPlayers New player count on server.
     */
    public void setPlayers(int newPlayers)
    {
        players = newPlayers;
    }

    /**
     * Update ping to server.
     * @param newPing New ping from client to server.
     */
    public void setPing(int newPing)
    {
        ping = newPing;
    }
    
    /**
     * IP address of this server.
     * @return IP address in String form.
     */
    public String getIp() 
    {
        return ip;
    }

    /**
     * New IP address of server.
     * @param ip address of server.
     */
    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    /**
     * Update maximum number of players.
     * @param maxplayers New maximum player limit for server.
     */
    public void setMaxplayers(int maxplayers) 
    {
        this.maxplayers = maxplayers;
    }

    /**
     * Whether server is favorite or not.
     * @return True is server is favorite.
     */
    public boolean isFavorite() 
    {
        return favorite;
    }

//    /**
//     * This is used if a server is timing out.
//     * @return True if server has timed out.
//     */
//    public boolean isTimeout() 
//    {
//        return timeout;
//    }
}
