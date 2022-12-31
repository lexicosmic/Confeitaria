/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lugar.controller;

import com.lugar.confeitaria.Util;
import static com.lugar.controller.Conexao.determinaValorErro;
import com.lugar.model.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lugar
 */
public class OperacoesTransacao implements OperacoesConexao<Transacao> {

    @Override
    public List<Transacao> buscaTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Transacao busca(int id) {
        String sql = "SELECT valor, descricao, diaHora FROM Transacao WHERE id=" + id + ";";
        Connection conn = null;
        Transacao transacaoAntiga = null;
        try {
            conn = Conexao.abreConexao();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                LocalDateTime dataHora = LocalDateTime.parse(rs.getString("diaHora"));
                transacaoAntiga = new Transacao(id, rs.getInt("valor"), dataHora, rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.fechaConexao(conn);
        }
        return transacaoAntiga;
    }

    @Override
    public int insere(Transacao transacao) {
        String sql = "INSERT INTO Transacao(valor, diaHora,descricao) VALUES(?,?,?);";
        Connection conn = null;
        int valorRetorno = Util.RETORNO_SUCESSO;
        try {
            conn = Conexao.abreConexao();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, transacao.getValor());
            pstmt.setString(2, transacao.getDiaHoraString());
            pstmt.setString(3, transacao.getDescricao());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName())
                    .log(Level.SEVERE, null, ex);
            valorRetorno = determinaValorErro(ex.getMessage());
        } finally {
            Conexao.fechaConexao(conn);
        }
        return valorRetorno;
    }

    @Override
    public int atualiza(Transacao objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
