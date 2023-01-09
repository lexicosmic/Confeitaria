/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.lugar.view;

import com.lugar.confeitaria.Util;
import com.lugar.model.Bolo;
import com.lugar.model.Caracteristica;
import com.lugar.model.Item;
import com.lugar.model.ProdutoPersonalizado;
import com.lugar.model.Trufa;
import java.util.List;

/**
 *
 * @author lugar
 */
public class VisualizacaoItemProdutoPersonalizado extends javax.swing.JDialog {

    private java.awt.Frame pai;

    public VisualizacaoItemProdutoPersonalizado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public VisualizacaoItemProdutoPersonalizado(java.awt.Frame parent, boolean modal, Item item) {
        super(parent, modal);
        this.pai = parent;
        ProdutoPersonalizado produtoPersonalizado = (ProdutoPersonalizado) item.getProduto();
        initComponents();
        textoNomePreenchido.setText(produtoPersonalizado.getNome());
        textoValorPreenchido.setText(Util.formataDinheiro(item.getValorTotal() / (double) item.getQuantidade()));
        textoQuantidadePreenchido.setText(String.valueOf(item.getQuantidade()));
        textoValorTotalPreenchido.setText(Util.formataDinheiro(item.getValorTotal()));
        if (produtoPersonalizado.getReceita().equals(Util.RECEITA_BOLO)) {
            this.painelCamposBolo.setVisible(true);
            this.painelCamposTrufa.setVisible(false);
            this.trocaExibicaoRecheios(((Bolo) produtoPersonalizado).getRecheios());
            textoFormaBoloPreenchido.setText(((Bolo) produtoPersonalizado).getForma().getNome());
            textoCorBoloPreenchido.setText(((Bolo) produtoPersonalizado).getCor().getNome());
            textoCoberturaBoloPreenchido.setText(((Bolo) produtoPersonalizado).getCobertura().getNome());
            areaTextoDetalheBolo.setText(((Bolo) produtoPersonalizado).getDetalhe());
        } else {
            this.painelCamposBolo.setVisible(false);
            this.painelCamposTrufa.setVisible(true);
            textoRecheioTrufaPreenchido.setText(((Trufa) produtoPersonalizado).getRecheio().getNome());
            textoCorTrufaPreenchido.setText(((Trufa) produtoPersonalizado).getCor().getNome());
            areaTextoDetalheTrufa.setText(((Trufa) produtoPersonalizado).getDetalhe());
        }
    }

    private void trocaExibicaoRecheios(List<Caracteristica> recheios) {
        this.textoRecheioBolo2Preenchido.setVisible(false);
        this.textoRecheioBolo3Preenchido.setVisible(false);
        textoRecheioBolo1Preenchido.setText(recheios.get(0).getNome());
        if (recheios.size() >= 2) {
            textoRecheioBolo2Preenchido.setText(recheios.get(1).getNome());
            this.textoRecheioBolo2Preenchido.setVisible(true);
        }
        if (recheios.size() >= 3) {
            textoRecheioBolo3Preenchido.setText(recheios.get(2).getNome());
            this.textoRecheioBolo3Preenchido.setVisible(true);
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
        textoNomePreenchido = new javax.swing.JLabel();
        textoValor = new javax.swing.JLabel();
        textoValorPreenchido = new javax.swing.JLabel();
        textoQuantidade = new javax.swing.JLabel();
        textoQuantidadePreenchido = new javax.swing.JLabel();
        textoValorTotal = new javax.swing.JLabel();
        textoValorTotalPreenchido = new javax.swing.JLabel();
        painelCamposBolo = new javax.swing.JPanel();
        textoFormaBolo = new javax.swing.JLabel();
        textoFormaBoloPreenchido = new javax.swing.JLabel();
        painelCamposRecheiosBolo = new javax.swing.JPanel();
        textoRecheioBolo1Preenchido = new javax.swing.JLabel();
        textoRecheioBolo2Preenchido = new javax.swing.JLabel();
        textoRecheioBolo3Preenchido = new javax.swing.JLabel();
        textoCoberturaBolo = new javax.swing.JLabel();
        textoCorBolo = new javax.swing.JLabel();
        textoDetalheBolo = new javax.swing.JLabel();
        painelAreaTextoDetalheBolo = new javax.swing.JScrollPane();
        areaTextoDetalheBolo = new javax.swing.JTextPane();
        textoCoberturaBoloPreenchido = new javax.swing.JLabel();
        textoCorBoloPreenchido = new javax.swing.JLabel();
        painelCamposTrufa = new javax.swing.JPanel();
        textoRecheioTrufa = new javax.swing.JLabel();
        textoRecheioTrufaPreenchido = new javax.swing.JLabel();
        textoCorTrufa = new javax.swing.JLabel();
        textoCorTrufaPreenchido = new javax.swing.JLabel();
        textoDetalheTrufa = new javax.swing.JLabel();
        painelAreaTextoDetalheTrufa = new javax.swing.JScrollPane();
        areaTextoDetalheTrufa = new javax.swing.JTextPane();
        painelBotoes = new javax.swing.JPanel();
        botaoEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edição da Transação");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        painelFormulario.setLayout(new java.awt.GridBagLayout());

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Item");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelFormulario.add(titulo, gridBagConstraints);

        painelCampos.setLayout(new java.awt.GridBagLayout());

        textoNome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNome.setText("Receita:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoNome, gridBagConstraints);

        textoNomePreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNomePreenchido.setText("Bolo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoNomePreenchido, gridBagConstraints);

        textoValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoValor.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoValor, gridBagConstraints);

        textoValorPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoValorPreenchido.setText("R$ 0,00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoValorPreenchido, gridBagConstraints);

        textoQuantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoQuantidade.setText("Quantidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoQuantidade, gridBagConstraints);

        textoQuantidadePreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoQuantidadePreenchido.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoQuantidadePreenchido, gridBagConstraints);

        textoValorTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoValorTotal.setText("Valor total:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoValorTotal, gridBagConstraints);

        textoValorTotalPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoValorTotalPreenchido.setText("R$ 1,11");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        painelCampos.add(textoValorTotalPreenchido, gridBagConstraints);

        painelCamposBolo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        painelCamposBolo.setLayout(new java.awt.GridBagLayout());

        textoFormaBolo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoFormaBolo.setText("Forma:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        painelCamposBolo.add(textoFormaBolo, gridBagConstraints);

        textoFormaBoloPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoFormaBoloPreenchido.setText("Redonda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        painelCamposBolo.add(textoFormaBoloPreenchido, gridBagConstraints);

        painelCamposRecheiosBolo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Recheios"));
        painelCamposRecheiosBolo.setLayout(new java.awt.GridBagLayout());

        textoRecheioBolo1Preenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoRecheioBolo1Preenchido.setText("R1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposRecheiosBolo.add(textoRecheioBolo1Preenchido, gridBagConstraints);

        textoRecheioBolo2Preenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoRecheioBolo2Preenchido.setText("R2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposRecheiosBolo.add(textoRecheioBolo2Preenchido, gridBagConstraints);

        textoRecheioBolo3Preenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoRecheioBolo3Preenchido.setText("R3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposRecheiosBolo.add(textoRecheioBolo3Preenchido, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 10);
        painelCamposBolo.add(painelCamposRecheiosBolo, gridBagConstraints);

        textoCoberturaBolo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCoberturaBolo.setText("Cobertura:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposBolo.add(textoCoberturaBolo, gridBagConstraints);

        textoCorBolo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCorBolo.setText("Cor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposBolo.add(textoCorBolo, gridBagConstraints);

        textoDetalheBolo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoDetalheBolo.setText("Detalhe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposBolo.add(textoDetalheBolo, gridBagConstraints);

        areaTextoDetalheBolo.setEnabled(false);
        areaTextoDetalheBolo.setMinimumSize(new java.awt.Dimension(62, 66));
        areaTextoDetalheBolo.setPreferredSize(new java.awt.Dimension(62, 66));
        painelAreaTextoDetalheBolo.setViewportView(areaTextoDetalheBolo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        painelCamposBolo.add(painelAreaTextoDetalheBolo, gridBagConstraints);

        textoCoberturaBoloPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCoberturaBoloPreenchido.setText("Glacê");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        painelCamposBolo.add(textoCoberturaBoloPreenchido, gridBagConstraints);

        textoCorBoloPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCorBoloPreenchido.setText("Amarelo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        painelCamposBolo.add(textoCorBoloPreenchido, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelCampos.add(painelCamposBolo, gridBagConstraints);

        painelCamposTrufa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        painelCamposTrufa.setLayout(new java.awt.GridBagLayout());

        textoRecheioTrufa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoRecheioTrufa.setText("Recheio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        painelCamposTrufa.add(textoRecheioTrufa, gridBagConstraints);

        textoRecheioTrufaPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoRecheioTrufaPreenchido.setText("RT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        painelCamposTrufa.add(textoRecheioTrufaPreenchido, gridBagConstraints);

        textoCorTrufa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCorTrufa.setText("Cor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposTrufa.add(textoCorTrufa, gridBagConstraints);

        textoCorTrufaPreenchido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoCorTrufaPreenchido.setText("Chocolate branco");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposTrufa.add(textoCorTrufaPreenchido, gridBagConstraints);

        textoDetalheTrufa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoDetalheTrufa.setText("Detalhe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        painelCamposTrufa.add(textoDetalheTrufa, gridBagConstraints);

        areaTextoDetalheTrufa.setEnabled(false);
        areaTextoDetalheTrufa.setMinimumSize(new java.awt.Dimension(62, 66));
        areaTextoDetalheTrufa.setPreferredSize(new java.awt.Dimension(62, 66));
        painelAreaTextoDetalheTrufa.setViewportView(areaTextoDetalheTrufa);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        painelCamposTrufa.add(painelAreaTextoDetalheTrufa, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelCampos.add(painelCamposTrufa, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        painelFormulario.add(painelCampos, gridBagConstraints);

        painelBotoes.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        botaoEditar.setText("Fechar");
        botaoEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarActionPerformed(evt);
            }
        });
        painelBotoes.add(botaoEditar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
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
        this.dispose();
    }//GEN-LAST:event_botaoEditarActionPerformed

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
            java.util.logging.Logger.getLogger(VisualizacaoItemProdutoPersonalizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizacaoItemProdutoPersonalizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizacaoItemProdutoPersonalizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizacaoItemProdutoPersonalizado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VisualizacaoItemProdutoPersonalizado dialog = new VisualizacaoItemProdutoPersonalizado(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextPane areaTextoDetalheBolo;
    private javax.swing.JTextPane areaTextoDetalheTrufa;
    private javax.swing.JButton botaoEditar;
    private javax.swing.JScrollPane painelAreaTextoDetalheBolo;
    private javax.swing.JScrollPane painelAreaTextoDetalheTrufa;
    private javax.swing.JPanel painelBotoes;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel painelCamposBolo;
    private javax.swing.JPanel painelCamposRecheiosBolo;
    private javax.swing.JPanel painelCamposTrufa;
    private javax.swing.JPanel painelFormulario;
    private javax.swing.JLabel textoCoberturaBolo;
    private javax.swing.JLabel textoCoberturaBoloPreenchido;
    private javax.swing.JLabel textoCorBolo;
    private javax.swing.JLabel textoCorBoloPreenchido;
    private javax.swing.JLabel textoCorTrufa;
    private javax.swing.JLabel textoCorTrufaPreenchido;
    private javax.swing.JLabel textoDetalheBolo;
    private javax.swing.JLabel textoDetalheTrufa;
    private javax.swing.JLabel textoFormaBolo;
    private javax.swing.JLabel textoFormaBoloPreenchido;
    private javax.swing.JLabel textoNome;
    private javax.swing.JLabel textoNomePreenchido;
    private javax.swing.JLabel textoQuantidade;
    private javax.swing.JLabel textoQuantidadePreenchido;
    private javax.swing.JLabel textoRecheioBolo1Preenchido;
    private javax.swing.JLabel textoRecheioBolo2Preenchido;
    private javax.swing.JLabel textoRecheioBolo3Preenchido;
    private javax.swing.JLabel textoRecheioTrufa;
    private javax.swing.JLabel textoRecheioTrufaPreenchido;
    private javax.swing.JLabel textoValor;
    private javax.swing.JLabel textoValorPreenchido;
    private javax.swing.JLabel textoValorTotal;
    private javax.swing.JLabel textoValorTotalPreenchido;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
