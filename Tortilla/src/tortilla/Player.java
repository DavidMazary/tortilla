package tortilla;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * A Nexuiz player object.
 * For each player in a server, show name, ping, score,
 * and whether player is bot or whether player is spectating.
 * Also converts special characters into displayable text in player names.
 * @author dmaz
 */
public class Player {

    private String name;
    private String coloredName;
    private int score;
    private int ping;

    /**
     * Translate special characters into displayable text in player names.
     * Converts the given name string to bytes,
     * which correspond to positions in the font table.
     *
     * @param name 
     * @return Player's name converted to ascii text
     */
    protected String translateName(String name) {
        String asciiName;
        try {
            byte nameBytes[] = name.getBytes("ISO-8859-1");
            StringBuilder sb = new StringBuilder("");
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

            // Character will correspond with unsigned byte.
            for (byte b : nameBytes) {
                sb.append(fontTable[b & 0xff]);
            }
            asciiName = sb.toString();
        } catch (UnsupportedEncodingException e) {
            asciiName = name;
        }

        return asciiName;
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
        this.setColoredName(colorName(newName));
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
    public void setScore(String score) {
        this.score = Integer.parseInt(score);
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
    public void setPing(String ping) {
        this.ping = Integer.parseInt(ping);
    }

    void setPing(int i) {
        this.ping = i;
    }

    void setScore(int i) {
        this.score = i;
    }

    private String colorName(String newName) {
        String htmlName = newName;
        boolean firstTag = true;
        for (int i = 0; i < htmlName.length(); i++) {
            if ((htmlName.charAt(i)) == '^' && (i != htmlName.length())) {
                char nextChar = htmlName.charAt(i + 1);
                String color;
                if (Character.isDigit(nextChar)) {
                    switch (nextChar) {
                        case '0':
                            color = "black";
                            break;
                        case '1':
                            color = "red";
                            break;
                        case '2':
                            color = "lime";
                            break;
                        case '3':
                            color = "yellow";
                            break;
                        case '4':
                            color = "blue";
                            break;
                        case '5':
                            color = "cyan";
                            break;
                        case '6':
                            color = "magenta";
                            break;
                        case '7':
                            color = "white";
                            break;
                        case '8':
                            color = "DimGray";
                            break;
                        default:
                            color = "gray";
                    }
                    String tag;
                    if (firstTag) {
                        tag = "<font color=\"" + color  + "\">";
                        firstTag = false;
                    } else {
                        tag = "</font><font color=\"" + color  + "\">";
                    }
                    // TODO: Insert HTML font tag into name
                    // remove old code, insert tag
                    i++;
                } else if (nextChar == 'x') {
                    // TODO: Use pattern matching to check if next 3 chars are hex
                    String colorCode = htmlName.substring(i + 2, i + 4);
                    if (Pattern.matches("\\p{XDigit}", colorCode)) {

                    }
                }
            }
        }
        if (!firstTag) {
            htmlName += "</font>";
        }
        return htmlName;
    }

    /**
     * @return the coloredName
     */
    public String getColoredName() {
        return coloredName;
    }

    /**
     * @param coloredName the coloredName to set
     */
    public void setColoredName(String coloredName) {
        this.coloredName = coloredName;
    }
}
