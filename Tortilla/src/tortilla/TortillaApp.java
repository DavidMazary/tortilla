/*
 * TortillaApp.java
 * TODO Application icon
 */
package tortilla;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class TortillaApp extends SingleFrameApplication {

    private transient TortillaView tortillaView;
    private static final String SESSION_FILE = "session.xml";

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        tortillaView = new TortillaView(this);
        try {
            getContext().getSessionStorage().restore(tortillaView.getComponent(), SESSION_FILE);
            getContext().getSessionStorage().restore(tortillaView.getPopupMenu(), "menu." + SESSION_FILE);
            tortillaView.restoreFilterBar();
        } catch (IOException ex) {
            Logger.getLogger(TortillaApp.class.getName()).log(Level.WARNING, "Couldn't restore session", ex);
        }
        show(tortillaView);
    }

    @Override
    protected void shutdown() {
        try {
            getContext().getSessionStorage().save(tortillaView.getComponent(), SESSION_FILE);
            getContext().getSessionStorage().save(tortillaView.getPopupMenu(), "menu." + SESSION_FILE);
        } catch (IOException ex) {
            Logger.getLogger(TortillaApp.class.getName()).log(Level.WARNING, "Couldn't save session", ex);
        }
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TortillaApp
     */
    public static TortillaApp getApplication() {
        return Application.getInstance(TortillaApp.class);
    }

    /**
     * Main method launching the application.
     * @param args 
     */
    public static void main(final String[] args) {
        launch(TortillaApp.class, args);
    }
}
