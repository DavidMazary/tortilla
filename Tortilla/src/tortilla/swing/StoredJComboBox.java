package tortilla.swing;

import java.awt.Component;
import org.jdesktop.application.SessionStorage;

/**
 * JComboBox which is able to have its state saved automatically.
 * @author dmaz
 */
@SuppressWarnings("serial")
public class StoredJComboBox extends javax.swing.JComboBox implements SessionStorage.Property {

    @Override
    public Object getSessionState(final Component comp) {
        return getSelectedIndex();
    }

    @Override
    public void setSessionState(final Component comp, final Object state) {
        this.setSelectedIndex((Integer) state);
    }
}
