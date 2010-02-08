package tortilla.swing;

import java.awt.Component;
import org.jdesktop.application.SessionStorage;

/**
 * JToggleButton which is able to have its state saved automatically.
 * @author dmaz
 */
@SuppressWarnings("serial")
public class StoredJToggleButton extends javax.swing.JToggleButton implements SessionStorage.Property {

    @Override
    public Object getSessionState(final Component comp) {
        return isSelected();
    }

    @Override
    public void setSessionState(final Component comp, final Object state) {
        this.setSelected((Boolean) state);
    }
}
