/*
 * TortillaView.java
 */
package tortilla;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.Task;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 * The application's main frame.
 * TODO Create custom table (ineditable and properly sortable).
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
        updateButton.doClick();

        // Refreshes serverlist every 20 seconds.
        int delay = 90000;
        ActionListener refreshTask = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                refreshButton.doClick();
            }
        };
        new Timer(delay, refreshTask).start();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        searchTextField = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        updateButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        detailButton = new javax.swing.JButton();
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

        jScrollPane1.setDoubleBuffered(true);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(getModel());
        jTable1.setDoubleBuffered(true);
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setName("jToolBar1"); // NOI18N
        jToolBar1.setPreferredSize(new java.awt.Dimension(214, 42));

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaView.class, this);
        updateButton.setAction(actionMap.get("update")); // NOI18N
        updateButton.setFocusable(false);
        updateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        updateButton.setMinimumSize(new java.awt.Dimension(42, 42));
        updateButton.setName("updateButton"); // NOI18N
        updateButton.setPreferredSize(new java.awt.Dimension(42, 42));
        updateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(updateButton);

        refreshButton.setAction(actionMap.get("refresh")); // NOI18N
        refreshButton.setFocusable(false);
        refreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        refreshButton.setMinimumSize(new java.awt.Dimension(42, 42));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setPreferredSize(new java.awt.Dimension(42, 42));
        refreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(refreshButton);

        detailButton.setAction(actionMap.get("viewServer")); // NOI18N
        detailButton.setFocusable(false);
        detailButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        detailButton.setMinimumSize(new java.awt.Dimension(42, 42));
        detailButton.setName("detailButton"); // NOI18N
        detailButton.setPreferredSize(new java.awt.Dimension(42, 42));
        detailButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(detailButton);

        connectButton.setAction(actionMap.get("connect")); // NOI18N
        connectButton.setFocusable(false);
        connectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectButton.setMinimumSize(new java.awt.Dimension(42, 42));
        connectButton.setName("connectButton"); // NOI18N
        connectButton.setPreferredSize(new java.awt.Dimension(42, 42));
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(connectButton);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
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
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        update();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

private void hideHighPingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideHighPingMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_hideHighPingMenuItemActionPerformed

private void hideEmptyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideEmptyMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_hideEmptyMenuItemActionPerformed

private void hideFullMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideFullMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_hideFullMenuItemActionPerformed

private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
    refreshTable();
}//GEN-LAST:event_searchTextFieldKeyTyped

private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
    connectButton.setEnabled(true);
    detailButton.setEnabled(true);
}//GEN-LAST:event_jTable1FocusGained

private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
    connectButton.setEnabled(false);
    detailButton.setEnabled(false);
}//GEN-LAST:event_jTable1FocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JButton detailButton;
    private javax.swing.JCheckBoxMenuItem hideEmptyMenuItem;
    private javax.swing.JCheckBoxMenuItem hideFullMenuItem;
    private javax.swing.JCheckBoxMenuItem hideHighPingMenuItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JButton refreshButton;
    private javax.swing.JCheckBoxMenuItem sdlCheckBoxMenuItem;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton updateButton;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
    private JDialog aboutBox;
    private JDialog addPrivateServerBox;
    private GameLauncher launcher = new GameLauncher();
    private MasterQuery queryM = new MasterQuery();
    private ServerQuery queryS = new ServerQuery();
    private DefaultTableModel model = new DefaultTableModel();
    private ArrayList<String> serverList;
    private ConcurrentHashMap<String, Server> serverMap;
    private static final int MAX_PING = 120;

    /**
     * The custom DefaultTableModel used in TortillaView.
     * @return The DefaultTableModel used here.
     */
    public synchronized DefaultTableModel getModel() {
//        model = new ServerTableModel();
        return model;
    }

    /**
     * Toggle availability of buttons under certain conditions.
     * States: 0 for Update/Refresh clicked, 
     * 1 for Update/Refresh finished,
     * 
     * @param state State of buttons.
     */
    protected void toggleButtons(int state) {
        if (state == 0) {
            updateButton.setEnabled(false);
            refreshButton.setEnabled(false);
        } else if (state == 1) {
            updateButton.setEnabled(true);
            refreshButton.setEnabled(true);
        }
    }

    /**
     * Refreshes the Table of server data using the stored serverMap.
     */
    protected synchronized void refreshTable() {
        for (int j = getModel().getRowCount() - 1; j >= 0; j--) {
            getModel().removeRow(j);
        }
        int ping;
        String hostname;
        int players;
        int maxplayers;
        String map;
        int count = 0;
        boolean permission;
        for (String Ip : serverMap.keySet()) {
            permission = true;
            ping = serverMap.get(Ip).getPing();
            hostname = serverMap.get(Ip).getHostname();
            players = serverMap.get(Ip).getPlayerCount();
            maxplayers = serverMap.get(Ip).getMaxPlayers();
            map = serverMap.get(Ip).getMap();
            if (hideEmptyMenuItem.getState()) {
                if (players == 0) {
                    permission = false;
                }
            }
            if (hideFullMenuItem.getState()) {
                if (players == maxplayers) {
                    permission = false;
                }
            }
            if (hideHighPingMenuItem.getState()) {
                if (ping > MAX_PING) {
                    permission = false;
                }
            }

            String query = searchTextField.getText().toLowerCase();
            if (!query.equals("") && hostname != null) {
                if (!hostname.toLowerCase().contains(query)) {
                    permission = false;
                }
            } else if (hostname == null) {
                permission = false;
            }

            if (permission) {
                getModel().addRow(
                        new Object[]{ping, hostname,
                            players, maxplayers, map
                        });
            }
            count++;
        }
    }

//    static class ServerTableModel extends DefaultTableModel {
//
//        String[] columnNames = {
//            "Ping", "Server", "Players", "Max", "Map"
//        };
////        Object data[][];
//
////        /**
////         * Returns the number of rows in the model.
////         * A JTable uses this method to determine how many rows it should display.
////         * This method should be quick, as it is called frequently during rendering.
////         * @return the number of rows in the model
////         */
////        @Override
////        public int getRowCount() {
////            return data.length;
////        }
////
//        /**
//         * Returns the number of columns in the model.
//         * A JTable uses this method to determine how
//         * many columns it should create and display by default.
//         * @return the number of columns in the model
//         */
//        @Override
//        public int getColumnCount() {
//            return columnNames.length;
//        }
//
//        @Override
//        public String getColumnName(int index) {
//            return columnNames[index];
//        }
//
//        /**
//         * Treat my numbers with more respect Mr. Table!
//         * @param column the column whose value is to be queried
//         * @return the value Class of the specified column
//         */
//        @Override
//        public Class getColumnClass(int column) {
//            Class dataType = String.class;
//            if (column == 0 || column == 2 || column == 3) {
//                dataType = Number.class;
//            }
//            return dataType;
//        }
//
//        /**
//         * No editing of any cells.
//         * @param row the row whose value is to be queried
//         * @param column the column whose value is to be queried
//         * @return True if cell is editable at given coordinates
//         */
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return false;
//        }
//    }
    /**
     * Use MasterQuery to download new serverlist.
     */
    @Action
    public Task update() {
        return new UpdateTask(getApplication());
    }

    private class UpdateTask extends org.jdesktop.application.Task<Object, Void> {

        UpdateTask(org.jdesktop.application.Application app) {
            super(app);
            toggleButtons(0);
        }

        @Override
        protected Object doInBackground() {
            this.setMessage("Updating...");
//            do {
            serverList = queryM.getServerList();
//            } while (!queryM.isDone());
            return null;
        }

        @Override
        protected void succeeded(Object result) {
            toggleButtons(1);
            refreshButton.doClick();
        }
    }

    /**
     * Gets details of each server in serverlist, and puts those in servermap.
     */
    @Action
    public Task refresh() {
        return new RefreshTask(getApplication());
    }

    private class RefreshTask extends org.jdesktop.application.Task<Object, Void> {

        RefreshTask(org.jdesktop.application.Application app) {
            super(app);
            if (getModel().getColumnCount() != 5) {
                getModel().addColumn("Ping");
                getModel().addColumn("Server");
                getModel().addColumn("Players");
                getModel().addColumn("Max");
                getModel().addColumn("Map");

                jTable1.getColumnModel().getColumn(0).setMaxWidth(36);
                jTable1.getColumnModel().getColumn(2).setMaxWidth(48);
                jTable1.getColumnModel().getColumn(3).setMaxWidth(36);
                jTable1.getColumnModel().getColumn(4).setMaxWidth(150);
            }

            toggleButtons(0);
        }

        @Override
        protected Object doInBackground() {
            this.setMessage("Refreshing...");

            // FindBugs complains that this is unsynchronized.
            serverMap = new ConcurrentHashMap<String, Server>();

            for (final String ip : serverList) {
                class ServerQuerier extends Thread {

                    @Override
                    public void run() {
                        Server server = queryS.getInfo(ip);

                        if (server != null) {
                            serverMap.put(ip, server);
                            refreshTable();
                        }
                    }
                }
                Thread querier = new ServerQuerier();
                querier.start();
            }
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result) {
            toggleButtons(1);
        }
    }

    @Action
    public void addFavorite() {
    }

    @Action
    public void viewServer() {
    }

    /**
     * Calls GameLauncher on selected server.
     */
    @Action
    public synchronized void connect() {
        int selectedRow = jTable1.getSelectedRow();
        int nameColumn = 1;
        String selectedIp = "";
        if (selectedRow != -1) {
            for (int i = 0; i < getModel().getColumnCount(); i++) {
                if (getModel().getColumnName(i).contains("Server")) {
                    nameColumn = i;
                }
            }
            String selectedServer = getModel().getValueAt(selectedRow,
                    nameColumn).toString();
            for (String ip : serverMap.keySet()) {
                String exp = serverMap.get(ip).getHostname();
                if (exp != null) {
                    if (exp.contains(selectedServer)) {
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
