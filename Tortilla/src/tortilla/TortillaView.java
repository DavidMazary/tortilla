/*
 * TortillaView.java
 */
package tortilla;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.Task;
import tortilla.xonotic.favorites.FavoritesDialog;
import tortilla.xonotic.GameUtils;
import tortilla.xonotic.Player;
import tortilla.xonotic.Server;
import tortilla.xonotic.tablemodel.ServerTableModel;
import tortilla.xonotic.query.MasterQuery;
import tortilla.xonotic.query.ServerQuery;
import tortilla.swing.StoredJCheckBoxMenuItem;
import tortilla.swing.StoredJComboBox;
import tortilla.swing.StoredJToggleButton;

/**
 * The application's main frame.
 */
public class TortillaView extends FrameView {

    /**
     * Create a new TortillaView using given SingleFrameApplication.
     * @param app Application calling TortillaView
     */
    public TortillaView(final SingleFrameApplication app) {
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
            final JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
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
        filterPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        favoritesToggleButton = new StoredJToggleButton();
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
            public void mouseClicked(final MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    connect();
                }
            }
        });
        serverTable.setAutoCreateRowSorter(true);
        serverTable.setModel(tableModel);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        serverTable.setGridColor(resourceMap.getColor("serverTable.gridColor")); // NOI18N
        serverTable.setName("serverTable"); // NOI18N
        serverTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        serverTable.setShowHorizontalLines(false);
        serverTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(serverTable);
        serverTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        filterPanel.setName("filterPanel"); // NOI18N

        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaView.class, this);
        favoritesToggleButton.setAction(actionMap.get("filter")); // NOI18N
        favoritesToggleButton.setIcon(resourceMap.getIcon("favoritesToggleButton.icon")); // NOI18N
        favoritesToggleButton.setText(resourceMap.getString("favoritesToggleButton.text")); // NOI18N
        favoritesToggleButton.setToolTipText(resourceMap.getString("favoritesToggleButton.toolTipText")); // NOI18N
        favoritesToggleButton.setBorderPainted(false);
        favoritesToggleButton.setMaximumSize(new java.awt.Dimension(24, 24));
        favoritesToggleButton.setMinimumSize(new java.awt.Dimension(24, 24));
        favoritesToggleButton.setName("favoritesToggleButton"); // NOI18N
        favoritesToggleButton.setPreferredSize(new java.awt.Dimension(24, 24));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(favoritesToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(favoritesToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        controlButton.setIcon(resourceMap.getIcon("controlButton.icon")); // NOI18N
        controlButton.setToolTipText(resourceMap.getString("controlButton.toolTipText")); // NOI18N
        controlButton.setFocusable(false);
        controlButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        controlButton.setName("controlButton"); // NOI18N
        controlButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(Box.createHorizontalGlue());
        controlButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                controlButtonMousePressed(evt);
            }
        });
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
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
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

        helpMenuItem.setText(resourceMap.getString("helpMenuItem.text")); // NOI18N
        helpMenuItem.setToolTipText(null);
        helpMenuItem.setName("helpMenuItem"); // NOI18N
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
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

private void controlButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controlButtonMousePressed
    if (controlMenu.getWidth() == 0) {
        controlMenu.show(controlButton, 0, 0); // Align menu to right of button on first click.
        controlMenu.setVisible(false);
        controlMenu.show(controlButton, controlButton.getWidth() - controlMenu.getWidth(), controlButton.getHeight());
    } else {
        controlMenu.show(controlButton, controlButton.getWidth() - controlMenu.getWidth(), controlButton.getHeight());
    }
}//GEN-LAST:event_controlButtonMousePressed

private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
    GameUtils.launchHelpPage();
}//GEN-LAST:event_helpMenuItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JToggleButton controlButton;
    private javax.swing.JPopupMenu controlMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JToggleButton favoritesToggleButton;
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
    private final javax.swing.JTable serverTable = new javax.swing.JTable() {

        public String getToolTipText(final MouseEvent evt) {
            int nameColumn = ServerTableModel.HOSTNAME;
            final int selectedRow = rowAtPoint(evt.getPoint());
            final StringBuilder playerList = new StringBuilder();
            if (!getModel().getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    if (tableModel.getColumnName(i).equals("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            final String selectedServer = this.getModel().getValueAt(convertRowIndexToModel(selectedRow), nameColumn).toString();
            for (Server server : tableModel.getDataVector()) {
                if (server.getHostname().equals(selectedServer)) {
                    if (server.getPlayerCount() > 0) {
                        playerList.append("<html><b>").append(server.getHostname()).append("</b><br/>");
                        for (Player player : server.getPlayerList()) {
                            playerList.append(player.getColoredName()).append("<br/>");
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

        public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {
            final Component component = super.prepareRenderer(renderer, row, column);
            if (component.getBackground() != null && !component.getBackground().equals(getSelectionBackground())) {
                final int playerCount = (Integer) getValueAt(row, ServerTableModel.PLAYERS);
                if (playerCount > 0) {
                    if (playerCount == (Integer) getValueAt(row, ServerTableModel.MAX)) {
                        component.setForeground(BRICK);
                    } else {
                        component.setForeground(Color.black);
                    }
                } else {
                    component.setForeground(Color.gray);
                }
                if ((row % 2) == 0) {
                    component.setBackground(LT_BLUE);
                } else {
                    component.setBackground(Color.white);
                }
            }
            return component;
        }
    };
    private javax.swing.JToggleButton showEmptyToggle;
    private javax.swing.JToggleButton showFullToggle;
    private javax.swing.JToggleButton showHighPingToggle;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
    private transient JDialog aboutBox;
    private transient FavoritesDialog favoriteDialog;
    private transient final ServerTableModel tableModel = new ServerTableModel();
    private transient List<String> serverList;
    private transient List<String> favoritesList;
    private transient List<Server> serverVector;
    private static final int HIGH_PING = 200;
    private transient final List<SortKey> sortOrder = new ArrayList<SortKey>(6);
    private transient int serverCount;
    private static final Color LT_BLUE = new Color(241, 245, 250);
    private static final Color BRICK = new Color(164, 0, 0);
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    protected javax.swing.JPopupMenu getPopupMenu() {
        return controlMenu;
    }

    protected void restoreFilterBar() {
        filterPanel.setVisible(filterBarCheckBox.getState());
    }

    /**
     * Adds a row to the table if the application state allows it.
     */
    private void addRowToModel(final Server server) {
        boolean canAddRow = true;
        // Filter by the preferences
        if ((!showEmptyToggle.isSelected() && (server.getPlayerCount() == 0))
                || (!showFullToggle.isSelected() && (server.getPlayerCount() == server.getMaxPlayers()))
                || (!showHighPingToggle.isSelected() && (server.getPing() > HIGH_PING))
                || (favoritesToggleButton.isSelected() && !server.isFavorite())
                || ((gameTypeComboBox.getSelectedIndex() != 0)
                && !((String) gameTypeComboBox.getSelectedItem()).equals(server.getType()))) {
            canAddRow = false;
        } else if (!searchTextField.getText().isEmpty()) {  // Filter by the search term
            final String query = searchTextField.getText().toLowerCase();
            canAddRow = false;
            if (server.getHostname().toLowerCase().contains(query)
                    || server.getMap().toLowerCase().contains(query)
                    || server.getType().contains(query)) {
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
            tableModel.getDataVector().add(server);
            tableModel.fireTableRowsInserted(tableModel.getDataVector().size() - 1, tableModel.getDataVector().size() - 1);
        }
        // TODO: Save and restore sortkeys
        serverTable.getRowSorter().setSortKeys(sortOrder);
    }

    /**
     * Clears table, then re-evaluates which rows may be added.
     */
    private void refreshTable() {
        // TODO: Save and restore selected row
        tableModel.clear();
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

        RefreshTask(final org.jdesktop.application.Application app) {
            super(app);
            refreshButton.setEnabled(false);
        }

        @Override
        protected Object doInBackground() {
            favoritesList = GameUtils.loadFavorites();
            serverList = MasterQuery.getServerList();
            if (serverList == null) {
                serverCount = 0;
            } else {
                serverCount = serverList.size();
                // TODO: Replace with Set.removeAll()
                if (favoritesList != null) {
                    for (String address : favoritesList) {
                        if (serverList.contains(address)) {
                            serverList.remove(address);
                        }
                    }
                    serverCount += favoritesList.size();
                }
            }
            return null;
        }

        @Override
        protected void succeeded(final Object result) {
            if (serverCount > 0) {
                tableModel.clear();
                serverVector = new ArrayList<Server>(serverCount);
                List<Future<Server>> futuresList = new ArrayList();

                class ServerQueryRunner implements Callable<Server> {

                    private final String address;

                    public ServerQueryRunner(final String addr) {
                        this.address = addr;
                    }

                    @Override
                    public Server call() throws Exception {
                        return ServerQuery.getStatus(address);
                    }
                }

                Future<Server> future;
                if (favoritesList != null) {
                    for (final String ip : favoritesList) {
                        future = pool.submit(new ServerQueryRunner(ip));
                        futuresList.add(future);
                    }
                }
                for (final String ip : serverList) {
                    future = pool.submit(new ServerQueryRunner(ip));
                    futuresList.add(future);
                }
                try {
                    Server server;
                    for (Future<Server> fut : futuresList) {
                        server = fut.get();
                        if (server != null) {
                            serverVector.add(server);
                            addRowToModel(server);
                        }
                    }
                } catch (ExecutionException ex) {
                } catch (InterruptedException ex) {
                }
            }
            refreshButton.setEnabled(true);
        }
    }

    /**
     * Stub.
     */
    @Action
    public void viewServer() {
        // TODO: Implement viewing server details
    }

    /**
     * Calls GameLauncher on selected server.
     */
    @Action
    public void connect() {
        final int selectedRow = serverTable.getSelectedRow();

        if (selectedRow > -1) {
            final String selectedServer = tableModel.getValueAt(serverTable.convertRowIndexToModel(selectedRow), ServerTableModel.HOSTNAME).toString();
            String selectedIp = null;
            for (Server server : tableModel.getDataVector()) {
                if (server.getHostname().equals(selectedServer)) {
                    selectedIp = server.getIp();
                    break;
                }
            }
            GameUtils.launchGame(selectedIp, sdlCheckBox.getState());
        } else if (serverVector == null) {  //TODO: Remove these and disable buttons when invalid
            JOptionPane.showMessageDialog(new Frame(), "Please update the server list");
        } else {
            JOptionPane.showMessageDialog(new Frame(), "Please select a server");
        }
    }

    @Action
    public void launchFavoriteServerDialog() {
        final int selectedRow = serverTable.getSelectedRow();
        String selectedIp = null;

        if (selectedRow > -1) {
            final String selectedServer = tableModel.getValueAt(serverTable.convertRowIndexToModel(selectedRow), ServerTableModel.HOSTNAME).toString();
            for (Server server : tableModel.getDataVector()) {
                if (server.getHostname().equals(selectedServer)) {
                    selectedIp = server.getIp();
                    break;
                }
            }
        }
        if (favoriteDialog == null) {
            final JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
            favoriteDialog = new FavoritesDialog(mainFrame, false);
            favoriteDialog.setLocationRelativeTo(this.getFrame());
            favoriteDialog.setTitle("Favorite");
        }
        if (selectedIp != null) {
            favoriteDialog.setAddressField(selectedIp);
        }
        TortillaApp.getApplication().show(favoriteDialog);
    }

    @Action
    public void filter() {
        if (serverCount > 0) {
            refreshTable();
        }
    }
}
