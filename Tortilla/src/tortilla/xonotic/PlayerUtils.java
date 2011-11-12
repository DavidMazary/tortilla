package tortilla.xonotic;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Helper functions for Player class.
 * @author dmaz
 */
public final class PlayerUtils {

    private static final char FONT_TABLE[] = {
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
    private static final String COLOR_TABLE[] = {
        "black", "red", "lime", "yellow", "blue", "cyan", "magenta", "white", "DimGray", "gray"};
    private static final String FONT_CLOSE = "</font>";

    /**
     * Singleton
     */
    private PlayerUtils() {
    }

    private static class SingletonHolder {

        private static final PlayerUtils INSTANCE = new PlayerUtils();
    }

    public static PlayerUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Converts xonotic color codes to HTML font color tags.
     * @param newName xonotic-encoded player name.
     * @return HTML-encoded player name.
     */
    protected static String xonoticColorsToHtml(final String newName) {
        String htmlName = newName;
        boolean closeTag = false;  // Indicates the need to add a close font tag
        final StringBuilder tag = new StringBuilder();
        for (int i = 0; i < htmlName.length(); i++) {
            if ((htmlName.charAt(i)) == '^' && (i < htmlName.length() - 1)) {
                final char nextChar = htmlName.charAt(i + 1);
                if (Character.isDigit(nextChar)) {
                    tag.delete(0, tag.length());
                    if (closeTag) {
                        tag.append(FONT_CLOSE);
                    } else {
                        closeTag = true;
                    }
                    tag.append("<font color=\"").append(COLOR_TABLE[Character.digit(nextChar, 10)]).append("\">");
                    htmlName = htmlName.replaceFirst("\\^\\p{Digit}", tag.toString());
                    i++;
                } else if (nextChar == 'x') {
                    final String colorCode = htmlName.substring(i + 2, i + 5);
                    if (Pattern.matches("\\p{XDigit}{3}", colorCode)) {
                        tag.delete(0, tag.length());
                        if (closeTag) {
                            tag.append(FONT_CLOSE);
                        } else {
                            closeTag = true;
                        }
                        tag.append("<font color=\"").append("#").append(colorCode.charAt(0)).append(colorCode.charAt(0)).append(colorCode.charAt(1)).append(colorCode.charAt(1)).append(colorCode.charAt(2)).append(colorCode.charAt(2)).append("\">");
                        htmlName = htmlName.replaceFirst("\\^x\\p{XDigit}{3}", tag.toString());
                        i += 4;
                    }
                }
            }
        }
        if (closeTag) {
            htmlName += FONT_CLOSE;
        }
        return htmlName;
    }

    protected static String decolorName(final String name) {
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
    protected static String sanitizeName(final String name) {
        final StringBuilder builder = new StringBuilder("");
        for (char c : name.toCharArray()) {
            if (c >= '\ue000' && c <= '\ue0ff') {
                c = FONT_TABLE[c - '\ue000'];
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
