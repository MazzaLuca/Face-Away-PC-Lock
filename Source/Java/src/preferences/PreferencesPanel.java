package preferences;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Main Panel, GUI for the setting parameters.
 * 
 * @author Luca Mazza
 * @version 8 May 2020
 */
public class PreferencesPanel extends javax.swing.JPanel {
    
    /**
     * Array with usernames.
     */
    String[] users = {"", "", "", ""};
    
    /**
     * Users directory path string.
     */
    private String dir = "./Dataset/";
    
    /**
     * Version label text.
     */
    private String version = "1.0";
    
    /**
     * Length of the countdown.
     */
    private int countdown;
    
    /**
     * Toggle for the log of the lock of the pc.
     */
    private boolean logLock;
    
    /**
     * Toggle for the log of the lock of the pc, when a stranger is seen.
     */
    private boolean logStranger;
    
    /**
     * Toggle for the login start of the script.
     */
    private boolean startAtLogin;
    
    /**
     * Creates new form PreferencesPanel.
     */
    public PreferencesPanel() {
        initComponents();
        deSerialize(new File("./Dataset/Users"));
        readCSV();
    }
    
    /**
     * Deletes a specified directory with the contained files and folders.
     * 
     * @param file Filename of the directory to delete.
     */
    public void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (!Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }
    
    /**
     * Serializes the 
     * 
     * @param handle 
     */
    public void serialize(File handle) {
        String path = handle.toString();
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.users);
            out.close();
            fileOut.close();
            System.out.println(this.users[0]);
        } catch (IOException i) {
            JOptionPane jop = new JOptionPane();
            jop.showOptionDialog(
                    null,
                    "Cannot write file " + handle,
                    "Error opening file",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, null, null);
        }
        validate();
    }

    
    /**
     * Gets the data of the users serialized in the 'users' file.
     * 
     * @param handle Serialization file
     */
    public void deSerialize(File handle) {
        String path = handle.toString();
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.users = Arrays.copyOf((String[])in.readObject(), 4);
            in.close();
            fileIn.close();
            if(!users[0].equals("")){
                this.addUser6.setText(users[0]);
                this.deleteLabel1.setForeground(new Color(0,153,255));
            }else{
                this.addUser6.setText("Add user");
                this.deleteLabel1.setForeground(new Color(238,238,238));
            }
            if(!users[1].equals("")){
                this.addUser2.setText(users[1]);
                this.deleteLabel2.setForeground(new Color(0,153,255));
            }else{
                this.addUser2.setText("Add user");
                this.deleteLabel2.setForeground(new Color(238,238,238));
            }
            
            if(!users[2].equals("")){
                this.addUser3.setText(users[2]);
                this.deleteLabel3.setForeground(new Color(0,153,255));
            }else{
                this.addUser3.setText("Add user");
                this.deleteLabel3.setForeground(new Color(238,238,238));
            }
            if(!users[3].equals("")){
                this.addUser4.setText(users[3]);
                this.deleteLabel4.setForeground(new Color(0,153,255));
            }else{
                this.addUser4.setText("Add user");
                this.deleteLabel4.setForeground(new Color(238,238,238));
            }
            validate();
        } catch (IOException | ClassNotFoundException i) {
//            JOptionPane jop = new JOptionPane();
//            jop.showOptionDialog(
//                    null,
//                    "Cannot open file " + handle ,
//                    "Error opening file",
//                    JOptionPane.DEFAULT_OPTION,
//                    JOptionPane.ERROR_MESSAGE,
//                    null, null, null);
        }
    }
    
    /**
     * Reads the file CSV 'settings' to remember past settings.
     */
    public void readCSV(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("./Settings/settings.csv"));
            for (String line : lines) {
                String val = line.split(",")[1];
                if(line.split(",")[0].equals("Countdown")){
                    this.countdown = Integer.parseInt(val);
                    this.TurnOffMinuteSpinner.setValue(this.countdown);
                }else if(line.split(",")[0].equals("logLock")){
                    this.logLock = Integer.parseInt(val)==1?true:false;
                    this.NotifyTurnOffCheck.setSelected(this.logLock);
                }else if(line.split(",")[0].equals("logStranger")){
                    this.logStranger = Integer.parseInt(val)==1?true:false;
                    this.NotifySomeoneSeenCheck.setSelected(this.logStranger);
//                }else if(line.split(",")[0].equals("startAtLogin")){
//                    this.startAtLogin = Integer.parseInt(val)==1?true:false;
//                    this.LaunchAtLoginCheckBox.setSelected(this.startAtLogin);
//
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PreferencesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Updates the file CSV 'settings' to update settings.
     */
    public void updateCSV(){
        try {
            List<List<String>> rows = Arrays.asList(
                    Arrays.asList("Countdown", "" + this.countdown),
                    Arrays.asList("logLock", this.logLock?"1":"0"),
                    Arrays.asList("logStranger", this.logStranger?"1":"0"),
                    Arrays.asList("startAtLogin", this.startAtLogin?"1":"0")
            );
            
            FileWriter csvWriter = new FileWriter("./Settings/settings.csv");
            
            for (List<String> rowData : rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
     
    /**
     * Adds an user to the array and serializes the new file containing its infos.
     */
    public void addUser(int index, String name){
        AddUserDialog addUser = new AddUserDialog(/*(JFrame)this.getParent()*/null ,true, this.dir);
        addUser.setUserName(name);
        addUser.setVisible(true);
        if(!addUser.isCancelled()){
            users[index] = addUser.getName().trim();
            serialize(new File(dir + "Users"));
            deSerialize(new File(dir + "Users"));
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
        userPanel2 = new javax.swing.JPanel();
        addUser2 = new javax.swing.JLabel();
        deleteLabel2 = new javax.swing.JLabel();
        userPanel3 = new javax.swing.JPanel();
        addUser3 = new javax.swing.JLabel();
        deleteLabel3 = new javax.swing.JLabel();
        userPanel4 = new javax.swing.JPanel();
        addUser4 = new javax.swing.JLabel();
        deleteLabel4 = new javax.swing.JLabel();
        userPanel5 = new javax.swing.JPanel();
        addUser6 = new javax.swing.JLabel();
        deleteLabel1 = new javax.swing.JLabel();
        GeneralPanel = new javax.swing.JPanel();
        GeneralLabel = new javax.swing.JLabel();
        TurnOffScreenLabel1 = new javax.swing.JLabel();
        TurnOffMinuteSpinner = new javax.swing.JSpinner();
        TurnOffScreenLabel2 = new javax.swing.JLabel();
        NotifyTurnOffCheck = new javax.swing.JCheckBox();
        AdvancedPanel = new javax.swing.JPanel();
        AdvancedLabel = new javax.swing.JLabel();
        NotifySomeoneSeenCheck = new javax.swing.JCheckBox();
        AboutPanel = new javax.swing.JPanel();
        AboutLabel = new javax.swing.JLabel();
        FaceLockLabel = new javax.swing.JLabel();
        VersionLabel = new javax.swing.JLabel();
        CopyrightLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ApplyButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(236, 236, 236));

        FacesPanel.setBackground(new java.awt.Color(236, 236, 236));

        FacesLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        FacesLabel.setText("Faces");

        userPanel2.setBackground(new java.awt.Color(236, 236, 236));
        userPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanel2MouseClicked(evt);
            }
        });

        addUser2.setForeground(new java.awt.Color(0, 153, 255));
        addUser2.setText("Add user");

        deleteLabel2.setForeground(new java.awt.Color(238, 238, 238));
        deleteLabel2.setText("x");
        deleteLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout userPanel2Layout = new javax.swing.GroupLayout(userPanel2);
        userPanel2.setLayout(userPanel2Layout);
        userPanel2Layout.setHorizontalGroup(
            userPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel2)
                .addContainerGap())
        );
        userPanel2Layout.setVerticalGroup(
            userPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addUser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        deleteLabel3.setForeground(new java.awt.Color(238, 238, 238));
        deleteLabel3.setText("x");
        deleteLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout userPanel3Layout = new javax.swing.GroupLayout(userPanel3);
        userPanel3.setLayout(userPanel3Layout);
        userPanel3Layout.setHorizontalGroup(
            userPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel3)
                .addContainerGap())
        );
        userPanel3Layout.setVerticalGroup(
            userPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel3Layout.createSequentialGroup()
                .addComponent(addUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(userPanel3Layout.createSequentialGroup()
                .addComponent(deleteLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        deleteLabel4.setForeground(new java.awt.Color(238, 238, 238));
        deleteLabel4.setText("x");
        deleteLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout userPanel4Layout = new javax.swing.GroupLayout(userPanel4);
        userPanel4.setLayout(userPanel4Layout);
        userPanel4Layout.setHorizontalGroup(
            userPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel4)
                .addContainerGap())
        );
        userPanel4Layout.setVerticalGroup(
            userPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(addUser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        userPanel5.setBackground(new java.awt.Color(236, 236, 236));
        userPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        userPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userPanel5MouseClicked(evt);
            }
        });

        addUser6.setForeground(new java.awt.Color(0, 153, 255));
        addUser6.setText("Add user");

        deleteLabel1.setForeground(new java.awt.Color(238, 238, 238));
        deleteLabel1.setText("x");
        deleteLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout userPanel5Layout = new javax.swing.GroupLayout(userPanel5);
        userPanel5.setLayout(userPanel5Layout);
        userPanel5Layout.setHorizontalGroup(
            userPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addUser6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel1)
                .addContainerGap())
        );
        userPanel5Layout.setVerticalGroup(
            userPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanel5Layout.createSequentialGroup()
                .addComponent(addUser6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(deleteLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout FacesPanelLayout = new javax.swing.GroupLayout(FacesPanel);
        FacesPanel.setLayout(FacesPanelLayout);
        FacesPanelLayout.setHorizontalGroup(
            FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FacesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacesPanelLayout.createSequentialGroup()
                        .addGroup(FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        FacesPanelLayout.setVerticalGroup(
            FacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FacesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        TurnOffMinuteSpinner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TurnOffMinuteSpinner.setFocusTraversalKeysEnabled(false);
        TurnOffMinuteSpinner.setFocusable(false);
        TurnOffMinuteSpinner.setOpaque(true);
        TurnOffMinuteSpinner.setRequestFocusEnabled(false);
        TurnOffMinuteSpinner.setVerifyInputWhenFocusTarget(false);
        TurnOffMinuteSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TurnOffMinuteSpinnerStateChanged(evt);
            }
        });

        TurnOffScreenLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        TurnOffScreenLabel2.setText("seconds I am away");

        NotifyTurnOffCheck.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        NotifyTurnOffCheck.setText("Log when the computer turns off");
        NotifyTurnOffCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifyTurnOffCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GeneralPanelLayout = new javax.swing.GroupLayout(GeneralPanel);
        GeneralPanel.setLayout(GeneralPanelLayout);
        GeneralPanelLayout.setHorizontalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GeneralPanelLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(GeneralLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                        .addGroup(GeneralPanelLayout.createSequentialGroup()
                            .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(GeneralPanelLayout.createSequentialGroup()
                                    .addComponent(TurnOffScreenLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TurnOffMinuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TurnOffScreenLabel2))
                                .addComponent(NotifyTurnOffCheck))
                            .addGap(0, 192, Short.MAX_VALUE)))
                    .addGap(3, 3, 3)))
        );
        GeneralPanelLayout.setVerticalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
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
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        AdvancedPanel.setBackground(new java.awt.Color(236, 236, 236));

        AdvancedLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        AdvancedLabel.setText("Advanced");

        NotifySomeoneSeenCheck.setFont(new java.awt.Font("Helvetica Neue", 0, 13)); // NOI18N
        NotifySomeoneSeenCheck.setText("Log when someone is seen using your computer");
        NotifySomeoneSeenCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotifySomeoneSeenCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdvancedPanelLayout = new javax.swing.GroupLayout(AdvancedPanel);
        AdvancedPanel.setLayout(AdvancedPanelLayout);
        AdvancedPanelLayout.setHorizontalGroup(
            AdvancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdvancedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdvancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdvancedPanelLayout.createSequentialGroup()
                        .addComponent(AdvancedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addGap(333, 333, 333))
                    .addGroup(AdvancedPanelLayout.createSequentialGroup()
                        .addComponent(NotifySomeoneSeenCheck)
                        .addGap(0, 0, Short.MAX_VALUE))))
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

        jPanel1.setBackground(new java.awt.Color(236, 236, 236));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        ApplyButton.setText("Apply");
        ApplyButton.setToolTipText("Apply changes");
        ApplyButton.setEnabled(false);
        ApplyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplyButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ApplyButton);

        javax.swing.GroupLayout AboutPanelLayout = new javax.swing.GroupLayout(AboutPanel);
        AboutPanel.setLayout(AboutPanelLayout);
        AboutPanelLayout.setHorizontalGroup(
            AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AboutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(FaceLockLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(VersionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CopyrightLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AboutPanelLayout.setVerticalGroup(
            AboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AboutPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AboutLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FaceLockLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VersionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CopyrightLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(FacesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(GeneralPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdvancedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AboutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Event thrown when clicked on the Log turn off checkbox. Activates the 
     * 'apply' button.
     * 
     * @param evt ActionEvent event.
     */
    private void NotifyTurnOffCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifyTurnOffCheckActionPerformed
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_NotifyTurnOffCheckActionPerformed
    
    /**
     * Event thrown when clicked on the second's user box. Activates the 
     * 'apply' button and adds/edits an user.
     * 
     * @param evt MouseEvent event.
     */
    private void userPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel2MouseClicked
        if(this.users[1].equals("")){
            addUser(1, "");
        }else{
            addUser(1, users[1]);
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_userPanel2MouseClicked
    
    /**
     * Event thrown when clicked on the third's user box. Activates the 
     * 'apply' button and adds/edits an user.
     * 
     * @param evt MouseEvent event.
     */
    private void userPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel3MouseClicked
        if(this.users[2].equals("")){
            addUser(2, "");
        }else{
            addUser(2, users[2]);
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_userPanel3MouseClicked
    
    /**
     * Event thrown when clicked on the fourth's user box. Activates the 
     * 'apply' button and adds/edits an user.
     * 
     * @param evt MouseEvent event.
     */
    private void userPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel4MouseClicked
        if(this.users[3].equals("")){
            addUser(3, "");
        }else{
            addUser(3, users[3]);
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_userPanel4MouseClicked
    
    /**
     * Event thrown when clicked on the first's user box. Activates the 
     * 'apply' button and adds/edits an user.
     * 
     * @param evt MouseEvent event.
     */
    private void userPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userPanel5MouseClicked
        if(this.users[0].equals("")){
            addUser(0, "");
        }else{
            addUser(0, users[0]);
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_userPanel5MouseClicked
    
    /**
     * Event thrown when clicked on the first's user delete button. Activates the 
     * 'apply' button and deletes the user.
     * 
     * @param evt MouseEvent event.
     */
    private void deleteLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabel1MouseClicked
        if(!this.users[0].equals("")){
            Object[] options = { "Ok", "Cancel" };
            int response = JOptionPane.showOptionDialog(null,
                "Are you sure you want to delete this user", "Warning",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
            if(JOptionPane.YES_OPTION == response){
                String fileDir = this.dir + addUser6.getText();
                deleteDir(new File(fileDir));
                this.users[0] = "";
                serialize(new File(dir + "Users"));
                deSerialize(new File(dir + "Users"));
                
            }
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_deleteLabel1MouseClicked
    
    /**
     * Event thrown when clicked on the fourth's user delete button. Activates the 
     * 'apply' button and deletes the user.
     * 
     * @param evt MouseEvent event.
     */
    private void deleteLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabel4MouseClicked
        if(!this.users[3].equals("")){
            Object[] options = { "Ok", "Cancel" };
            int response = JOptionPane.showOptionDialog(null,
                "Are you sure you want to delete this user", "Warning",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
            if(JOptionPane.YES_OPTION == response){
                String fileDir = this.dir + addUser4.getText();
                deleteDir(new File(fileDir));
                this.users[3] = "";
                serialize(new File(dir + "Users"));
                deSerialize(new File(dir + "Users"));
            }
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_deleteLabel4MouseClicked
    
    /**
     * Event thrown when clicked on the second's user delete button. Activates the 
     * 'apply' button and deletes the user.
     * 
     * @param evt MouseEvent event.
     */
    private void deleteLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabel2MouseClicked
        if(!this.users[1].equals("")){
            Object[] options = { "Ok", "Cancel" };
            int response = JOptionPane.showOptionDialog(null,
                "Are you sure you want to delete this user", "Warning",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
            if(JOptionPane.YES_OPTION == response){
                String fileDir = this.dir + addUser2.getText();
                deleteDir(new File(fileDir));
                this.users[1] = "";
                serialize(new File(dir + "Users"));
                deSerialize(new File(dir + "Users"));
            }
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_deleteLabel2MouseClicked
    
    /**
     * Event thrown when clicked on the third's user delete button. Activates the 
     * 'apply' button and deletes the user.
     * 
     * @param evt MouseEvent event. 
     */
    private void deleteLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteLabel3MouseClicked
        if(!this.users[2].equals("")){
            Object[] options = { "Ok", "Cancel" };
            int response = JOptionPane.showOptionDialog(null,
                "Are you sure you want to delete this user", "Warning",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[0]);
            if(JOptionPane.YES_OPTION == response){
                String fileDir = this.dir + addUser3.getText();
                deleteDir(new File(fileDir));
                this.users[2] = "";
                serialize(new File(dir + "Users"));
                deSerialize(new File(dir + "Users"));
            }
        }
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_deleteLabel3MouseClicked
    
    /**
     * Event thrown when the 'Apply' button is clicked.
     * It updates the 'Settings' parameters and updates the CSV.
     * 
     * @param evt ActionEvent event.
     */
    private void ApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplyButtonActionPerformed
        this.countdown = (int)this.TurnOffMinuteSpinner.getValue();
        this.logLock = this.NotifyTurnOffCheck.isSelected();
        this.logStranger = this.NotifySomeoneSeenCheck.isSelected();
        updateCSV();
        this.ApplyButton.setEnabled(false);
    }//GEN-LAST:event_ApplyButtonActionPerformed
    
    /**
     * Event thrown when clicked on the countdown spinner. Activates the 
     * 'apply' button.
     * 
     * @param evt ActionEvent event.
     */
    private void TurnOffMinuteSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TurnOffMinuteSpinnerStateChanged
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_TurnOffMinuteSpinnerStateChanged
       
    /**
     * Event thrown when clicked on the Log turn off checkbox. Activates the 
     * 'apply' button.
     * 
     * @param evt ActionEvent event.
     */
    private void NotifySomeoneSeenCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotifySomeoneSeenCheckActionPerformed
        this.ApplyButton.setEnabled(true);
    }//GEN-LAST:event_NotifySomeoneSeenCheckActionPerformed
    
// private void LaunchAtLoginCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                      
//        String os = System.getProperty("os.name").toLowerCase();
//        this.ApplyButton.setEnabled(true);
//        if(LaunchAtLoginCheckBox.isSelected()){
//            if (os.indexOf("mac") >= 0) {
//                try {
//                    String[] command = {"bash", "startatlogin.sh"};
//                    ProcessBuilder processBuilder = new ProcessBuilder(command);
//                    Process process = processBuilder.start();
//                    this.startAtLogin = true;
//                } catch (IOException ex) {
//                    
//                }
//            }
//        }else{
//            if (os.indexOf("mac") >= 0) {
//                try {
//                    String[] command = {"./nostartatlogin.sh"};
//                    ProcessBuilder processBuilder = new ProcessBuilder(command);
//                    Process process = processBuilder.start();
//                    this.startAtLogin = true;
//                } catch (IOException ex) {
//                    
//                }
//            }
//        }
//    }     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AboutLabel;
    private javax.swing.JPanel AboutPanel;
    private javax.swing.JLabel AdvancedLabel;
    private javax.swing.JPanel AdvancedPanel;
    private javax.swing.JButton ApplyButton;
    private javax.swing.JLabel CopyrightLabel;
    private javax.swing.JLabel FaceLockLabel;
    private javax.swing.JLabel FacesLabel;
    private javax.swing.JPanel FacesPanel;
    private javax.swing.JLabel GeneralLabel;
    private javax.swing.JPanel GeneralPanel;
    private javax.swing.JCheckBox NotifySomeoneSeenCheck;
    private javax.swing.JCheckBox NotifyTurnOffCheck;
    private javax.swing.JSpinner TurnOffMinuteSpinner;
    private javax.swing.JLabel TurnOffScreenLabel1;
    private javax.swing.JLabel TurnOffScreenLabel2;
    private javax.swing.JLabel VersionLabel;
    private javax.swing.JLabel addUser2;
    private javax.swing.JLabel addUser3;
    private javax.swing.JLabel addUser4;
    private javax.swing.JLabel addUser6;
    private javax.swing.JLabel deleteLabel1;
    private javax.swing.JLabel deleteLabel2;
    private javax.swing.JLabel deleteLabel3;
    private javax.swing.JLabel deleteLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel userPanel2;
    private javax.swing.JPanel userPanel3;
    private javax.swing.JPanel userPanel4;
    private javax.swing.JPanel userPanel5;
    // End of variables declaration//GEN-END:variables
}
