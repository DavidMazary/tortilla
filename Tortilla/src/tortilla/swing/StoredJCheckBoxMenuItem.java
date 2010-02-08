package tortilla.swing;

import java.awt.Component;
import org.jdesktop.application.SessionStorage;

/**
 * JCheckBoxMenuItem which is able to have its state saved automatically.
 * @author dmaz
 */
@SuppressWarnings("serial")
public class StoredJCheckBoxMenuItem extends javax.swing.JCheckBoxMenuItem implements SessionStorage.Property {

    @Override
    public Object getSessionState(final Component comp) {
        return getState();
    }

    @Override
    public void setSessionState(final Component comp, final Object state) {
        this.setState((Boolean) state);
    }
}
