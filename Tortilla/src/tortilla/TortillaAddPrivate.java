/*
 * TortillaAddPrivate.java
 *
 * Created on May 26, 2008, 6:03 PM
 */
package tortilla;

import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author  david
 */
public class TortillaAddPrivate extends javax.swing.JDialog {

    private static final long serialVersionUID = 5877677525126224471L;

    /** Creates new form TortillaAddPrivate
     * @param parent
     * @param modal
     */
    public TortillaAddPrivate(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addressField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getResourceMap(TortillaAddPrivate.class);
        addressField.setText(resourceMap.getString("addressField.text")); // NOI18N
        addressField.setName("addressField"); // NOI18N
        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });
        addressField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressFieldFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addressField, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setName("jPanel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(tortilla.TortillaApp.class).getContext().getActionMap(TortillaAddPrivate.class, this);
        addButton.setAction(actionMap.get("cancel")); // NOI18N
        addButton.setName("addButton"); // NOI18N

        cancelButton.setAction(actionMap.get("add")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addressFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressFieldFocusGained
        addressField.selectAll();
    }//GEN-LAST:event_addressFieldFocusGained

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        add();
    }//GEN-LAST:event_addressFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TortillaAddPrivate dialog = new TortillaAddPrivate(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @Action
    public void cancel() {
        this.setVisible(false);
    }

    /**
     * Reads in config.cfg, adds private server if it does not already exist,
     * then writes back config.cfg.
     */
    @Action
    public void add() {
        String operatingSystem = System.getProperty("os.name");

        Vector<String> configText = new Vector<String>();
        File configFile;
        try {
            if (operatingSystem.contains("Linux")) {
                configFile = new File(System.getProperty("user.home") + "/.nexuiz/data/config.cfg");
            } else {
                configFile = new File(System.getProperty("user.dir") + "\\data\\config.cfg");
            }
            Scanner scanner = new Scanner(configFile);
            while (scanner.hasNextLine()) {
                configText.add(scanner.nextLine());
            }
            boolean favLineFound = false;
            for (String line : configText) {
                if (line.contains("net_slist_favorites")) {
                    favLineFound = true;
                    if (line.contains(addressField.getText())) {
                        int choice = JOptionPane.showConfirmDialog(null, "Remove server from favorites?");
                        if (choice == JOptionPane.YES_OPTION) {
                            configText.remove(line);
                            configText.add(line.replaceAll(addressField.getText(), ""));
                        }
                        break;
                    } else {
                        configText.remove(line);
                        configText.add(line.substring(0, line.length() - 1) + " " + addressField.getText() + "\"");
                        break;
                    }
                }
            }
            if (!favLineFound) {
                configText.add("net_slist_favorites \"" + addressField.getText() + "\"");
            }
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(configFile));
                for (String line : configText) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Could not write to config file");
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Could not write to config file");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new Frame(),
                    "Could not find config file");
        }
        this.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the addressField
     */
    public String getAddressField() {
        return addressField.getText();
    }

    /**
     * @param addressField the addressField to set
     */
    public void setAddressField(String addressField) {
        this.addressField.setText(addressField);
    }
}
