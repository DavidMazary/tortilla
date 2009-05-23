package tortilla;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A Nexuiz player object.
 * For each player in a server, show name, ping, score,
 * and whether player is bot or whether player is spectating.
 * Also converts special characters into displayable text in player names.
 * @author dmaz
 */
public class Player {

    private String name;
    private int score;
    private int ping;

    /**
     * Translate special characters into displayable text in player names.
     * Converts the given name string to bytes,
     * which correspond to positions in the font table.
     * The font table is taken from the Nexuiz source code.
     * I can't thank KadaverJack enough for this, saved me so much time. :)
     *
     * @param name 
     * @return Player's name converted to normal text
     */
    protected String translateName(String name) {
        // Thanks to KadaverJack for showing me this lovely bit here :)
        char fontTable[] = {
            ' ', '#', '#', '#', '#', '.', '#', '#',
            '#', 9, 10, '#', ' ', 13, '.', '.',
            '[', ']', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '.', '<', '=', '>',
            ' ', '!', '"', '#', '$', '%', '&', '\'',
            '(', ')', '*', '+', ',', '-', '.', '/',
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', ':', ';', '<', '=', '>', '?',
            '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '[', '\\', ']', '^', '_',
            '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '{', '|', '}', '~', '<',
            '<', '=', '>', '#', '#', '.', '#', '#',
            '#', '#', ' ', '#', ' ', '>', '.', '.',
            '[', ']', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '.', '<', '=', '>',
            ' ', '!', '"', '#', '$', '%', '&', '\'',
            '(', ')', '*', '+', ',', '-', '.', '/',
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', ':', ';', '<', '=', '>', '?',
            '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '[', '\\', ']', '^', '_',
            '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '{', '|', '}', '~', '<'
        };
        String playerName = "";

        try {
            byte nameBytes[] = name.getBytes("ISO-8859-1");
            StringBuilder sb = new StringBuilder("");

            // Character will correspond with unsigned byte.
            for (byte b : nameBytes) {
                sb.append(fontTable[b & 0xff]);
            }

            playerName = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playerName;
    }

    /**
     * Whether this player is a bot.
     * @return True if player is a bot.
     */
    public boolean isBot() {
        return getPing() == 0;
    }

    /**
     * Whether this player is spectating.
     * @return true if player is spectating.
     */
    public boolean isSpec() {
        return getScore() == -666;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param newName
     */
    public void setName(String newName) {
        this.name = translateName(newName);
    }

    /**
     * 
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * 
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 
     * @return
     */
    public int getPing() {
        return ping;
    }

    /**
     * 
     * @param ping
     */
    public void setPing(int ping) {
        this.ping = ping;
    }
}
