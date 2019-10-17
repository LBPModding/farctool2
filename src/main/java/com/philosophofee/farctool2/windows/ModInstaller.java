/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.philosophofee.farctool2.windows;

import com.philosophofee.farctool2.utilities.FarUtils;
import com.philosophofee.farctool2.utilities.MiscUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aidan
 */
public class ModInstaller extends javax.swing.JFrame {

    /**
     * Creates new form ModInstaller
     */
    public String directory;
    public Document mod;
    public MainWindow window;
    public File[] selectedFARCs;

    public ModInstaller(Document mod, String directory, File[] selectedFARCs, MainWindow window) throws IOException {
        initComponents();
        Title.setText(mod.getDocumentElement().getAttribute("name"));
        Description.setText("<html>" + mod.getDocumentElement().getAttribute("description") + "</html>");
        this.selectedFARCs = selectedFARCs;
        this.directory = directory;
        this.mod = mod;
        this.window = window;
        setIconImage(new ImageIcon(getClass().getResource("resources/farctool2_icon.png")).getImage());

        try {
            BufferedImage image = ImageIO.read(new File(directory + "/" + mod.getDocumentElement().getAttribute("icon")));
            Icon.setIcon(MiscUtils.getScaledIcon(image));
        } catch (IOException ex) {
            Logger.getLogger(ModInstaller.class.getName()).log(Level.SEVERE, null, ex);
        }

        setVisible(true);
        setTitle("Mod Installer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    private ModInstaller() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        Background = new javax.swing.JLabel();
        Icon = new javax.swing.JLabel();
        Install = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        Description = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
                jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 95, Short.MAX_VALUE)
        );
        jLayeredPane2Layout.setVerticalGroup(
                jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 147, Short.MAX_VALUE)
        );

        jLayeredPane1.add(jLayeredPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 84, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/philosophofee/farctool2/windows/resources/news_fill.tex.png"))); // NOI18N
        jLayeredPane1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 330));
        jLayeredPane1.setLayer(Icon, 2);
        jLayeredPane1.add(Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 96, 116, 116));

        Install.setBackground(new java.awt.Color(0, 0, 0));
        Install.setForeground(new java.awt.Color(255, 255, 255));
        Install.setText("Install");
        Install.setBorderPainted(false);
        Install.setContentAreaFilled(false);
        Install.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstallActionPerformed(evt);
            }
        });
        jLayeredPane1.setLayer(Install, 5);
        jLayeredPane1.add(Install, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 246, -1, 33));

        Title.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setText("Sample Mod");
        jLayeredPane1.setLayer(Title, 5);
        jLayeredPane1.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 76, 310, 40));

        Description.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        Description.setForeground(new java.awt.Color(255, 255, 255));
        Description.setText("<html>This is a test description, it is designed to test out the new layout.</html>");
        Description.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLayeredPane1.setLayer(Description, 5);
        jLayeredPane1.add(Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 131, 310, 110));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InstallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstallActionPerformed
        Install.setEnabled(false);
        try {
            System.out.println("Installing Mod \"" + mod.getDocumentElement().getAttribute("name") + "\"...");

            NodeList fileList = mod.getElementsByTagName("file");

            for (int i = 0; i < fileList.getLength(); i++) {

                Node file = fileList.item(i);

                if (file.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) file;

                    File newFile = new File(directory + element.getElementsByTagName("path").item(0).getTextContent());
                    FileInputStream fis = new FileInputStream(newFile);
                    byte[] newFileData = fis.readAllBytes();
                    System.out.println("Adding File... " + newFile.getName());

                    byte[] SHA1 = MiscUtils.getSHA1(newFile);
                    long Size = newFile.length();
                    String GUID = element.getAttribute("guid");
                    String Path = element.getElementsByTagName("map").item(0).getTextContent();

                    if (MiscUtils.findGUIDOffset(GUID, window.MAP) != -1)
                        MiscUtils.replaceEntryByGUID(GUID, Path, Integer.toString((int) Size), MiscUtils.byteArrayToHexString(SHA1), window);
                    else
                        MiscUtils.addEntry(Path, MiscUtils.byteArrayToHexString(SHA1), Integer.toString((int) Size), GUID, window);

                    FarUtils.addFile(newFile, selectedFARCs);

                    ((DefaultTreeModel) window.mapTree.getModel()).reload((DefaultMutableTreeNode) window.mapTree.getModel().getRoot());
                    window.mapTree.updateUI();

                }
            }
            window.showUserDialog("Success!", "Mod has successfully been installed!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModInstaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModInstaller.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_InstallActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModInstaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModInstaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModInstaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModInstaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>



        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModInstaller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Description;
    private javax.swing.JLabel Icon;
    private javax.swing.JButton Install;
    private javax.swing.JLabel Title;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    // End of variables declaration//GEN-END:variables
}
