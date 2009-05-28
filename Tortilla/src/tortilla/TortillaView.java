/*
 * TortillaView.java
 */
package tortilla;

import java.awt.Frame;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.Task;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
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
     * TODO Smooth refreshes (overwrite new values, don't delete data first).
     * @param app Application calling TortillaView
     */
    public TortillaView(SingleFrameApplication app) {
        super(app);

        initComponents();
        update().execute();

//        // Refreshes serverlist every 20 seconds.
//        int delay = 90000;
//        ActionListener refreshTask = new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                refreshButton.doClick();
//            }
//        };
//        new Timer(delay, refreshTask).start();
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
        serverTable = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        detailButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyReleased(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaView.class, this);
        connectButton.setAction(actionMap.get("connect")); // NOI18N
        connectButton.setFocusable(false);
        connectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectButton.setMinimumSize(new java.awt.Dimension(42, 42));
        connectButton.setName("connectButton"); // NOI18N
        connectButton.setPreferredSize(new java.awt.Dimension(42, 42));
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        detailButton.setAction(actionMap.get("viewServer")); // NOI18N
        detailButton.setFocusable(false);
        detailButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        detailButton.setMinimumSize(new java.awt.Dimension(42, 42));
        detailButton.setName("detailButton"); // NOI18N
        detailButton.setPreferredSize(new java.awt.Dimension(42, 42));
        detailButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        refreshButton.setAction(actionMap.get("refresh")); // NOI18N
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton.setMinimumSize(new java.awt.Dimension(42, 42));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setPreferredSize(new java.awt.Dimension(42, 42));
        refreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        updateButton.setAction(actionMap.get("update")); // NOI18N
        updateButton.setFocusable(false);
        updateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateButton.setMinimumSize(new java.awt.Dimension(42, 42));
        updateButton.setName("updateButton"); // NOI18N
        updateButton.setPreferredSize(new java.awt.Dimension(42, 42));
        updateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detailButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
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
    detailButton.setEnabled(true);
}//GEN-LAST:event_serverTableFocusGained

private void serverTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverTableFocusLost
    connectButton.setEnabled(false);
    detailButton.setEnabled(false);
}//GEN-LAST:event_serverTableFocusLost

private void searchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyReleased
    refreshTable();
}//GEN-LAST:event_searchTextFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JButton detailButton;
    private javax.swing.JCheckBoxMenuItem hideEmptyMenuItem;
    private javax.swing.JCheckBoxMenuItem hideFullMenuItem;
    private javax.swing.JCheckBoxMenuItem hideHighPingMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JButton refreshButton;
    private javax.swing.JCheckBoxMenuItem sdlCheckBoxMenuItem;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JTable serverTable;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JButton updateButton;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
    private JDialog aboutBox;
    private JDialog addPrivateServerBox;
    private GameLauncher launcher = new GameLauncher();
    private MasterQuery queryM = new MasterQuery();
    private AtomicReference<ServerTableModel> model = 
            new AtomicReference<ServerTableModel>(new ServerTableModel());
    private ArrayList<String> serverList;
    private ConcurrentHashMap<String, Server> serverMap;
    private static final int HIGH_PING = 200;

    /**
     * The custom DefaultTableModel used in TortillaView.
     * @return The DefaultTableModel used here.
     */
    public AbstractTableModel getModel() {
        return model.get();
    }

    /**
     * Toggle availability of update and refresh buttons so they
     * cannot be clicked mid-action.
     * 
     * @param state of buttons
     */
    private void setUpdateButtonsEnabled(boolean state) {
        updateButton.setEnabled(state);
        refreshButton.setEnabled(state);
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

                if ((hideEmptyMenuItem.getState() && (current.getPlayerCount() == 0)) || (hideFullMenuItem.getState() && (current.getPlayerCount() == current.getMaxPlayers())) || (hideHighPingMenuItem.getState() && (current.getPing() > HIGH_PING))) {
                    canAddRow = false;
                }

                String query = searchTextField.getText().toLowerCase();
                if (!query.isEmpty()) {
                    if (current.getHostname() != null && !current.getHostname().toLowerCase().contains(query)) {
                        canAddRow = false;
                    }
                    // Search for player matches; requires getstatus query
//                    if (players != null) {
//                        for (Player player : players) {
//                            if (player.getName().toLowerCase().contains(query)) {
//                                canAddRow = true;
//                                break;
//                            }
//                        }
//                    }
                    if (current.getMap() != null && current.getMap().toLowerCase().contains(query)) {
                        canAddRow = true;
                    }
                }


                if (model.get().getDataVector().contains(current)) {
                    model.get().deleteRow(current);
                }

                if (canAddRow) {
                    model.get().addRow(current);
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
            this.setMessage("Updating...");
            serverList = queryM.getServerList();
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
                model.get().deleteRow(server);
            }
        }
        serverMap = new ConcurrentHashMap<String, Server>();

        class ServerQueryThread extends Thread {

            String ip;

            public ServerQueryThread(String address) {
                ip = address;
            }

            @Override
            public void run() {
                Server server = new ServerQuery().getStatus(ip);
                if (server != null) {
                    serverMap.putIfAbsent(ip, server);
                    refreshTable();
                }
            }
        }

        for (final String ip : serverList) {
            (new ServerQueryThread(ip)).start();
        }
    }

    /**
     * Stub.
     */
    @Action
    public void addFavorite() {
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
            for (int i = 0; i < model.get().getColumnCount(); i++) {
                if (model.get().getColumnName(i).contains("Server")) {
                    nameColumn = i;
                }
            }
            String selectedServer = model.get().getValueAt(selectedRow,
                    nameColumn).toString();
            Server current;
            for (String ip : serverMap.keySet()) {
                if ((current = serverMap.get(ip)) != null) {
                    if (current.getHostname().equals(selectedServer)) {
                        selectedIp = ip;
                    }
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
    public void showPrivateServerBox() {
        if (addPrivateServerBox == null) {
            JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
            addPrivateServerBox = new TortillaAddPrivate(mainFrame, false);
            addPrivateServerBox.setLocationRelativeTo(mainFrame);
        }
        TortillaApp.getApplication().show(addPrivateServerBox);
    }
}
