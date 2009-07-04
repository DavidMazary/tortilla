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
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.Timer;
import javax.swing.table.AbstractTableModel;

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
        serverTable = new javax.swing.JTable() {

            public String getToolTipText(MouseEvent e) {
                int nameColumn = 1;
                int selectedRow = rowAtPoint(e.getPoint());
                StringBuilder playerList = new StringBuilder("");
                if (!getModel().getColumnName(nameColumn).equals("Server")) {
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        if (model.getColumnName(i).equals("Server")) {
                            nameColumn = i;
                            break;
                        }
                    }
                }
                String selectedServer = this.getModel().getValueAt(convertRowIndexToModel(selectedRow), nameColumn).toString();
                for (Server server : model.getDataVector()) {
                    if (server.getHostname().equals(selectedServer)) {
                        if (server.getPlayerCount() > 0) {
                            playerList.append("<html>" + selectedRow + ": "+ server.getHostname() + "<br/>");
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
        connectButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
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
        serverTable.setModel(getModel());
        serverTable.setDoubleBuffered(true);
        serverTable.setName("serverTable"); // NOI18N
        serverTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        serverTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                serverTableFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                serverTableFocusLost(evt);
            }
        });
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
        favoriteServersToggleButton.setName("favoriteServersToggleButton"); // NOI18N

        connectButton.setAction(actionMap.get("connect")); // NOI18N
        connectButton.setMinimumSize(new java.awt.Dimension(42, 42));
        connectButton.setName("connectButton"); // NOI18N
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        updateButton.setAction(actionMap.get("update")); // NOI18N
        updateButton.setName("updateButton"); // NOI18N
        updateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        addButton.setAction(actionMap.get("launchFavoriteServerDialog")); // NOI18N
        addButton.setName("addButton"); // NOI18N

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addGap(18, 18, 18)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addComponent(favoriteServersToggleButton)
                .addGap(18, 18, 18)
                .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(updateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addComponent(connectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addComponent(favoriteServersToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(controlsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
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

private void serverTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverTableFocusGained
    connectButton.setEnabled(true);
}//GEN-LAST:event_serverTableFocusGained

private void serverTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverTableFocusLost
    connectButton.setEnabled(false);
}//GEN-LAST:event_serverTableFocusLost

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
    private ServerTableModel model = new ServerTableModel();
    private ArrayList<String> serverList;
    private ArrayList<String> favoriteServerList;
    private ConcurrentHashMap<String, Server> serverMap;
    private static final int HIGH_PING = 200;
    private String operatingSystem = null;

    /**
     * The custom DefaultTableModel used in TortillaView.
     * @return The DefaultTableModel used here.
     */
    public synchronized AbstractTableModel getModel() {
        return model;
    }

    /**
     * Toggle availability of update and refresh buttons so they
     * cannot be clicked mid-action.
     * 
     * @param state of buttons
     */
    private void setUpdateButtonsEnabled(boolean state) {
        updateButton.setEnabled(state);
    }

    /**
     * Refreshes the Table of server data using the stored serverMap.
     */
    private synchronized void refreshTable() {
        boolean canAddRow;

        Server current;
        for (String Ip : serverMap.keySet()) {
            if ((current = serverMap.get(Ip)) != null) {
                canAddRow = true;

                if ((hideEmptyMenuItem.getState() && (current.getPlayerCount() == 0)) ||
                        (hideFullMenuItem.getState() && (current.getPlayerCount() == current.getMaxPlayers())) ||
                        (hideHighPingMenuItem.getState() && (current.getPing() > HIGH_PING)) ||
                        (favoriteServersToggleButton.isSelected() && !current.isFavorite())) {
                    canAddRow = false;
                }

                String query = searchTextField.getText().toLowerCase();
                if (canAddRow && !query.isEmpty()) {
                    if ((current.getHostname() != null && !current.getHostname().toLowerCase().contains(query)) ||
                            (current.getMap() != null && !current.getMap().toLowerCase().contains(query))) {
                        canAddRow = false;
                    }
                    // Search for player matches (requires getstatus query)
                    if (canAddRow && (current.getPlayerList() != null)) {
                        for (Player player : current.getPlayerList()) {
                            if (player.getName().toLowerCase().contains(query)) {
                                canAddRow = true;
                                break;
                            }
                        }
                    }
                }

                if (model.getDataVector().contains(current)) {
                    model.deleteRow(current);
                }

                if (canAddRow) {
                    model.addRow(current);
                }
            }
        }
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
            setUpdateButtonsEnabled(false);
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
            setUpdateButtonsEnabled(true);
            refresh();
        }
    }

    /**
     * Gets details of each server in serverlist, and puts those in servermap.
     */
    @Action
    public void refresh() {
        // Refresh table model
        if (serverMap != null) {
            for (Server server : serverMap.values()) {
                model.deleteRow(server);
            }
        }
        serverMap = new ConcurrentHashMap<String, Server>();

        class ServerQueryThread extends Thread {

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

        if (favoriteServerList != null) {
            for (final String ip : favoriteServerList) {
                (new ServerQueryThread(ip, true)).start();
            }
        }
        for (final String ip : serverList) {
            (new ServerQueryThread(ip, false)).start();
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
        int nameColumn = 1;
        String selectedIp = "";

        if (selectedRow != -1) {
            if (!model.getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < model.getColumnCount(); i++) {
                    if (model.getColumnName(i).contains("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            String selectedServer = model.getValueAt(serverTable.convertRowIndexToModel(selectedRow), nameColumn).toString();
            Server current;
            for (String ip : serverMap.keySet()) {
                if (((current = serverMap.get(ip)) != null) &&
                        (current.getHostname().equals(selectedServer))) {
                    selectedIp = ip;
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
            if (!model.getColumnName(nameColumn).equals("Server")) {
                for (int i = 0; i < model.getColumnCount(); i++) {
                    if (model.getColumnName(i).equals("Server")) {
                        nameColumn = i;
                        break;
                    }
                }
            }
            String selectedServer = model.getValueAt(serverTable.convertRowIndexToModel(selectedRow), nameColumn).toString();
            Server current;
            for (String ip : serverMap.keySet()) {
                if (((current = serverMap.get(ip)) != null) &&
                        (current.getHostname().equals(selectedServer))) {
                    selectedIp = ip;
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
