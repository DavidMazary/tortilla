package tortilla.nexuiz;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * Helper functions for Player class.
 * @author dmaz
 */
public class PlayerUtils {

    private static final char fontTable[] = {
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
    private static final String colorTable[] = {
        "black", "red", "lime", "yellow", "blue", "cyan", "magenta", "white", "DimGray", "gray"};
    private static final String FONT_CLOSE = "</font>";
    private static boolean firstTag;
    
    /**
     * Converts Nexuiz color codes to HTML font color tags.
     * @param newName Nexuiz-encoded player name.
     * @return HTML-encoded player name.
     */
    protected static String nexuizColorsToHtml(String newName) {
        String htmlName = newName;
        firstTag = true;
        for (int i = 0; i < htmlName.length(); i++) {
            if ((htmlName.charAt(i)) == '^' && (i < htmlName.length() - 1)) {
                char nextChar = htmlName.charAt(i + 1);
                if (Character.isDigit(nextChar)) {
                    htmlName = htmlName.replaceFirst("\\^\\p{Digit}", colorToTag(
                            colorTable[Character.digit(nextChar, 10)]));
                    i++;
                } else if (nextChar == 'x') {
                    String colorCode = htmlName.substring(i + 2, i + 5);
                    if (Pattern.matches("\\p{XDigit}{3}", colorCode)) {
                        htmlName = htmlName.replaceFirst("\\^x\\p{XDigit}{3}", colorToTag(
                                "#" + colorCode.charAt(0) + colorCode.charAt(0) + colorCode.charAt(1)
                                + colorCode.charAt(1) + colorCode.charAt(2) + colorCode.charAt(2)));
                        i += 4;
                    }
                }
            }
        }
        if (!firstTag) {
            htmlName += FONT_CLOSE;
        }
        return htmlName;
    }

    private static String colorToTag(String color) {
        String tag = "";
        if (!firstTag) {
            tag = FONT_CLOSE;
        } else {
            firstTag = false;
        }
        tag += "<font color=\"" + color + "\">";
        return tag;
    }

    protected static String decolorName(String name) {
        return name.replaceAll("\\^(\\p{Digit}|x\\p{XDigit}{3})", "");
    }

    /**
     * Translate special characters into displayable text in player names.
     * Converts the given name string to bytes,
     * which correspond to positions in the font table.
     *
     * @param name
     * @return Player's name converted to ascii text
     */
    protected static String sanitizeName(String name) {
        String asciiName;
        try {
            byte nameBytes[] = name.getBytes("ISO-8859-1");
            StringBuilder sb = new StringBuilder("");

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
}
