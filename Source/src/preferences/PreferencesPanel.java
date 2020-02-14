/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preferences;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucamazza
 */
public class PreferencesPanel extends javax.swing.JPanel {

    
    private String version = "0.1 Beta";
    
    private List<String> userNames;
    
    /**
     * Creates new form PreferencesPanel
     */
    public PreferencesPanel() {
        this.userNames = new ArrayList<>();
        initComponents();
        for (String userName : userNames) {
            javax.swing.JLabel userLabel = new javax.swing.JLabel(userName);
            userLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 20));
            this.add(userLabel);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FacesPanel = new javax.swing.JPanel();
        FacesLabel = new javax.swing.JLabel();
        userPanel1 = new javax.swing.JPanel();
        addUser1 = new javax.swing.JLabel();
        userPanel2 = new javax.swing.JPanel();
        addUser2 = new javax.swing.JLabel();
        userPanel3 = new javax.swing.JPanel();
        addUser3 = new javax.swing.JLabel();
        userPanel4 = new javax.swing.JPanel();
        addUser4 = new javax.swing.JLabel();
        GeneralPanel = new javax.swing.JPanel();
        GeneralLabel = new javax.swing.JLabel();
        TurnOffScreenLabel1 = new javax.swing.JLabel();
        TurnOffMinuteSpinner = new javax.swing.JSpinner();
        TurnOffScreenLabel2 = new javax.swing.JLabel();
        NotifyTurnOffCheck = new javax.swing.JCheckBox();
        ShortCutCheck = new javax.swing.JCheckBox();
        DarkModeCheck = new javax.swing.JCheckBox();
        ModifierCombo = new javax.swing.JComboBox<>();
        ShortcutLetter = new javax.swing.JTextField();
        AdvancedPanel = new javax.swing.JPanel();
        AdvancedLabel = new javax.swing.JLabel();
        NotifySomeoneSeenCheck = new javax.swing.JCheckBox();
        AboutPanel = new javax.swing.JPanel();
        AboutLabel = new javax.swing.JLabel();
        FaceLockLabel = new javax.swing.JLabel();
        VersionLabel = new javax.swing.JLabel();
        CopyrightLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(236, 236, 236));

        FacesPanel.setBackground(new java.awt.Color(236, 236, 236));

        FacesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        FacesLabel.setText("Faces");

        userPanel1.setBackground(new java.awt.Color(236, 236, 236));
        userPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanel1MouseClicked(evt);
            }
        });

        addUser1.setForeground(new java.awt.Color(0, 153, 255));
        addUser1.setText("Add user");

        javax.swing.GroupLayout userPanel1Layout = new javax.swing.GroupLayout(userPanel1);
        userPanel1.setLayout(userPanel1Layout);
        userPanel1Layout.setHorizontalGroup(
            userPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userPanel1Layout.setVerticalGroup(
            userPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addUser1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        userPanel2.setBackground(new java.awt.Color(236, 236, 236));
        userPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanel2MouseClicked(evt);
            }
        });

        addUser2.setForeground(new java.awt.Color(0, 153, 255));
        addUser2.setText("Add user");

        javax.swing.GroupLayout userPanel2Layout = new javax.swing.GroupLayout(userPanel2);
        userPanel2.setLayout(userPanel2Layout);
        userPanel2Layout.setHorizontalGroup(
            userPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userPanel2Layout.setVerticalGroup(
            userPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addUser2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        userPanel3.setBackground(new java.awt.Color(236, 236, 236));
        userPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanel3MouseClicked(evt);
            }
        });

        addUser3.setForeground(new java.awt.Color(0, 153, 255));
        addUser3.setText("Add user");

        javax.swing.GroupLayout userPanel3Layout = new javax.swing.GroupLayout(userPanel3);
        userPanel3.setLayout(userPanel3Layout);
        userPanel3Layout.setHorizontalGroup(
            userPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userPanel3Layout.setVerticalGroup(
            userPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addUser3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        userPanel4.setBackground(new java.awt.Color(236, 236, 236));
        userPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanel4MouseClicked(evt);
            }
        });

        addUser4.setForeground(new java.awt.Color(0, 153, 255));
        addUser4.setText("Add user");

        javax.swing.GroupLayout userPanel4Layout = new javax.swing.GroupLayout(userPanel4);
        userPanel4.setLayout(userPanel4Layout);
        userPanel4Layout.setHorizontalGroup(
            userPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userPanel4Layout.setVerticalGroup(
            userPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addUser4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout FacesPanelLayout = new javax.swing.GroupLayout(FacesPanel);
        FacesPanel.setLayout(FacesPanelLayout);
        FacesPanelLayout.setHorizontalGroup(
            FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FacesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacesPanelLayout.createSequentialGroup()
                        .addGroup(FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        FacesPanelLayout.setVerticalGroup(
            FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FacesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GeneralPanel.setBackground(new java.awt.Color(236, 236, 236));

        GeneralLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        GeneralLabel.setText("General");

        TurnOffScreenLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        TurnOffScreenLabel1.setText("Turn off screen after");

        TurnOffMinuteSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 30, 5));

        TurnOffScreenLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        TurnOffScreenLabel2.setText("minutes I am away");

        NotifyTurnOffCheck.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        NotifyTurnOffCheck.setText("Notify when the computer turns off");
        NotifyTurnOffCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifyTurnOffCheckActionPerformed(evt);
            }
        });

        ShortCutCheck.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        ShortCutCheck.setText("Shortcut");
        ShortCutCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShortCutCheckActionPerformed(evt);
            }
        });

        DarkModeCheck.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        DarkModeCheck.setText("Dark mode");
        DarkModeCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DarkModeCheckActionPerformed(evt);
            }
        });

        ModifierCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "⌘", "⌥", "⇧", "⌃" }));
        ModifierCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierComboActionPerformed(evt);
            }
        });

        ShortcutLetter.setToolTipText("");

        javax.swing.GroupLayout GeneralPanelLayout = new javax.swing.GroupLayout(GeneralPanel);
        GeneralPanel.setLayout(GeneralPanelLayout);
        GeneralPanelLayout.setHorizontalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GeneralPanelLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(GeneralLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                        .addGroup(GeneralPanelLayout.createSequentialGroup()
                            .addComponent(ShortCutCheck)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ModifierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ShortcutLetter, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(GeneralPanelLayout.createSequentialGroup()
                            .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(GeneralPanelLayout.createSequentialGroup()
                                    .addComponent(TurnOffScreenLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TurnOffMinuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TurnOffScreenLabel2))
                                .addComponent(NotifyTurnOffCheck)
                                .addComponent(DarkModeCheck))
                            .addGap(0, 151, Short.MAX_VALUE)))
                    .addGap(3, 3, 3)))
        );
        GeneralPanelLayout.setVerticalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
            .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GeneralPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(GeneralLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TurnOffScreenLabel1)
                        .addComponent(TurnOffMinuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TurnOffScreenLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(NotifyTurnOffCheck)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ShortCutCheck)
                        .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ModifierCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShortcutLetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(11, 11, 11)
                    .addComponent(DarkModeCheck)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        AdvancedPanel.setBackground(new java.awt.Color(236, 236, 236));

        AdvancedLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        AdvancedLabel.setText("Advanced");

        NotifySomeoneSeenCheck.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        NotifySomeoneSeenCheck.setText("Notify when someone is seen using your computer");

        javax.swing.GroupLayout AdvancedPanelLayout = new javax.swing.GroupLayout(AdvancedPanel);
        AdvancedPanel.setLayout(AdvancedPanelLayout);
        AdvancedPanelLayout.setHorizontalGroup(
            AdvancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdvancedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AdvancedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
            .addGroup(AdvancedPanelLayout.createSequentialGroup()
                .addComponent(NotifySomeoneSeenCheck)
                .addGap(6, 6, 6))
        );
        AdvancedPanelLayout.setVerticalGroup(
            AdvancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdvancedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AdvancedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NotifySomeoneSeenCheck)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AboutPanel.setBackground(new java.awt.Color(236, 236, 236));

        AboutLabel.setBackground(new java.awt.Color(236, 236, 236));
        AboutLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        AboutLabel.setText("About");

        FaceLockLabel.setBackground(new java.awt.Color(236, 236, 236));
        FaceLockLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        FaceLockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FaceLockLabel.setText("FaceLock");

        VersionLabel.setBackground(new java.awt.Color(236, 236, 236));
        VersionLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        VersionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VersionLabel.setText(this.version);

        CopyrightLabel.setBackground(new java.awt.Color(236, 236, 236));
        CopyrightLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        CopyrightLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CopyrightLabel.setText("Copyright LMBJ");

        javax.swing.GroupLayout AboutPanelLayout = new javax.swing.GroupLayout(AboutPanel);
        AboutPanel.setLayout(AboutPanelLayout);
        AboutPanelLayout.setHorizontalGroup(
            AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AboutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(FaceLockLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(VersionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CopyrightLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AboutPanelLayout.setVerticalGroup(
            AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AboutLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FaceLockLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VersionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CopyrightLabel)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FacesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(GeneralPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AdvancedPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(FacesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(GeneralPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdvancedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AboutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void NotifyTurnOffCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifyTurnOffCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NotifyTurnOffCheckActionPerformed

    private void ShortCutCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShortCutCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShortCutCheckActionPerformed

    private void DarkModeCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DarkModeCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DarkModeCheckActionPerformed

    private void ModifierComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModifierComboActionPerformed

    private void userPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel1MouseClicked
        AddUserDialog addUser = new AddUserDialog(null, true);
        addUser.setVisible(true);
    }//GEN-LAST:event_userPanel1MouseClicked

    private void userPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel2MouseClicked
        AddUserDialog addUser = new AddUserDialog(null, true);
        addUser.setVisible(true);
    }//GEN-LAST:event_userPanel2MouseClicked

    private void userPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel3MouseClicked
        AddUserDialog addUser = new AddUserDialog(null, true);
        addUser.setVisible(true);
    }//GEN-LAST:event_userPanel3MouseClicked

    private void userPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel4MouseClicked
        AddUserDialog addUser = new AddUserDialog(null, true);
        addUser.setVisible(true);
    }//GEN-LAST:event_userPanel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AboutLabel;
    private javax.swing.JPanel AboutPanel;
    private javax.swing.JLabel AdvancedLabel;
    private javax.swing.JPanel AdvancedPanel;
    private javax.swing.JLabel CopyrightLabel;
    private javax.swing.JCheckBox DarkModeCheck;
    private javax.swing.JLabel FaceLockLabel;
    private javax.swing.JLabel FacesLabel;
    private javax.swing.JPanel FacesPanel;
    private javax.swing.JLabel GeneralLabel;
    private javax.swing.JPanel GeneralPanel;
    private javax.swing.JComboBox<String> ModifierCombo;
    private javax.swing.JCheckBox NotifySomeoneSeenCheck;
    private javax.swing.JCheckBox NotifyTurnOffCheck;
    private javax.swing.JCheckBox ShortCutCheck;
    private javax.swing.JTextField ShortcutLetter;
    private javax.swing.JSpinner TurnOffMinuteSpinner;
    private javax.swing.JLabel TurnOffScreenLabel1;
    private javax.swing.JLabel TurnOffScreenLabel2;
    private javax.swing.JLabel VersionLabel;
    private javax.swing.JLabel addUser1;
    private javax.swing.JLabel addUser2;
    private javax.swing.JLabel addUser3;
    private javax.swing.JLabel addUser4;
    private javax.swing.JPanel userPanel1;
    private javax.swing.JPanel userPanel2;
    private javax.swing.JPanel userPanel3;
    private javax.swing.JPanel userPanel4;
    // End of variables declaration//GEN-END:variables
}