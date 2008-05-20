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
import javax.swing.table.TableColumn;

/**
 * The application's main frame.
 * @todo Create custom table (ineditable and properly sortable).
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
     *
     * @param app
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
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
//                    progressBar.setVisible(false);
//                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer) (evt.getNewValue());
//                    progressBar.setVisible(true);
//                    progressBar.setIndeterminate(false);
//                    progressBar.setValue(value);
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
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem5 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaView.class);
        searchTextField.setText(resourceMap.getString("searchTextField.text")); // NOI18N
        searchTextField.setToolTipText(resourceMap.getString("searchTextField.toolTipText")); // NOI18N
        searchTextField.setName("searchTextField"); // NOI18N
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        searchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusGained(evt);
            }
        });

        searchButton.setIcon(resourceMap.getIcon("searchButton.icon")); // NOI18N
        searchButton.setText(resourceMap.getString("searchButton.text")); // NOI18N
        searchButton.setToolTipText(resourceMap.getString("searchButton.toolTipText")); // NOI18N
        searchButton.setMinimumSize(new java.awt.Dimension(42, 42));
        searchButton.setName("searchButton"); // NOI18N
        searchButton.setPreferredSize(new java.awt.Dimension(42, 42));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        updateButton.setIcon(resourceMap.getIcon("updateButton.icon")); // NOI18N
        updateButton.setText(resourceMap.getString("updateButton.text")); // NOI18N
        updateButton.setToolTipText(resourceMap.getString("updateButton.toolTipText")); // NOI18N
        updateButton.setMinimumSize(new java.awt.Dimension(42, 42));
        updateButton.setName("updateButton"); // NOI18N
        updateButton.setPreferredSize(new java.awt.Dimension(42, 42));
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        refreshButton.setIcon(resourceMap.getIcon("refreshButton.icon")); // NOI18N
        refreshButton.setText(resourceMap.getString("refreshButton.text")); // NOI18N
        refreshButton.setToolTipText(resourceMap.getString("refreshButton.toolTipText")); // NOI18N
        refreshButton.setMinimumSize(new java.awt.Dimension(42, 42));
        refreshButton.setName("refreshButton"); // NOI18N
        refreshButton.setPreferredSize(new java.awt.Dimension(42, 42));
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        connectButton.setIcon(resourceMap.getIcon("connectButton.icon")); // NOI18N
        connectButton.setText(resourceMap.getString("connectButton.text")); // NOI18N
        connectButton.setToolTipText(resourceMap.getString("connectButton.toolTipText")); // NOI18N
        connectButton.setMinimumSize(new java.awt.Dimension(42, 42));
        connectButton.setName("connectButton"); // NOI18N
        connectButton.setPreferredSize(new java.awt.Dimension(42, 42));
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(getModel());
        jTable1.setDoubleBuffered(true);
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(connectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
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

        jMenu1.setMnemonic('V');
        jMenu1.setText(resourceMap.getString("viewMenu.text")); // NOI18N
        jMenu1.setName("viewMenu"); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem1.setIcon(resourceMap.getIcon("refreshMenuItem.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("refreshMenuItem.text")); // NOI18N
        jMenuItem1.setToolTipText(resourceMap.getString("refreshMenuItem.toolTipText")); // NOI18N
        jMenuItem1.setName("refreshMenuItem"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(resourceMap.getIcon("updateMenuItem.icon")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("updateMenuItem.text")); // NOI18N
        jMenuItem2.setToolTipText(resourceMap.getString("updateMenuItem.toolTipText")); // NOI18N
        jMenuItem2.setName("updateMenuItem"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu1.add(jSeparator2);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText(resourceMap.getString("jCheckBoxMenuItem1.text")); // NOI18N
        jCheckBoxMenuItem1.setToolTipText(resourceMap.getString("jCheckBoxMenuItem1.toolTipText")); // NOI18N
        jCheckBoxMenuItem1.setName("jCheckBoxMenuItem1"); // NOI18N
        jMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setText(resourceMap.getString("favoritesCheckBoxMenuItem.text")); // NOI18N
        jCheckBoxMenuItem2.setToolTipText(resourceMap.getString("favoritesCheckBoxMenuItem.toolTipText")); // NOI18N
        jCheckBoxMenuItem2.setEnabled(false);
        jCheckBoxMenuItem2.setName("favoritesCheckBoxMenuItem"); // NOI18N
        jMenu1.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText(resourceMap.getString("showEmptyCheckBoxMenuItem.text")); // NOI18N
        jCheckBoxMenuItem4.setToolTipText(resourceMap.getString("showEmptyCheckBoxMenuItem.toolTipText")); // NOI18N
        jCheckBoxMenuItem4.setName("showEmptyCheckBoxMenuItem"); // NOI18N
        jMenu1.add(jCheckBoxMenuItem4);

        jCheckBoxMenuItem5.setText(resourceMap.getString("showFullCheckBoxMenuItem.text")); // NOI18N
        jCheckBoxMenuItem5.setToolTipText(resourceMap.getString("showFullCheckBoxMenuItem.toolTipText")); // NOI18N
        jCheckBoxMenuItem5.setName("showFullCheckBoxMenuItem"); // NOI18N
        jMenu1.add(jCheckBoxMenuItem5);

        menuBar.add(jMenu1);

        jMenu2.setText(resourceMap.getString("optionsMenu.text")); // NOI18N
        jMenu2.setName("optionsMenu"); // NOI18N

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText(resourceMap.getString("sdlCheckBoxMenuItem.text")); // NOI18N
        jCheckBoxMenuItem3.setToolTipText(resourceMap.getString("sdlCheckBoxMenuItem.toolTipText")); // NOI18N
        jCheckBoxMenuItem3.setName("sdlCheckBoxMenuItem"); // NOI18N
        jMenu2.add(jCheckBoxMenuItem3);

        menuBar.add(jMenu2);

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem3.setIcon(resourceMap.getIcon("connectMenuItem.icon")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("connectMenuItem.text")); // NOI18N
        jMenuItem3.setToolTipText(resourceMap.getString("connectMenuItem.toolTipText")); // NOI18N
        jMenuItem3.setName("connectMenuItem"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setIcon(resourceMap.getIcon("viewPlayersMenuItem.icon")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("viewPlayersMenuItem.text")); // NOI18N
        jMenuItem4.setToolTipText(resourceMap.getString("viewPlayersMenuItem.toolTipText")); // NOI18N
        jMenuItem4.setEnabled(false);
        jMenuItem4.setName("viewPlayersMenuItem"); // NOI18N
        jMenu3.add(jMenuItem4);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jMenu3.add(jSeparator3);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(resourceMap.getIcon("addFavoritesMenuItem.icon")); // NOI18N
        jMenuItem6.setText(resourceMap.getString("addFavoritesMenuItem.text")); // NOI18N
        jMenuItem6.setEnabled(false);
        jMenuItem6.setName("addFavoritesMenuItem"); // NOI18N
        jMenu3.add(jMenuItem6);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(resourceMap.getIcon("addPrivateServerMenuItem.icon")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("addPrivateServerMenuItem.text")); // NOI18N
        jMenuItem5.setToolTipText(resourceMap.getString("addPrivateServerMenuItem.toolTipText")); // NOI18N
        jMenuItem5.setEnabled(false);
        jMenuItem5.setName("addPrivateServerMenuItem"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        menuBar.add(jMenu3);

        helpMenu.setMnemonic('H');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel))
                .addGap(12, 12, 12))
        );

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Get server list from master server when clicked.
     * @param evt
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        update();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        update();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        connect();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        refresh();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * When searchTextField gains focus, highlight text in the box.
     * @param evt
     */
    private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusGained
        searchTextField.selectAll();
    }//GEN-LAST:event_searchTextFieldFocusGained

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        filter();
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        filter();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        connect();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * Creates a new "Add Private Server" dialog.
     * @todo Implement "Add Private Server"
     * @param evt
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (addPrivateServer == null) {
            JFrame mainFrame = TortillaApp.getApplication().getMainFrame();
            addPrivateServer = new TortillaAddPrivateServer();
            addPrivateServer.setLocationRelativeTo(mainFrame);
        }
        TortillaApp.getApplication().show(addPrivateServer);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton updateButton;
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
            launcher.setSdl(isUseSdl());
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
     * Filter serverList according to search term.
     * @todo Filter as each letter is typed,
     * and remove filters when search box is emptied.
     */
    public void filter() {
        String query = searchTextField.getText().toLowerCase();

        if (!query.equals("") && getModel().getRowCount() != 0) {
            for (int i = getModel().getRowCount() - 1; i >= 0; --i) {
                if (!getModel().getValueAt(i, 1).toString().toLowerCase().
                        contains(query)) {
                    getModel().removeRow(i);
                }
            }
        }
    }

    /**
     * Use TortillaQueryMaster to download new serverlist.
     */
    public void update() {
        statusMessageLabel.setText("Updating...");
        // set up table
        if (getModel().getColumnCount() != 5) {
            getModel().addColumn("Ping");
            getModel().addColumn("Server");
            getModel().addColumn("Players");
            getModel().addColumn("Max");
            getModel().addColumn("Map");
        }
        TableColumn column;
        for (int i = 0; i < 4; i++) {
            column = jTable1.getColumnModel().getColumn(i);
            if (i == 0 || i == 2 || i == 3) {
                column.setPreferredWidth(24); //numerical columns smaller

            }
        }
        for (int j = getModel().getRowCount() - 1; j >= 0; j--) {
            getModel().removeRow(j);
        }
        queryM.saveServerList();
        refresh();
        statusMessageLabel.setText("");
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
        TableColumn column;
        for (int i = 0; i < 4; i++) {
            column = jTable1.getColumnModel().getColumn(i);
            if (i == 0 || i == 2 || i == 3) {
                column.setPreferredWidth(24); //numerical columns smaller

            }
        }
        for (int j = getModel().getRowCount() - 1; j >= 0; j--) {
            getModel().removeRow(j);
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
                JOptionPane.showMessageDialog(null,
                        "Click update to begin...");
            } catch (IOException ex) {
                Logger.getLogger(TortillaView.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        if (serverList != null) {
            SwingWorker worker = new SwingWorker<ConcurrentHashMap<String, TortillaServer>, Void>() {

                @Override
                public ConcurrentHashMap<String, TortillaServer> doInBackground() {
                    final ConcurrentHashMap<String, TortillaServer> tempSL =
                            new ConcurrentHashMap<String, TortillaServer>();
                    for (final String ip : serverList) {
                        class ServerQuerier extends Thread {

                            @Override
                            public void run() {
                                TortillaServer server = queryS.getInfo(ip);
                                if (server != null) {
                                    tempSL.put(ip, queryS.getInfo(ip));
                                }
                            }
                        }
                        Thread querier = new ServerQuerier();
                        querier.start();
                    }
                    try {
                        if (!busyIconTimer.isRunning()) {
                            statusAnimationLabel.setIcon(busyIcons[0]);
                            busyIconIndex = 0;
                            busyIconTimer.start();
                        }
                        statusMessageLabel.setText("Refreshing...");
                        Thread.sleep(3000);
                        statusMessageLabel.setText("");
                        busyIconTimer.stop();
                        statusAnimationLabel.setIcon(idleIcon);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TortillaView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                    return tempSL;
                }

                @Override
                public void done() {
                    try {
                        statusMessageLabel.setText("");
                        serverMap = get();
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
                            if (isHideEmpty()) {
                                if (players == 0) {
                                    permission = false;
                                }
                            }
                            if (isHideFull()) {
                                if (players == maxplayers) {
                                    permission = false;
                                }
                            }
                            if (isHideHighPing()) {
                                if (ping > MAX_PING) {
                                    permission = false;
                                }
                            }
                            if (permission) {
                                getModel().addRow(
                                        new Object[]{ping, hostname,
                                            players, maxplayers, map
                                        });
                            }
                            count++;
                        }
                        statusMessageLabel.setText("");
                    } catch (InterruptedException ignore) {
                    } catch (java.util.concurrent.ExecutionException ex) {
                        Logger.getLogger(TortillaView.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                }
            };
            worker.execute();
        }
    }

    /**
     *
     * @return
     */
    public boolean isHideEmpty() {
        return jCheckBoxMenuItem4.getState();
    }

    /**
     *
     * @return
     */
    public boolean isUseSdl() {
        return jCheckBoxMenuItem3.getState();
    }

    /**
     *
     * @return
     */
    public boolean isHideFull() {
        return jCheckBoxMenuItem5.getState();
    }

    /**
     *
     * @return
     */
    public boolean isHideHighPing() {
        return jCheckBoxMenuItem1.getState();
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
            Class dataType = Object.class;
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
