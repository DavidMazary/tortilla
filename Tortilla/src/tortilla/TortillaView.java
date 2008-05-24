/*
 * TortillaView.java
 */
package tortilla;

import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 * The application's main frame.
 * @todo Create custom table (ineditable and properly sortable).
 * @todo Implement Favorite servers.
 */
public class TortillaView extends FrameView {

    private TortillaGameLauncher launcher = new TortillaGameLauncher();
    private TortillaQueryMaster queryM = new TortillaQueryMaster();
    private TortillaQueryServer queryS = new TortillaQueryServer();
    private TortillaAddPrivateServer addPrivateServer;
    private DefaultTableModel model = new DefaultTableModel();
    private ArrayList<String> serverList;
    private ConcurrentHashMap<String, TortillaServer> serverMap;
    private static final int MAX_PING = 200;

    /**
     * Create a new TortillaView using given SingleFrameApplication.
     * @param app Application calling TortillaView
     */
    public TortillaView(SingleFrameApplication app) {
        super(app);

        initComponents();
        refresh();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger(
                "StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
//        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    /**
     *
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
        jToolBar1 = new javax.swing.JToolBar();
        updateButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        detailButton = new javax.swing.JButton();
        favoriteButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        searchTextField = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        optionsMenu = new javax.swing.JMenu();
        sdlCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        viewMenu = new javax.swing.JMenu();
        refreshMenuItem = new javax.swing.JMenuItem();
        updateMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        showEmptyCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        showFullCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        favoritesCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        serverMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        favoriteMenuItem = new javax.swing.JMenuItem();
        detailMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        addPrivateServerMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        statusMessageLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        statusAnimationLabel = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

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
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setName("jToolBar1"); // NOI18N
        jToolBar1.setPreferredSize(new java.awt.Dimension(162, 42));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        updateButton.setIcon(resourceMap.getIcon("updateButton.icon")); // NOI18N
        updateButton.setText(resourceMap.getString("updateButton.text")); // NOI18N
        updateButton.setToolTipText(resourceMap.getString("updateButton.toolTipText")); // NOI18N
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

        refreshButton.setIcon(resourceMap.getIcon("refreshButton.icon")); // NOI18N
        refreshButton.setText(resourceMap.getString("refreshButton.text")); // NOI18N
        refreshButton.setToolTipText(resourceMap.getString("refreshButton.toolTipText")); // NOI18N
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

        detailButton.setIcon(resourceMap.getIcon("detailButton.icon")); // NOI18N
        detailButton.setText(resourceMap.getString("detailButton.text")); // NOI18N
        detailButton.setToolTipText(resourceMap.getString("detailButton.toolTipText")); // NOI18N
        detailButton.setFocusable(false);
        detailButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        detailButton.setMinimumSize(new java.awt.Dimension(42, 42));
        detailButton.setName("detailButton"); // NOI18N
        detailButton.setPreferredSize(new java.awt.Dimension(42, 42));
        detailButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(detailButton);

        favoriteButton.setIcon(resourceMap.getIcon("favoriteButton.icon")); // NOI18N
        favoriteButton.setToolTipText(resourceMap.getString("favoriteButton.toolTipText")); // NOI18N
        favoriteButton.setFocusable(false);
        favoriteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        favoriteButton.setMinimumSize(new java.awt.Dimension(42, 42));
        favoriteButton.setName("favoriteButton"); // NOI18N
        favoriteButton.setPreferredSize(new java.awt.Dimension(42, 42));
        favoriteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(favoriteButton);

        connectButton.setIcon(resourceMap.getIcon("connectButton.icon")); // NOI18N
        connectButton.setText(resourceMap.getString("connectButton.text")); // NOI18N
        connectButton.setToolTipText(resourceMap.getString("connectButton.toolTipText")); // NOI18N
        connectButton.setFocusable(false);
        connectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectButton.setMinimumSize(new java.awt.Dimension(42, 42));
        connectButton.setName("connectButton"); // NOI18N
        connectButton.setPreferredSize(new java.awt.Dimension(42, 42));
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(connectButton);

        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        optionsMenu.setText(resourceMap.getString("optionsMenu.text")); // NOI18N
        optionsMenu.setName("optionsMenu"); // NOI18N

        sdlCheckBoxMenuItem.setSelected(true);
        sdlCheckBoxMenuItem.setText(resourceMap.getString("sdlCheckBoxMenuItem.text")); // NOI18N
        sdlCheckBoxMenuItem.setToolTipText(resourceMap.getString("sdlCheckBoxMenuItem.toolTipText")); // NOI18N
        sdlCheckBoxMenuItem.setName("sdlCheckBoxMenuItem"); // NOI18N
        optionsMenu.add(sdlCheckBoxMenuItem);

        menuBar.add(optionsMenu);

        viewMenu.setMnemonic('V');
        viewMenu.setText(resourceMap.getString("viewMenu.text")); // NOI18N
        viewMenu.setName("viewMenu"); // NOI18N

        refreshMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        refreshMenuItem.setIcon(resourceMap.getIcon("refreshMenuItem.icon")); // NOI18N
        refreshMenuItem.setText(resourceMap.getString("refreshMenuItem.text")); // NOI18N
        refreshMenuItem.setToolTipText(resourceMap.getString("refreshMenuItem.toolTipText")); // NOI18N
        refreshMenuItem.setName("refreshMenuItem"); // NOI18N
        refreshMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(refreshMenuItem);

        updateMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        updateMenuItem.setIcon(resourceMap.getIcon("updateMenuItem.icon")); // NOI18N
        updateMenuItem.setText(resourceMap.getString("updateMenuItem.text")); // NOI18N
        updateMenuItem.setToolTipText(resourceMap.getString("updateMenuItem.toolTipText")); // NOI18N
        updateMenuItem.setName("updateMenuItem"); // NOI18N
        updateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(updateMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        viewMenu.add(jSeparator2);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText(resourceMap.getString("jCheckBoxMenuItem1.text")); // NOI18N
        jCheckBoxMenuItem1.setToolTipText(resourceMap.getString("jCheckBoxMenuItem1.toolTipText")); // NOI18N
        jCheckBoxMenuItem1.setName("jCheckBoxMenuItem1"); // NOI18N
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        viewMenu.add(jCheckBoxMenuItem1);

        showEmptyCheckBoxMenuItem.setSelected(true);
        showEmptyCheckBoxMenuItem.setText(resourceMap.getString("showEmptyCheckBoxMenuItem.text")); // NOI18N
        showEmptyCheckBoxMenuItem.setToolTipText(resourceMap.getString("showEmptyCheckBoxMenuItem.toolTipText")); // NOI18N
        showEmptyCheckBoxMenuItem.setName("showEmptyCheckBoxMenuItem"); // NOI18N
        showEmptyCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEmptyCheckBoxMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(showEmptyCheckBoxMenuItem);

        showFullCheckBoxMenuItem.setText(resourceMap.getString("showFullCheckBoxMenuItem.text")); // NOI18N
        showFullCheckBoxMenuItem.setToolTipText(resourceMap.getString("showFullCheckBoxMenuItem.toolTipText")); // NOI18N
        showFullCheckBoxMenuItem.setName("showFullCheckBoxMenuItem"); // NOI18N
        showFullCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFullCheckBoxMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(showFullCheckBoxMenuItem);

        favoritesCheckBoxMenuItem.setText(resourceMap.getString("favoritesCheckBoxMenuItem.text")); // NOI18N
        favoritesCheckBoxMenuItem.setToolTipText(resourceMap.getString("favoritesCheckBoxMenuItem.toolTipText")); // NOI18N
        favoritesCheckBoxMenuItem.setEnabled(false);
        favoritesCheckBoxMenuItem.setName("favoritesCheckBoxMenuItem"); // NOI18N
        favoritesCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoritesCheckBoxMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(favoritesCheckBoxMenuItem);

        menuBar.add(viewMenu);

        serverMenu.setText(resourceMap.getString("serverMenu.text")); // NOI18N
        serverMenu.setName("serverMenu"); // NOI18N

        connectMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        connectMenuItem.setIcon(resourceMap.getIcon("connectMenuItem.icon")); // NOI18N
        connectMenuItem.setText(resourceMap.getString("connectMenuItem.text")); // NOI18N
        connectMenuItem.setToolTipText(resourceMap.getString("connectMenuItem.toolTipText")); // NOI18N
        connectMenuItem.setName("connectMenuItem"); // NOI18N
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerformed(evt);
            }
        });
        serverMenu.add(connectMenuItem);

        favoriteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        favoriteMenuItem.setIcon(resourceMap.getIcon("favoriteMenuItem.icon")); // NOI18N
        favoriteMenuItem.setText(resourceMap.getString("favoriteMenuItem.text")); // NOI18N
        favoriteMenuItem.setName("favoriteMenuItem"); // NOI18N
        serverMenu.add(favoriteMenuItem);

        detailMenuItem.setIcon(resourceMap.getIcon("detailMenuItem.icon")); // NOI18N
        detailMenuItem.setText(resourceMap.getString("detailMenuItem.text")); // NOI18N
        detailMenuItem.setToolTipText(resourceMap.getString("detailMenuItem.toolTipText")); // NOI18N
        detailMenuItem.setName("detailMenuItem"); // NOI18N
        serverMenu.add(detailMenuItem);

        jSeparator3.setName("jSeparator3"); // NOI18N
        serverMenu.add(jSeparator3);

        addPrivateServerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        addPrivateServerMenuItem.setIcon(resourceMap.getIcon("addPrivateServerMenuItem.icon")); // NOI18N
        addPrivateServerMenuItem.setText(resourceMap.getString("addPrivateServerMenuItem.text")); // NOI18N
        addPrivateServerMenuItem.setToolTipText(resourceMap.getString("addPrivateServerMenuItem.toolTipText")); // NOI18N
        addPrivateServerMenuItem.setEnabled(false);
        addPrivateServerMenuItem.setName("addPrivateServerMenuItem"); // NOI18N
        addPrivateServerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrivateServerMenuItemActionPerformed(evt);
            }
        });
        serverMenu.add(addPrivateServerMenuItem);

        menuBar.add(serverMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        progressBar.setEnabled(false);
        progressBar.setName("progressBar"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusMessageLabel)
                        .addComponent(statusAnimationLabel))
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Get server list from master server when clicked.
     * @param evt
     */
    private void updateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMenuItemActionPerformed
        update();
}//GEN-LAST:event_updateMenuItemActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        update();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        connect();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void refreshMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshMenuItemActionPerformed
        refresh();
}//GEN-LAST:event_refreshMenuItemActionPerformed

    /**
     * When searchTextField gains focus, highlight text in the box.
     * @param evt
     */
    private void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        connect();
}//GEN-LAST:event_connectMenuItemActionPerformed

    /**
     * Creates a new "Add Private Server" dialog.
     * @todo Implement "Add Private Server"
     * @param evt
     */
    private void addPrivateServerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrivateServerMenuItemActionPerformed
        if (addPrivateServer == null) {
            JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
            addPrivateServer = new TortillaAddPrivateServer();
            addPrivateServer.setLocationRelativeTo(mainFrame);
        }
        TortillaApp.getApplication().show(addPrivateServer);
}//GEN-LAST:event_addPrivateServerMenuItemActionPerformed

private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
    refreshTable();
}//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

private void showEmptyCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEmptyCheckBoxMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_showEmptyCheckBoxMenuItemActionPerformed

private void showFullCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFullCheckBoxMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_showFullCheckBoxMenuItemActionPerformed

private void favoritesCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoritesCheckBoxMenuItemActionPerformed
    refreshTable();
}//GEN-LAST:event_favoritesCheckBoxMenuItemActionPerformed

private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
    refreshTable();
}//GEN-LAST:event_searchTextFieldKeyTyped

private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
    connectButton.setEnabled(true);
    connectMenuItem.setEnabled(true);
    detailButton.setEnabled(true);
    detailMenuItem.setEnabled(true);
    favoriteButton.setEnabled(true);
    favoriteMenuItem.setEnabled(true);
}//GEN-LAST:event_jTable1FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addPrivateServerMenuItem;
    private javax.swing.JButton connectButton;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JButton detailButton;
    private javax.swing.JMenuItem detailMenuItem;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JMenuItem favoriteMenuItem;
    private javax.swing.JCheckBoxMenuItem favoritesCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton refreshButton;
    private javax.swing.JMenuItem refreshMenuItem;
    private javax.swing.JCheckBoxMenuItem sdlCheckBoxMenuItem;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JMenu serverMenu;
    private javax.swing.JCheckBoxMenuItem showEmptyCheckBoxMenuItem;
    private javax.swing.JCheckBoxMenuItem showFullCheckBoxMenuItem;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton updateButton;
    private javax.swing.JMenuItem updateMenuItem;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;

    /**
     * The custom DefaultTableModel used in TortillaView.
     * @return The DefaultTableModel used here.
     */
    public DefaultTableModel getModel() {
//        model = new ServerTableModel();
        return model;
    }

    /**
     * Calls TortillaGameLauncher on selected server.
     */
    public void connect() {
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

    /**
     * Use TortillaQueryMaster to download new serverlist.
     */
    public void update() {
        SwingWorker Task = new SwingWorker<Void, Void>() {
            /*
             * Main task. Executed in background thread.
             */

            @Override
            public Void doInBackground() {
                updateButton.setEnabled(false);
                updateMenuItem.setEnabled(false);
                statusMessageLabel.setText("Updating...");
                if (!busyIconTimer.isRunning()) {
                    statusAnimationLabel.setIcon(busyIcons[0]);
                    busyIconIndex = 0;
                    busyIconTimer.start();
                }
                queryM.saveServerList();
                statusMessageLabel.setText("");
                busyIconTimer.stop();
                statusAnimationLabel.setIcon(idleIcon);
                updateButton.setEnabled(true);
                updateMenuItem.setEnabled(true);
                return null;
            }

            /*
             * Executed in event dispatch thread
             */
            @Override
            public void done() {
                refresh();
            }
        };
        Task.execute();
    }

    /**
     * Gets details of each server in serverlist, and puts those in servermap.
     */
    public void refresh() {

        if (getModel().getColumnCount() != 5) {
            getModel().addColumn("Ping");
            getModel().addColumn("Server");
            getModel().addColumn("Players");
            getModel().addColumn("Max");
            getModel().addColumn("Map");
        }

        if (serverList == null) {
            BufferedReader reader = null;
            try {
                FileReader fReader = new FileReader("servercache");
                reader = new BufferedReader(fReader);
                serverList = new ArrayList<String>();
                String line;
                while ((line = reader.readLine()) != null) {
                    serverList.add(line);
                }
            } catch (FileNotFoundException e) {
                statusMessageLabel.setText("Click update to begin...");
            } catch (IOException ex) {
                Logger.getLogger(TortillaView.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

        if (serverList != null) {
            SwingWorker worker = new SwingWorker<Void, Void>() {

                @Override
                public Void doInBackground() {
                    serverMap = new ConcurrentHashMap<String, TortillaServer>();

                    for (final String ip : serverList) {
                        class ServerQuerier extends Thread {

                            @Override
                            public void run() {
                                TortillaServer server = queryS.getInfo(ip);
                                if (server != null) {
                                    serverMap.put(ip, server);
                                }
                            }
                        }
                        Thread querier = new ServerQuerier();
                        querier.start();
                    }
                    try {
                        refreshButton.setEnabled(false);
                        refreshMenuItem.setEnabled(false);
                        statusMessageLabel.setText("Refreshing...");
                        progressBar.setVisible(true);
                        progressBar.setIndeterminate(false);
                        for (int i = 1; i < 100; i++) {
                            progressBar.setValue(i);
                            Thread.sleep(30);
                        }
                        progressBar.setVisible(false);
                        statusMessageLabel.setText("");
                        refreshButton.setEnabled(true);
                        refreshMenuItem.setEnabled(true);
                        connectButton.setEnabled(false);
                        connectMenuItem.setEnabled(false);
                        detailButton.setEnabled(false);
                        detailMenuItem.setEnabled(false);
                        favoriteButton.setEnabled(false);
                        favoriteMenuItem.setEnabled(false);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TortillaView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                    return null;
                }

                @Override
                public void done() {
                    refreshTable();
                }
            };
            worker.execute();
        }
    }

    /**
     * Refreshes the Table of server data using the stored serverMap.
     */
    protected void refreshTable() {
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
            if (showEmptyCheckBoxMenuItem.getState()) {
                if (players == 0) {
                    permission = false;
                }
            }
            if (showFullCheckBoxMenuItem.getState()) {
                if (players == maxplayers) {
                    permission = false;
                }
            }
            if (jCheckBoxMenuItem1.getState()) {
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

    static class ServerTableModel extends DefaultTableModel {

        String[] columnNames = {
            "Ping", "Server", "Players", "Max", "Map"
        };


//        Object data[][];

//        /**
//         * Returns the number of rows in the model.
//         * A JTable uses this method to determine how many rows it should display.
//         * This method should be quick, as it is called frequently during rendering.
//         * @return the number of rows in the model
//         */
//        @Override
//        public int getRowCount() {
//            return data.length;
//        }
//
        /**
         * Returns the number of columns in the model.
         * A JTable uses this method to determine how
         * many columns it should create and display by default.
         * @return the number of columns in the model
         */
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int index) {
            return columnNames[index];
        }

        /**
         * Treat my numbers with more respect Mr. Table!
         * @param column the column whose value is to be queried
         * @return the value Class of the specified column
         */
        @Override
        public Class getColumnClass(int column) {
            Class dataType = String.class;
            if (column == 0 || column == 2 || column == 3) {
                dataType = Number.class;
            }
            return dataType;
        }

        /**
         * No editing of any cells.
         * @param row the row whose value is to be queried
         * @param column the column whose value is to be queried
         * @return True if cell is editable at given coordinates
         */
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
