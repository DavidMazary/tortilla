package tortilla;

import java.awt.Frame;
import java.io.File;
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
                cmd = game.toString() + " -basedir " + userDir + CONNECT +
                        getIp();
            } else {
                game = new File(userDir + "\\nexuiz.exe");
                cmd = game.toString() + " -basedir " + userDir + CONNECT +
                        getIp();
            }
        } else if (osName.contains("Linux") || osName.contains("SunOS") ||
                osName.contains("FreeBSD")) {
            if (isSdl()) {
                game = new File(userDir + "/nexuiz-linux-sdl.sh");
                cmd = game.toString() + CONNECT + getIp();
            } else {
                game = new File(userDir + "/nexuiz-linux-glx.sh");
                cmd = game.toString() + CONNECT + getIp();
            }
        } else {
            cmd = "touch ~/wtf";
        }

        if (game.exists()) {
            try {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec(cmd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(new Frame(),
                    "Game not found.\nTortilla must be within the Nexuiz folder");
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
