package tortilla;

import java.awt.Frame;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Launches the game.
 * @author dmaz
 */
public class GameLauncher {

    private boolean sdl;
    private String ip;
    private File game;
    private static final String CONNECT = " +connect ";

    /**
     * True if GameLauncher will be using the SDL game binary.
     * @return Whether GameLauncher is launching SDL.
     */
    public boolean isSdl() {
        return sdl;
    }

    /**
     * Takes in user's preference to use the SDL binary or not.
     * This preference will come from the checkbox in the options menu.
     * @param sdl True if using SDL
     */
    public void setSdl(boolean sdl) {
        this.sdl = sdl;
    }

    /**
     * Launch game, depending on OS and isSdl().
     */
    public void playGame() {
        String cmd;
        String osName = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osName.contains("Windows")) {
            if (isSdl()) {
                game = new File(userDir + "\\nexuiz-sdl.exe");
            } else {
                game = new File(userDir + "\\nexuiz.exe");
            }
            cmd = game.toString() + " -basedir " + userDir + CONNECT + getIp();
        } else if (osName.contains("Linux") || osName.contains("SunOS") ||
                osName.contains("FreeBSD")) {
            if (isSdl()) {
                game = new File(userDir + "/nexuiz-linux-sdl.sh");
            } else {
                game = new File(userDir + "/nexuiz-linux-glx.sh");
            }
            cmd = game.toString() + CONNECT + getIp();
        } else {
            JOptionPane.showMessageDialog(new Frame(),
                    "OS not supported.");
            cmd = "";
        }

        if (!cmd.isEmpty() && game.exists()) {
            try {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(cmd);
                Thread.sleep(5000);
                runtime.exit(0);
            } catch (Exception ex) {
                Logger.getLogger(GameLauncher.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(new Frame(),
                    "Tried to launch game at " + game.toString());
        }
    }

    /**
     * IP address being used by a GameLauncher.
     * @return String of IP address
     */
    public String getIp() {
        return ip;
    }

    /**
     * Assign IP address for GameLauncher to connect.
     * @param ip Address to connect.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}
