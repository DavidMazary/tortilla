package tortilla;

import java.io.UnsupportedEncodingException;

/**
 * A Nexuiz player object.
 * For each player in a server, show name, ping, score,
 * and whether player is bot or whether player is spectating.
 * Also converts special characters into displayable text in player names.
 * @author dmaz
 */
public class TortillaPlayer implements java.io.Serializable {

    private String name;
    private int score;
    private int ping;
    private boolean bot;
    private boolean spec;

    /*
     * Constructor taking in no parameters
     */
    public TortillaPlayer() {
    // empty constructor
    }

    /**
     * Translate special characters into displayable text in player names.
     * Converts the given name string to bytes,
     * which correspond to positions in the font table.
     * The font table is taken from the Nexuiz source code.
     * I can't thank KadaverJack enough for this, saved me so much time. :)
     *
     * @return Player's name converted to normal text
     */
    protected String translateName(String name) {
        // Thanks to KadaverJack for showing me this lovely bit here :)
        char fontTable[] = {
            '\0', '#', '#', '#', '#', '.', '#', '#',
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

        try {
            byte nameBytes[] = name.getBytes("ISO-8859-1");
            StringBuffer sb = new StringBuffer("");

            for (int i = 0; i < nameBytes.length; i++) {
                sb.append(fontTable[nameBytes[i] + 128]);
            }

            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Whether this player is a bot.
     * @return True if player is a bot.
     */
    public boolean isBot() {
        if (getPing() == 0) {
            bot = true;
        } else {
            bot = false;
        }
        return bot;
    }

    /**
     * Whether this player is spectating.
     * @return true if player is spectating.
     */
    public boolean isSpec() {
        if (getScore() == -666) {
            spec = true;
        } else {
            spec = false;
        }
        return spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = translateName(newName);
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
