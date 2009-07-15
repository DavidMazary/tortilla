/*
 * TortillaView.java
 */
package tortilla;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.Task;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;

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
        update().execute();
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
                int nameColumn = 1;
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
                for (Server server : tableModel.getDataVector()) {
                    if (server.getHostname().equals(selectedServer)) {
                        if (server.getPlayerCount() > 0) {
                            playerList.append("<html><b>Players</b><br/>");
                            for (Player player : server.getPlayerList()) {
                                playerList.append(player.getName() + "<br/>");
                            }
                            playerList.append("</html>");
                        }
                        break;
                    }
                }
                return playerList.toString();
            }
        };
        controlsPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        favoriteServersToggleButton = new javax.swing.JToggleButton();
        updateButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        hideHighPingMenuItem = new javax.swing.JCheckBoxMenuItem();
        hideEmptyMenuItem = new javax.swing.JCheckBoxMenuItem();
        hideFullMenuItem = new javax.swing.JCheckBoxMenuItem();
        optionsMenu = new javax.swing.JMenu();
        sdlCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        tableScrollPane.setDoubleBuffered(true);
        tableScrollPane.setName("tableScrollPane"); // NOI18N

        serverTable.setAutoCreateRowSorter(true);
        serverTable.setDoubleBuffered(true);
        serverTable.setName("serverTable"); // NOI18N
        serverTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableScrollPane.setViewportView(serverTable);
        serverTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        controlsPanel.setName("controlsPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaView.class, this);
        favoriteServersToggleButton.setAction(actionMap.get("filterFavorites")); // NOI18N
        favoriteServersToggleButton.setText(resourceMap.getString("favoriteServersToggleButton.text")); // NOI18N
        favoriteServersToggleButton.setBorderPainted(false);
        favoriteServersToggleButton.setName("favoriteServersToggleButton"); // NOI18N

        updateButton.setAction(actionMap.get("update")); // NOI18N
        updateButton.setBorderPainted(false);
        updateButton.setName("updateButton"); // NOI18N
        updateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        addButton.setAction(actionMap.get("launchFavoriteServerDialog")); // NOI18N
        addButton.setBorderPainted(false);
        addButton.setName("addButton"); // NOI18N

        connectButton.setAction(actionMap.get("connect")); // NOI18N
        connectButton.setBorderPainted(false);
        connectButton.setName("connectButton"); // NOI18N

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(favoriteServersToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(updateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(favoriteServersToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(connectButton)
                    .addGroup(controlsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(controlsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N
        menuBar.setPreferredSize(new java.awt.Dimension(214, 23));

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        viewMenu.setMnemonic('V');
        viewMenu.setText(resourceMap.getString("viewMenu.text")); // NOI18N
        viewMenu.setName("viewMenu"); // NOI18N

        hideHighPingMenuItem.setMnemonic('p');
        hideHighPingMenuItem.setSelected(true);
        hideHighPingMenuItem.setText(resourceMap.getString("hideHighPingMenuItem.text")); // NOI18N
        hideHighPingMenuItem.setToolTipText(resourceMap.getString("hideHighPingMenuItem.toolTipText")); // NOI18N
        hideHighPingMenuItem.setName("hideHighPingMenuItem"); // NOI18N
        hideHighPingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideHighPingMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(hideHighPingMenuItem);

        hideEmptyMenuItem.setMnemonic('e');
        hideEmptyMenuItem.setSelected(true);
        hideEmptyMenuItem.setText(resourceMap.getString("hideEmptyMenuItem.text")); // NOI18N
        hideEmptyMenuItem.setToolTipText(resourceMap.getString("hideEmptyMenuItem.toolTipText")); // NOI18N
        hideEmptyMenuItem.setName("hideEmptyMenuItem"); // NOI18N
        hideEmptyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideEmptyMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(hideEmptyMenuItem);

        hideFullMenuItem.setMnemonic('f');
        hideFullMenuItem.setText(resourceMap.getString("hideFullMenuItem.text")); // NOI18N
        hideFullMenuItem.setToolTipText(resourceMap.getString("hideFullMenuItem.toolTipText")); // NOI18N
        hideFullMenuItem.setName("hideFullMenuItem"); // NOI18N
        hideFullMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideFullMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(hideFullMenuItem);

        menuBar.add(viewMenu);

        optionsMenu.setMnemonic('G');
        optionsMenu.setText(resourceMap.getString("optionsMenu.text")); // NOI18N
        optionsMenu.setName("optionsMenu"); // NOI18N

        sdlCheckBoxMenuItem.setMnemonic('S');
        sdlCheckBoxMenuItem.setSelected(true);
        sdlCheckBoxMenuItem.setText(resourceMap.getString("sdlCheckBoxMenuItem.text")); // NOI18N
        sdlCheckBoxMenuItem.setToolTipText(resourceMap.getString("sdlCheckBoxMenuItem.toolTipText")); // NOI18N
        sdlCheckBoxMenuItem.setName("sdlCheckBoxMenuItem"); // NOI18N
        optionsMenu.add(sdlCheckBoxMenuItem);

        menuBar.add(optionsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Get server list from master server when clicked.
     * @param evt
     */
private void hideHighPingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideHighPingMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_hideHighPingMenuItemActionPerformed

private void hideEmptyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideEmptyMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_hideEmptyMenuItemActionPerformed

private void hideFullMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideFullMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_hideFullMenuItemActionPerformed

private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
    refreshTable();
}//GEN-LAST:event_searchTextFieldKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JToggleButton favoriteServersToggleButton;
    private javax.swing.JCheckBoxMenuItem hideEmptyMenuItem;
    private javax.swing.JCheckBoxMenuItem hideFullMenuItem;
    private javax.swing.JCheckBoxMenuItem hideHighPingMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JCheckBoxMenuItem sdlCheckBoxMenuItem;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable serverTable;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JButton updateButton;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
    private JDialog aboutBox;
    private FavoriteServerDialog addPrivateServerBox;
    private GameLauncher launcher = new GameLauncher();
    private MasterQuery queryM = new MasterQuery();
    private ServerTableModel tableModel = new ServerTableModel();
    private ArrayList<String> serverList;
    private ArrayList<String> favoriteServerList;
    private ConcurrentHashMap<String, Server> serverMap;
    private static final int HIGH_PING = 200;
    private String operatingSystem = null;
    private Vector<SortKey> sortOrder = new Vector<SortKey>(5);


    /**
     * Refreshes the Table of server data using the stored serverMap.
     */
    private synchronized void refreshTable() {
        boolean canAddRow;

        for (Server server : serverMap.values()) {
            canAddRow = true;
            String query = searchTextField.getText().toLowerCase();

            // Filter by the preferences
            if ((hideEmptyMenuItem.getState() && (server.getPlayerCount() == 0)) ||
                    (hideFullMenuItem.getState() && (server.getPlayerCount() == server.getMaxPlayers())) ||
                    (hideHighPingMenuItem.getState() && (server.getPing() > HIGH_PING)) ||
                    (favoriteServersToggleButton.isSelected() && !server.isFavorite())) {
                canAddRow = false;
                // Filter by the search term
            } else if (!query.isEmpty()) {
                canAddRow = false;
                if (server.getHostname().toLowerCase().contains(query) ||
                        server.getMap().toLowerCase().contains(query)) {
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

            if (tableModel.getDataVector().contains(server)) {
                tableModel.deleteRow(server);
            }
            if (canAddRow) {
                tableModel.insertRow(server);
            }
        }

        serverTable.getRowSorter().setSortKeys(sortOrder);
    }

    /**
     * Use MasterQuery to download new serverlist.
     * @return
     */
    @Action
    public Task update() {
        return new UpdateTask(getApplication());
    }

    private class UpdateTask extends org.jdesktop.application.Task<Object, Void> {

        UpdateTask(org.jdesktop.application.Application app) {
            super(app);
            updateButton.setEnabled(false);
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
            }
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            updateButton.setEnabled(true);
            refresh();
            serverList.clear();
            favoriteServerList.clear();
        }
    }

    /**
     * Gets details of each server in serverlist, and puts those in servermap.
     */
    @Action
    public void refresh() {
        // Refresh table tableModel
        if (!tableModel.getDataVector().isEmpty()) {
            int dataSize = tableModel.getDataVector().size();
            tableModel.getDataVector().clear();
            tableModel.fireTableRowsDeleted(0, dataSize - 1);
        }
        serverMap = new ConcurrentHashMap<String, Server>();

        class ServerQueryThread implements Runnable {

            String ip;
            boolean favorite;

            public ServerQueryThread(String address, boolean fav) {
                ip = address;
                favorite = fav;
            }

            @Override
            public void run() {
                Server server = new ServerQuery().getStatus(ip);
                if (server != null) {
                    server.setFavorite(favorite);
                    serverMap.putIfAbsent(ip, server);
                    refreshTable();
                }
            }
        }

        ExecutorService pool = Executors.newFixedThreadPool(200);
        if (favoriteServerList != null) {
            for (final String ip : favoriteServerList) {
                pool.execute(new ServerQueryThread(ip, true));
            }
        }
        for (final String ip : serverList) {
            pool.execute(new ServerQueryThread(ip, false));
        }
        pool.shutdown();
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
        int nameColumn = 1;
        String selectedIp = "";

        if (selectedRow != -1) {
            if (!tableModel.getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    if (tableModel.getColumnName(i).equals("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            String selectedServer = tableModel.getValueAt(serverTable.convertRowIndexToModel(selectedRow), nameColumn).toString();
            for (Server server : tableModel.getDataVector()) {
                if (server.getHostname().equals(selectedServer)) {
                    selectedIp = server.getIp();
                    break;
                }
            }

            launcher.setSdl(sdlCheckBoxMenuItem.getState());
            launcher.setIp(selectedIp);
            launcher.playGame();
        } else if (serverMap == null) {
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
        int nameColumn = 1;
        String selectedIp = null;

        if (selectedRow != -1) {
            if (!tableModel.getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                    if (tableModel.getColumnName(i).equals("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            String selectedServer = tableModel.getValueAt(serverTable.convertRowIndexToModel(selectedRow), nameColumn).toString();
            for (Server server : tableModel.getDataVector()) {
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
    public void filterFavorites() {
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
}
