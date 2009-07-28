/*
 * TortillaView.java
 */
package tortilla;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SessionStorage;
import org.jdesktop.application.Task;

/**
 * The application's main frame.
 */
public class TortillaView extends FrameView {

    /**
     * Create a new TortillaView using given SingleFrameApplication.
     * @param app Application calling TortillaView
     */
    public TortillaView(SingleFrameApplication app) {
        super(app);

        initComponents();
        sortOrder.add(new SortKey(0, SortOrder.ASCENDING));
        refresh().execute();
    }

    /**
     * Display about box.
     */
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
            aboutBox = new TortillaAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        TortillaApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        serverTable = new javax.swing.JTable(tableModel) {

            public String getToolTipText(MouseEvent e) {
                int nameColumn = ServerTableModel.HOSTNAME;
                int selectedRow = rowAtPoint(e.getPoint());
                StringBuilder playerList = new StringBuilder("");
                if (!getModel().getColumnName(nameColumn).equals("Server")) {
                    for (int i = 0; i < tableModel.getColumnCount(); i++) {
                        if (tableModel.getColumnName(i).equals("Server")) {
                            nameColumn = i;
                            break;
                        }
                    }
                }
                String selectedServer = this.getModel().getValueAt(convertRowIndexToModel(selectedRow), nameColumn).toString();
                for (Server server : tableModel.dataVector) {
                    if (server.getHostname().equals(selectedServer)) {
                        if (server.getPlayerCount() > 0) {
                            playerList.append("<html><b>" + server.getHostname() + "</b><br/>");
                            for (Player player : server.getPlayerList()) {
                                playerList.append(player.getColoredName() + "<br/>");
                            }
                            playerList.append("</html>");
                        } else {
                            return null;
                        }
                        break;
                    }
                }
                return playerList.toString();
            }

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (!component.getBackground().equals(getSelectionBackground())) {
                    int playerCount = (Integer) getValueAt(row, ServerTableModel.PLAYERS);
                    if (playerCount > 0) {
                        if (playerCount == (Integer) getValueAt(row, ServerTableModel.MAX)) {
                            component.setForeground(new Color(164,0,0));
                        } else {
                            component.setForeground(Color.black);
                        }
                    } else {
                        component.setForeground(Color.gray);
                    }
                }
                return component;
            }
        };
        filterPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        favoriteServersToggleButton = new StoredJToggleButton();
        showHighPingToggle = new StoredJToggleButton();
        showFullToggle = new StoredJToggleButton();
        showEmptyToggle = new StoredJToggleButton();
        gameTypeComboBox = new StoredJComboBox();
        toolBar = new javax.swing.JToolBar();
        refreshButton = new javax.swing.JButton();
        separator = new javax.swing.JToolBar.Separator();
        addButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        controlButton = new javax.swing.JToggleButton();
        controlMenu = new javax.swing.JPopupMenu();
        filterBarCheckBox = new StoredJCheckBoxMenuItem();
        sdlCheckBox = new StoredJCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        aboutMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        tableScrollPane.setDoubleBuffered(true);
        tableScrollPane.setName("tableScrollPane"); // NOI18N

        serverTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    connect();
                }
            }
        });
        serverTable.setAutoCreateRowSorter(true);
        serverTable.setName("serverTable"); // NOI18N
        serverTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableScrollPane.setViewportView(serverTable);
        serverTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        filterPanel.setName("filterPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaView.class, this);
        favoriteServersToggleButton.setAction(actionMap.get("filter")); // NOI18N
        favoriteServersToggleButton.setIcon(resourceMap.getIcon("favoriteServersToggleButton.icon")); // NOI18N
        favoriteServersToggleButton.setText(resourceMap.getString("favoriteServersToggleButton.text")); // NOI18N
        favoriteServersToggleButton.setToolTipText(resourceMap.getString("favoriteServersToggleButton.toolTipText")); // NOI18N
        favoriteServersToggleButton.setBorderPainted(false);
        favoriteServersToggleButton.setMaximumSize(new java.awt.Dimension(24, 24));
        favoriteServersToggleButton.setMinimumSize(new java.awt.Dimension(24, 24));
        favoriteServersToggleButton.setName("favoriteServersToggleButton"); // NOI18N
        favoriteServersToggleButton.setPreferredSize(new java.awt.Dimension(24, 24));

        showHighPingToggle.setAction(actionMap.get("filter")); // NOI18N
        showHighPingToggle.setText(resourceMap.getString("showHighPingToggle.text")); // NOI18N
        showHighPingToggle.setToolTipText(resourceMap.getString("showHighPingToggle.toolTipText")); // NOI18N
        showHighPingToggle.setBorderPainted(false);
        showHighPingToggle.setName("showHighPingToggle"); // NOI18N

        showFullToggle.setAction(actionMap.get("filter")); // NOI18N
        showFullToggle.setText(resourceMap.getString("showFullToggle.text")); // NOI18N
        showFullToggle.setToolTipText(resourceMap.getString("showFullToggle.toolTipText")); // NOI18N
        showFullToggle.setBorderPainted(false);
        showFullToggle.setName("showFullToggle"); // NOI18N

        showEmptyToggle.setAction(actionMap.get("filter")); // NOI18N
        showEmptyToggle.setText(resourceMap.getString("showEmptyToggle.text")); // NOI18N
        showEmptyToggle.setToolTipText(resourceMap.getString("showEmptyToggle.toolTipText")); // NOI18N
        showEmptyToggle.setBorderPainted(false);
        showEmptyToggle.setName("showEmptyToggle"); // NOI18N

        gameTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "dm", "tdm", "dom", "ctf", "rune", "lms", "arena", "kh", "ons", "as", "rc", "nexball", "cts" }));
        gameTypeComboBox.setToolTipText(resourceMap.getString("gameTypeComboBox.toolTipText")); // NOI18N
        gameTypeComboBox.setName("gameTypeComboBox"); // NOI18N
        gameTypeComboBox.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                gameTypeComboBoxMouseWheelMoved(evt);
            }
        });
        gameTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameTypeComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createSequentialGroup()
                .addComponent(gameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showEmptyToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showFullToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showHighPingToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(favoriteServersToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        filterPanelLayout.setVerticalGroup(
            filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(gameTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(showEmptyToggle)
                .addComponent(showFullToggle)
                .addComponent(showHighPingToggle))
            .addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(favoriteServersToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        toolBar.setFloatable(false);
        toolBar.setBorderPainted(false);
        toolBar.setName("toolBar"); // NOI18N
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));

        refreshButton.setAction(actionMap.get("refresh")); // NOI18N
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(refreshButton);

        separator.setName("separator"); // NOI18N
        toolBar.add(separator);

        addButton.setAction(actionMap.get("launchFavoriteServerDialog")); // NOI18N
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setName("addButton"); // NOI18N
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(addButton);

        connectButton.setAction(actionMap.get("connect")); // NOI18N
        connectButton.setFocusable(false);
        connectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectButton.setName("connectButton"); // NOI18N
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(connectButton);

        controlButton.setAction(actionMap.get("showControlPopup")); // NOI18N
        controlButton.setToolTipText(resourceMap.getString("controlButton.toolTipText")); // NOI18N
        controlButton.setFocusable(false);
        controlButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        controlButton.setName("controlButton"); // NOI18N
        controlButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(controlButton);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
        );

        controlMenu.setName("controlMenu"); // NOI18N
        controlMenu.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                controlMenuPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                controlMenuPopupMenuCanceled(evt);
            }
        });

        filterBarCheckBox.setAction(actionMap.get("showFilterPanel")); // NOI18N
        filterBarCheckBox.setSelected(true);
        filterBarCheckBox.setText(resourceMap.getString("filterBarCheckBox.text")); // NOI18N
        filterBarCheckBox.setName("filterBarCheckBox"); // NOI18N
        controlMenu.add(filterBarCheckBox);

        sdlCheckBox.setSelected(true);
        sdlCheckBox.setText(resourceMap.getString("sdlCheckBox.text")); // NOI18N
        sdlCheckBox.setName("sdlCheckBox"); // NOI18N
        controlMenu.add(sdlCheckBox);

        jSeparator1.setName("jSeparator1"); // NOI18N
        controlMenu.add(jSeparator1);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        controlMenu.add(aboutMenuItem);

        helpMenuItem.setAction(actionMap.get("launchHelpPage")); // NOI18N
        helpMenuItem.setText(resourceMap.getString("helpMenuItem.text")); // NOI18N
        helpMenuItem.setToolTipText(null);
        helpMenuItem.setName("helpMenuItem"); // NOI18N
        controlMenu.add(helpMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        controlMenu.add(jSeparator2);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        controlMenu.add(exitMenuItem);

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
    refreshTable();
}//GEN-LAST:event_searchTextFieldKeyReleased

private void gameTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameTypeComboBoxActionPerformed
    // Session restore sets combo box selection before data initialization.
    if (serverVector != null) {
        refreshTable();
    }
}//GEN-LAST:event_gameTypeComboBoxActionPerformed

    /**
     * Scrolls through the selected item in the combo box.
     * @param evt
     */
private void gameTypeComboBoxMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_gameTypeComboBoxMouseWheelMoved
    if (evt.getWheelRotation() < 0) {
        if (gameTypeComboBox.getSelectedIndex() > 0) {
            gameTypeComboBox.setSelectedIndex(gameTypeComboBox.getSelectedIndex() - 1);
        }
    } else if (gameTypeComboBox.getSelectedIndex() < gameTypeComboBox.getItemCount() - 1) {
        gameTypeComboBox.setSelectedIndex(gameTypeComboBox.getSelectedIndex() + 1);
    }
}//GEN-LAST:event_gameTypeComboBoxMouseWheelMoved

private void controlMenuPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_controlMenuPopupMenuCanceled
    controlButton.setSelected(false);
}//GEN-LAST:event_controlMenuPopupMenuCanceled

private void controlMenuPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_controlMenuPopupMenuWillBecomeInvisible
    controlButton.setSelected(false);
}//GEN-LAST:event_controlMenuPopupMenuWillBecomeInvisible

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JToggleButton controlButton;
    private javax.swing.JPopupMenu controlMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JToggleButton favoriteServersToggleButton;
    private javax.swing.JCheckBoxMenuItem filterBarCheckBox;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JComboBox gameTypeComboBox;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JCheckBoxMenuItem sdlCheckBox;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JToolBar.Separator separator;
    private javax.swing.JTable serverTable;
    private javax.swing.JToggleButton showEmptyToggle;
    private javax.swing.JToggleButton showFullToggle;
    private javax.swing.JToggleButton showHighPingToggle;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
    private JDialog aboutBox;
    private FavoriteServerDialog addPrivateServerBox;
    private GameLauncher launcher = new GameLauncher();
    private MasterQuery queryM = new MasterQuery();
    private ServerTableModel tableModel = new ServerTableModel();
    private ArrayList<String> serverList;
    private ArrayList<String> favoriteServerList;
    private Vector<Server> serverVector;
    private static final int HIGH_PING = 200;
    private static final String[] COLUMN_NAMES = {"Ping", "Server", "Players", "Max", "Map", "Type"};
    private String operatingSystem = null;
    private Vector<SortKey> sortOrder = new Vector<SortKey>(COLUMN_NAMES.length);
    private int serverCount;

    protected javax.swing.JPopupMenu getPopupMenu() {
        return controlMenu;
    }

    /**
     * JCheckBoxMenuItem which is able to have its state saved automatically.
     */
    @SuppressWarnings("serial")
    class StoredJCheckBoxMenuItem extends javax.swing.JCheckBoxMenuItem implements SessionStorage.Property {

        @Override
        public Object getSessionState(Component c) {
            return getState();
        }

        @Override
        public void setSessionState(Component c, Object state) {
            this.setState((Boolean) state);
            filterPanel.setVisible(filterBarCheckBox.getState());
        }
    }

    /**
     * JComboBox which is able to have its state saved automatically.
     */
    @SuppressWarnings("serial")
    class StoredJComboBox extends javax.swing.JComboBox implements SessionStorage.Property {

        @Override
        public Object getSessionState(Component c) {
            return getSelectedIndex();
        }

        @Override
        public void setSessionState(Component c, Object state) {
            this.setSelectedIndex((Integer) state);
        }
    }

    /**
     * JToggleButton which is able to have its state saved automatically.
     */
    @SuppressWarnings("serial")
    class StoredJToggleButton extends javax.swing.JToggleButton implements SessionStorage.Property {

        @Override
        public Object getSessionState(Component c) {
            return isSelected();
        }

        @Override
        public void setSessionState(Component c, Object state) {
            this.setSelected((Boolean) state);
        }
    }

    /**
     * Model of Nexuiz server data.
     * @author dmaz
     */
    class ServerTableModel extends AbstractTableModel {

        public static final int PING = 0;
        public static final int HOSTNAME = 1;
        public static final int PLAYERS = 2;
        public static final int MAX = 3;
        public static final int MAP = 4;
        public static final int TYPE = 5;
        private static final long serialVersionUID = 2187967572701857442L;
        protected Vector<Server> dataVector = null;

        public ServerTableModel() {
            dataVector = new Vector<Server>();
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                case PING:
                case PLAYERS:
                case MAX:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        @Override
        public Object getValueAt(int row, int column) {
            Server server = dataVector.get(row);
            switch (column) {
                case PING:
                    return server.getPing();
                case PLAYERS:
                    return server.getPlayerCount();
                case MAX:
                    return server.getMaxPlayers();
                case HOSTNAME:
                    return server.getHostname();
                case MAP:
                    return server.getMap();
                case TYPE:
                    return server.getType();
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            Server server = dataVector.get(row);
            switch (column) {
                case PING:
                    server.setPing((Integer) value);
                    break;
                case PLAYERS:
                    server.setPlayerCount((Integer) value);
                    break;
                case MAX:
                    server.setMaxPlayers((Integer) value);
                    break;
                case HOSTNAME:
                    server.setHostname((String) value);
                    break;
                case MAP:
                    server.setMap((String) value);
                    break;
                case TYPE:
                    server.setType((String) value);
                    break;
                default:
                    throw new IndexOutOfBoundsException();
            }
            fireTableCellUpdated(row, column);
        }

        @Override
        public int getRowCount() {
            return dataVector.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }
    }

    /**
     * Adds a row to the table if the application state allows it.
     */
    private synchronized void addRowToModel(Server server) {
        boolean canAddRow = true;
        // Filter by the preferences
        if ((!showEmptyToggle.isSelected() && (server.getPlayerCount() == 0)) ||
                (!showFullToggle.isSelected() && (server.getPlayerCount() == server.getMaxPlayers())) ||
                (!showHighPingToggle.isSelected() && (server.getPing() > HIGH_PING)) ||
                (favoriteServersToggleButton.isSelected() && !server.isFavorite()) ||
                ((gameTypeComboBox.getSelectedIndex() != 0) &&
                !((String) gameTypeComboBox.getSelectedItem()).equals(server.getType()))) {
            canAddRow = false;
        } else if (!searchTextField.getText().isEmpty()) {  // Filter by the search term
            String query = searchTextField.getText().toLowerCase();
            canAddRow = false;
            if (server.getHostname().toLowerCase().contains(query) ||
                    server.getMap().toLowerCase().contains(query) ||
                    server.getType().contains(query)) {
                canAddRow = true;
            } else if (server.getPlayerList() != null) {
                for (Player player : server.getPlayerList()) {
                    if (player.getName().toLowerCase().contains(query)) {
                        canAddRow = true;
                        break;
                    }
                }
            }
        }
        // Add row to preferences
        if (canAddRow) {
            tableModel.dataVector.add(server);
            tableModel.fireTableRowsInserted(tableModel.dataVector.size() - 1, tableModel.dataVector.size() - 1);
        }
        // TODO: Save and restore sortkeys
        serverTable.getRowSorter().setSortKeys(sortOrder);
    }

    /**
     * Deletes data from server table's model and updates rows.
     */
    private void clearTable() {
        if (!tableModel.dataVector.isEmpty()) {
            int dataSize = tableModel.dataVector.size();
            tableModel.dataVector.clear();
            tableModel.fireTableRowsDeleted(0, dataSize - 1);
        }
    }

    /**
     * Clears table, then re-evaluates which rows may be added.
     */
    private void refreshTable() {
        // TODO: Save and restore selected row
        clearTable();
        for (Server server : serverVector) {
            addRowToModel(server);
        }
    }

    /* ------- Actions ------- */
    /**
     * Gets list of servers from master server.
     * Queries servers and adds them to table.
     * @return
     */
    @Action
    public Task refresh() {
        return new RefreshTask(getApplication());
    }

    private class RefreshTask extends org.jdesktop.application.Task<Object, Void> {

        RefreshTask(org.jdesktop.application.Application app) {
            super(app);
            refreshButton.setEnabled(false);
        }

        @Override
        protected Object doInBackground() {
            loadFavoriteServers();
            serverList = queryM.getServerList();
            if (favoriteServerList != null) {
                for (String address : favoriteServerList) {
                    if (serverList.contains(address)) {
                        serverList.remove(address);
                    }
                }
                serverCount = serverList.size() + favoriteServerList.size();
            } else if (serverList != null) {
                serverCount = serverList.size();
            } else {
                serverCount = 0;
            }
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            if (serverCount > 0) {
                clearTable();
                serverVector = new Vector<Server>(serverCount);

                class ServerQueryRunner implements Runnable {

                    String address;
                    boolean favorite;

                    public ServerQueryRunner(String ip, boolean fav) {
                        address = ip;
                        favorite = fav;
                    }

                    @Override
                    public void run() {
                        Server server = new ServerQuery().getStatus(address);
                        if (server != null) {
                            server.setFavorite(favorite);
                            serverVector.add(server);
                            addRowToModel(server);
                        }
                    }
                }

                ExecutorService pool = Executors.newFixedThreadPool(serverCount);
                if (favoriteServerList != null) {
                    for (final String ip : favoriteServerList) {
                        pool.execute(new ServerQueryRunner(ip, true));
                    }
                }
                for (final String ip : serverList) {
                    pool.execute(new ServerQueryRunner(ip, false));
                }
                pool.shutdown();
            }
            refreshButton.setEnabled(true);
        }
    }

    /**
     * Stub.
     */
    @Action
    public void viewServer() {
    }

    /**
     * Calls GameLauncher on selected server.
     */
    @Action
    public void connect() {
        int selectedRow = serverTable.getSelectedRow();

        if (selectedRow != -1) {
            int nameColumn = ServerTableModel.HOSTNAME;
            if (!tableModel.getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    if (tableModel.getColumnName(i).equals("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            String selectedServer = tableModel.getValueAt(serverTable.convertRowIndexToModel(selectedRow), nameColumn).toString();
            String selectedIp = null;
            for (Server server : tableModel.dataVector) {
                if (server.getHostname().equals(selectedServer)) {
                    selectedIp = server.getIp();
                    break;
                }
            }

            launcher.setSdl(sdlCheckBox.getState());
            launcher.setIp(selectedIp);
            launcher.playGame();
        } else if (serverVector == null) {
            JOptionPane.showMessageDialog(new Frame(),
                    "Please update the server list");
        } else {
            JOptionPane.showMessageDialog(new Frame(),
                    "Please select a server");
        }
    }

    @Action
    public void launchFavoriteServerDialog() {
        int selectedRow = serverTable.getSelectedRow();
        String selectedIp = null;

        if (selectedRow != -1) {
            int nameColumn = ServerTableModel.HOSTNAME;
            if (!tableModel.getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    if (tableModel.getColumnName(i).equals("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            String selectedServer = tableModel.getValueAt(serverTable.convertRowIndexToModel(selectedRow), nameColumn).toString();
            for (Server server : tableModel.dataVector) {
                if (server.getHostname().equals(selectedServer)) {
                    selectedIp = server.getIp();
                    break;
                }
            }
        }
        if (addPrivateServerBox == null) {
            JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
            addPrivateServerBox = new FavoriteServerDialog(mainFrame, false);
            addPrivateServerBox.setLocationRelativeTo(this.getFrame());
            addPrivateServerBox.setTitle("Favorite");
        }
        if (selectedIp != null) {
            addPrivateServerBox.setAddressField(selectedIp);
        }
        TortillaApp.getApplication().show(addPrivateServerBox);
    }

    @Action
    public void filter() {

        refreshTable();
    }

    /**
     * Reads in config.cfg for favorite servers.
     */
    private void loadFavoriteServers() {
        if (operatingSystem == null) {
            operatingSystem = System.getProperty("os.name");
        }

        try {
            Scanner scanner;
            if (operatingSystem.contains("Linux")) {
                scanner = new Scanner(new File(System.getProperty("user.home") + "/.nexuiz/data/config.cfg"));
            } else {
                scanner = new Scanner(new File(System.getProperty("user.dir") + "\\data\\config.cfg"));
            }
            String line = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("net_slist_favorites")) {
                    scanner = new Scanner(line.replaceAll("[\"]", ""));
                    scanner.next();
                    favoriteServerList = new ArrayList<String>();
                    while (scanner.hasNext()) {
                        favoriteServerList.add(scanner.next());
                    }
                    break;
                }
            }

        } catch (FileNotFoundException ex) {
        }
    }

    @Action
    public void showControlPopup() {
        if (controlMenu.getWidth() == 0) {
            controlMenu.setVisible(false);
            controlMenu.show(controlButton, 0, 0); // Align menu to right of button on first click.
            controlMenu.show(controlButton, 0, 0);
            controlMenu.show(controlButton, controlButton.getWidth() - controlMenu.getWidth(), controlButton.getHeight());
            controlMenu.setVisible(true);
        } else {
            controlMenu.show(controlButton, controlButton.getWidth() - controlMenu.getWidth(), controlButton.getHeight());
        }
    }

    @Action
    public void launchHelpPage() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                URI uri = null;
                try {
                    uri = new URI("http://code.google.com/p/tortilla/wiki/UsingTortilla");
                    desktop.browse(uri);
                } catch (IOException ex) {
                    Logger.getLogger(TortillaAboutBox.class.getName()).
                            log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(TortillaAboutBox.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Action
    public void showFilterPanel() {
        filterPanel.setVisible(filterBarCheckBox.getState());
    }
}
