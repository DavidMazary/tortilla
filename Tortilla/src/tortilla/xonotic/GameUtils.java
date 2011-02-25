package tortilla.xonotic;

import java.awt.Desktop;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tortilla.TortillaView;

/**
 * Provides utilities which do not modify the view or the data.
 * @author dmaz
 */
public class GameUtils {

    /**
     * Singleton
     */
    private GameUtils() {
    }

    private static class SingletonHolder {

        private static final GameUtils INSTANCE = new GameUtils();
    }

    public static GameUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private static final String CONNECT_FLAG = " +connect ";
    private static final String OS_NAME = System.getProperty("os.name");
    private static final String USER_DIR = System.getProperty("user.dir");

    /**
     * Launches xonotic.
     * @param address to connect to
     * @param useSdl or not
     */
    public static void launchGame(final String address, final boolean useSdl) {
        String gamePath = null;
        String cmd = null;
        if (OS_NAME.contains("Windows")) {
            if (useSdl) {
                gamePath = "\\xonotic-sdl.exe";
            } else {
                gamePath = "\\xonotic.exe";
            }
            cmd = USER_DIR + gamePath + " -basedir " + USER_DIR + CONNECT_FLAG + address;
        } else if (OS_NAME.contains("Linux")) {
            if (useSdl) {
                gamePath = "/xonotic-linux-sdl.sh";
            } else {
                gamePath = "/xonotic-linux-glx.sh";
            }
            cmd = USER_DIR + gamePath + CONNECT_FLAG + address;
        } else if (OS_NAME.contains("Mac")) {
            if (useSdl) {
                gamePath = "/xonotic-SDL.app";
            } else {
                gamePath = "/xonotic.app";
            }
            cmd = USER_DIR + gamePath + CONNECT_FLAG + address;
        } else {
            JOptionPane.showMessageDialog(new Frame(), "OS not supported.");
            return;
        }

        if (cmd != null && (new File(USER_DIR + gamePath)).exists()) {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception ex) {
                Logger.getLogger(TortillaView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (gamePath != null) {
            JOptionPane.showMessageDialog(new Frame(), "Tried to launch game at " + cmd);
        }
    }

    /**
     * Reads in config.cfg for favorite servers.
     * @return List of favorite server addresses
     */
    public static List<String> loadFavorites() {
        List<String> favoritesList = null;
        try {
            String configPath;
            if (OS_NAME.contains("Windows")) {
                configPath = System.getProperty("user.dir") + "\\data\\config.cfg";
            } else {
                configPath = System.getProperty("user.home") + "/.xonotic/data/config.cfg";
            }
            Scanner scanner = new Scanner(new File(configPath));
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("net_slist_favorites")) {
                    scanner = new Scanner(line.replaceAll("[\"]", ""));
                    scanner.next();
                    favoritesList = new ArrayList<String>();
                    while (scanner.hasNext()) {
                        favoritesList.add(scanner.next());
                    }
                    scanner.close();
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TortillaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return favoritesList;
    }

    public void addFavorite(String address) {
        final String operatingSystem = System.getProperty("os.name");

        final List<String> configText = new ArrayList<String>();
        File configFile;
        try {
            if (operatingSystem.contains("Windows")) {
                configFile = new File(System.getProperty("user.dir") + "\\data\\config.cfg");
            } else {
                configFile = new File(System.getProperty("user.home") + "/.xonotic/data/config.cfg");
            }
            final Scanner scanner = new Scanner(configFile);
            while (scanner.hasNextLine()) {
                configText.add(scanner.nextLine());
            }
            boolean favLineFound = false;
            for (String line : configText) {
                if (line.contains("net_slist_favorites")) {
                    favLineFound = true;
                    if (line.contains(address)) {
                        if (JOptionPane.showConfirmDialog(null, "Remove server from favorites?") == JOptionPane.YES_OPTION) {
                            configText.remove(line);
                            configText.add(line.replaceAll(address, ""));
                        }
                        break;
                    } else {
                        configText.remove(line);
                        configText.add(line.substring(0, line.length() - 1) + " " + address + "\"");
                        break;
                    }
                }
            }
            if (!favLineFound) {
                configText.add("net_slist_favorites \"" + address + "\"");
            }
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(configFile));
                for (String line : configText) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Could not write to config file");
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Could not write to config file");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new Frame(), "Could not find config file");
        }
    }

    /**
     * Connects to help page in default browser.
     */
    public static void launchHelpPage() {
        launchPage("http://code.google.com/p/tortilla/wiki/UsingTortilla");
    }

    /**
     * Connects to home page in default browser.
     */
    public static void launchHomePage() {
        launchPage("http://code.google.com/p/tortilla/");
    }

    /**
     * Connects to URI in default browser.
     * @param URI to browse to
     */
    private static void launchPage(final String URI) {
        if (Desktop.isDesktopSupported()) {
            final Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(URI));
                } catch (IOException ex) {
                    Logger.getLogger(GameUtils.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(GameUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
