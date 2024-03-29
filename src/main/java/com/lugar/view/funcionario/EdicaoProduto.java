/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.lugar.view.funcionario;

import com.lugar.confeitaria.Util;
import com.lugar.controller.Conexao;
import com.lugar.controller.OperacoesProdutoPronto;
import com.lugar.controller.OperacoesProduto;
import com.lugar.model.ProdutoPronto;
import javax.swing.JOptionPane;

/**
 *
 * @author lugar
 */
public class EdicaoProduto extends javax.swing.JDialog {

    private static final int INVALIDO = -1;
    private static final int MUDANCA_APENAS_ESTOQUE = 1;
    private static final int MUDANCA_NOME_OU_VALOR = 2;

    private int id;
    private ProdutoPronto estadoAnterior;
    private java.awt.Frame pai;
    private OperacoesProdutoPronto operacoesProdutoPronto;
    private OperacoesProduto operacoesProduto;

    public EdicaoProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.pai = parent;
        initComponents();
    }

    public EdicaoProduto(java.awt.Frame parent, boolean modal, int id) {
        super(parent, modal);
        this.id = id;
        this.pai = parent;
        this.operacoesProdutoPronto = new OperacoesProdutoPronto();
        this.operacoesProduto = new OperacoesProduto();
        OperacoesProdutoPronto novoProdutoPronto = new OperacoesProdutoPronto();
        ProdutoPronto produto = novoProdutoPronto.busca(id);
        this.estadoAnterior = produto;
        initComponents();
        campoNome.setText(produto.getNome());
        campoValor.setValue(produto.getValor());
    }

    private int validaCampos(String nomeForm, double valorForm, int estoqueForm) {
        boolean valido = !nomeForm.isBlank() && valorForm > 0 && estoqueForm >= 0;
        boolean mudancaNomeOuValor = valido && (!nomeForm.equals(this.estadoAnterior.getNome())
                || valorForm != this.estadoAnterior.getValor());
        if (valido) {
            if (mudancaNomeOuValor) {
                return MUDANCA_NOME_OU_VALOR;
            } else if (estoqueForm != this.estadoAnterior.getEstoque()) {
                return MUDANCA_APENAS_ESTOQUE;
            }
        }
        return INVALIDO;
    }

    private void editaProduto() {
        String nomeForm = campoNome.getText().trim();
        double valorForm = (double) campoValor.getValue();
        int estoqueForm = (int) campoEstoque.getValue();

        int validacao = this.validaCampos(nomeForm, valorForm, estoqueForm);
        if (validacao != INVALIDO) {
            boolean confirmacao = JOptionPane.showConfirmDialog(null,
                    "Deseja editar este produto?",
                    "Edição de Produto", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE) == 0;

            if (confirmacao) {
                int resultado = Util.RETORNO_SUCESSO;
                if (validacao == MUDANCA_APENAS_ESTOQUE) {
                    resultado = this.operacoesProdutoPronto.atualizaEstoque(id, estoqueForm);
                } else {
                    ProdutoPronto produtoEditado = new ProdutoPronto(id, nomeForm, valorForm, estoqueForm);
                    resultado = this.operacoesProdutoPronto.atualiza(produtoEditado);
                }
                if (resultado == Util.RETORNO_SUCESSO) {
                    JOptionPane.showMessageDialog(this.pai, "Edição realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else if (resultado == Util.RETORNO_ERRO_NAO_UNICO) {
                    JOptionPane.showMessageDialog(this.pai, "Não foi possível realizar a edição, pois já existe um produto com este nome!", "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this.pai, "Não foi possível realizar a edição! Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deletaProduto() {
        boolean confirmacao = JOptionPane.showConfirmDialog(null,
                "Deseja deletar este produto permanentemente?",
                "Deleção de Produto", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) == 0;

        if (confirmacao) {
            int resultado = this.operacoesProduto.deleta(this.id);
            if (resultado == Util.RETORNO_SUCESSO) {
                JOptionPane.showMessageDialog(this.pai, "Deleção realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this.pai, "Não foi possível realizar a deleção! Tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        painelFormulario = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        painelCampos = new javax.swing.JPanel();
        textoNome = new javax.swing.JLabel();
        campoNome = new javax.swing.JFormattedTextField();
        textoValor = new javax.swing.JLabel();
        textoEstoque = new javax.swing.JLabel();
        campoEstoque = new javax.swing.JSpinner();
        campoValor = new javax.swing.JSpinner();
        painelBotoes = new javax.swing.JPanel();
        botaoDeletar = new javax.swing.JButton();
        botaoEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edição do Produto");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        painelFormulario.setLayout(new java.awt.GridBagLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Editar Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelFormulario.add(titulo, gridBagConstraints);

        painelCampos.setLayout(new java.awt.GridBagLayout());

        textoNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoNome, gridBagConstraints);

        campoNome.setPreferredSize(new java.awt.Dimension(200, 22));
        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoNome, gridBagConstraints);

        textoValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoValor.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoValor, gridBagConstraints);

        textoEstoque.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoEstoque.setText("Estoque:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        painelCampos.add(textoEstoque, gridBagConstraints);

        campoEstoque.setModel(new javax.swing.SpinnerNumberModel(this.estadoAnterior.getEstoque(), 0, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        painelCampos.add(campoEstoque, gridBagConstraints);

        campoValor.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(campoValor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelFormulario.add(painelCampos, gridBagConstraints);

        painelBotoes.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        botaoDeletar.setText("Deletar");
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });
        painelBotoes.add(botaoDeletar);

        botaoEditar.setText("Editar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });
        painelBotoes.add(botaoEditar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        painelFormulario.add(painelBotoes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(painelFormulario, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarActionPerformed
        this.editaProduto();
    }//GEN-LAST:event_botaoEditarActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        this.deletaProduto();
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

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
            java.util.logging.Logger.getLogger(EdicaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EdicaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EdicaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EdicaoProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EdicaoProduto dialog = new EdicaoProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JSpinner campoEstoque;
    private javax.swing.JFormattedTextField campoNome;
    private javax.swing.JSpinner campoValor;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel painelFormulario;
    private javax.swing.JLabel textoEstoque;
    private javax.swing.JLabel textoNome;
    private javax.swing.JLabel textoValor;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
