/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.lugar.confeitaria;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lugar
 */
public class Login extends javax.swing.JDialog {

    private ArrayList<Usuario> listaUsuarios;

    /**
     * Creates new form Login
     */
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Login(java.awt.Frame parent, boolean modal, ArrayList<Usuario> listaUsuariosP) {
        super(parent, modal);
        listaUsuarios = listaUsuariosP;
        initComponents();
    }

    private boolean verificaLogin(String usuario, char[] senha) {
        return usuario.compareTo("admin") == 0
                && String.valueOf(senha).compareTo("1234") == 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        painelFormulario = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JLabel();
        campoUsuario = new javax.swing.JFormattedTextField();
        textoSenha = new javax.swing.JLabel();
        campoSenha = new javax.swing.JPasswordField();
        painelBotoes = new javax.swing.JPanel();
        botaoLogin = new javax.swing.JButton();
        botaoCadastro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        painelFormulario.setLayout(new java.awt.GridBagLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("LUGAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelFormulario.add(titulo, gridBagConstraints);

        textoUsuario.setText("Usuário:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        painelFormulario.add(textoUsuario, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelFormulario.add(campoUsuario, gridBagConstraints);

        textoSenha.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        painelFormulario.add(textoSenha, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        painelFormulario.add(campoSenha, gridBagConstraints);

        painelBotoes.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        botaoLogin.setText("Enviar");
        botaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLoginActionPerformed(evt);
            }
        });
        painelBotoes.add(botaoLogin);

        botaoCadastro.setText("Registrar-se");
        botaoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastroActionPerformed(evt);
            }
        });
        painelBotoes.add(botaoCadastro);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelFormulario.add(painelBotoes, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLoginActionPerformed
        String usuarioForm = campoUsuario.getText();
        char[] senhaForm = campoSenha.getPassword();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNomeUsuario().compareTo(usuarioForm) == 0
                    && usuario.getSenhaHash().compareTo(String.valueOf(senhaForm)) == 0) {
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuário ou senha não correspondem");
    }//GEN-LAST:event_botaoLoginActionPerformed

    private void botaoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastroActionPerformed
        System.out.println("Registrar-se!");
    }//GEN-LAST:event_botaoCadastroActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCadastro;
    private javax.swing.JButton botaoLogin;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JFormattedTextField campoUsuario;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelFormulario;
    private javax.swing.JLabel textoSenha;
    private javax.swing.JLabel textoUsuario;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
