package tortilla;

/**
 * A Nexuiz player object.
 * For each player in a server, show colored name, ping, score, 
 * whether player is bot, whether player is spectating.
 * @author David
 */
public class TortillaPlayer {
    
    private String name;
//    private String plainName;
    private int score;
    private int ping;
    private boolean bot;
    private boolean spec;
    
    /**
     * Constructor taking in the following parameters.
     * @param newName String of name of player.
     * @param newScore int of score of player.
     * @param newPing int of ping of player.
     */
    public TortillaPlayer(String newName, int newScore, int newPing)
    {
        name = newName;
        score = newScore;
        ping = newPing;
    }
    
//    /**
//     * Set new uncolored name for player.
//     * @todo Make sure a number is after a caret before deleting it.
//     */
//    public void setPlainName()
//    {
//        char caret = '^';
//        StringBuffer nameBuffer = new StringBuffer(getName());
//        
//        for (int i = 0; i < nameBuffer.length(); i++)
//        {
//            if (nameBuffer.charAt(i) == caret)
//            {
//                nameBuffer.deleteCharAt(i);
//                nameBuffer.deleteCharAt(i);
//            }
//        }
//        
//        this.plainName = nameBuffer.toString();
//    }
    
    /**
     * Whether this player is a bot.
     * @return True if player is a bot.
     */
    public boolean isBot()
    {
        if (getPing() == 0)
        {
            bot = true;
        }
        else
        {
            bot = false;
        }
        return bot;
    }
    
    /**
     * Whether this player is spectating.
     * @return true if player is spectating.
     */
    public boolean isSpec()
    {
        if (getScore() == -666)
        {
            spec = true;
        }
        else
        {
            spec = false;
        }
        return spec;
    }
    
//    /**
//     * Remove colors from name of player.
//     * @return String of plain name.
//     */
//    public String getPlainName()
//    {
//        return plainName;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }
        

}
