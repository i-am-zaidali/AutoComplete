

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.logging.*;

/**
 *
 * @author zimp
 */
public class AutoCompleteGUI extends javax.swing.JFrame {

    private final Logger log = Logger.
            getLogger("AutoCompleteGUI");
    private AutoCompleteHandler handler;

    class AutoCompletionDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            this.update();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            this.update();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            this.update();
        }

        private void update() {
            var text = inputField.getText().strip();
            log.finer("inputField text: " + text);

            if (text.isBlank()) {
                log.finer("inputField is empty");
                ghostField.setText(text);
                return;
            }
            var split = text.split(" ");
            log.finer("Split inputField text: " + Arrays.toString(split));
            var lastWord = split[split.length - 1];
            log.finer("Last word: " + lastWord);
            var possibilities = handler.getSucceedingWords(lastWord, 2);
            log.finer("Possibilities: " + possibilities.toString());
            String words;
            try {
                words = possibilities.getFirst();
            } catch (NoSuchElementException e) {
                words = "";
            }
//            var lastSpace = text.lastIndexOf(" ")+1;
            ghostField.setText(text + words);
            log.finer("ghostField text at end: " + ghostField.getText());
        }
    }

    /**
     * Creates new form AutoComplete
     */
    public AutoCompleteGUI() {
        initComponents();
        log.addHandler(new ConsoleHandler());
        this.inputField.setBackground(new java.awt.Color(0, 0, 0, 0));
        this.inputField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.emptySet());
        FileReader reader;
        try {
            reader = new FileReader("/home/zimp/IdeaProjects/AutoComplete/src/duet data.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
            return;
        }
        this.handler = new AutoCompleteHandler(reader.getCompleteData());
        System.out.println(this.handler.getWords());
        System.out.println(reader.getCompleteData());
        this.inputField.getDocument().addDocumentListener(new AutoCompletionDocumentListener());
        System.out.println(ghostField.getText());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        inputField = new javax.swing.JTextField();
        ghostField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Monaspace Argon SemiWide SemiBold", 1, 24)); // NOI18N
        jLabel1.setText("AutoComplete Demo");

        inputField.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        inputField.setText("ghost");
        inputField.setAutoscrolls(false);
        inputField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputFieldKeyPressed(evt);
            }
        });

        ghostField.setEditable(false);
        ghostField.setBackground(new java.awt.Color(255, 255, 255));
        ghostField.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        ghostField.setForeground(new java.awt.Color(204, 204, 204));
        ghostField.setText("ghost text");
        ghostField.setAutoscrolls(false);
        ghostField.setFocusable(false);
        ghostField.setKeymap(null);

        jLayeredPane1.setLayer(inputField, javax.swing.JLayeredPane.POPUP_LAYER);
        jLayeredPane1.setLayer(ghostField, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ghostField, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(0, 54, Short.MAX_VALUE)
                    .addComponent(ghostField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 112, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            System.out.println("hehe");
            inputField.setText(ghostField.getText());
        }
    }//GEN-LAST:event_inputFieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(AutoCompleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutoCompleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutoCompleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutoCompleteGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutoCompleteGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ghostField;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables
}
