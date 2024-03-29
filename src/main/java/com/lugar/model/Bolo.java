/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lugar.model;

import com.lugar.confeitaria.Util;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lugar
 */
public class Bolo extends ProdutoPersonalizado {

    private Forma forma;
    private Caracteristica cobertura;
    private List<Caracteristica> recheios;

    public Bolo(int id, String detalhe) {
        super(id, Util.RECEITA_BOLO, detalhe);
        this.recheios = new ArrayList<Caracteristica>();
    }

    public Bolo(int id, String detalhe, Caracteristica cor, Forma forma,
            Caracteristica cobertura, List<Caracteristica> recheios) {
        super(id, Util.RECEITA_BOLO, detalhe, cor);
        this.forma = forma;
        this.cobertura = cobertura;
        this.recheios = recheios;
    }

    public Forma getForma() {
        return forma;
    }

    public Caracteristica getCobertura() {
        return cobertura;
    }

    public List<Caracteristica> getRecheios() {
        return recheios;
    }

    public Caracteristica getRecheio(int indice) {
        return this.recheios.get(indice);
    }

    @Override
    public String getNome() {
        return "Bolo de " + this.getRecheio(0);
    }

    @Override
    public double getValor() {
        double valorFinal = 0;
        valorFinal += this.forma.getGramaCobertura() * this.getCobertura().getValorGrama();
        valorFinal += this.forma.getGramaMassa() * this.getForma().getValorGrama();
        valorFinal += this.getCor().getValorGrama();
        for (Caracteristica recheio : recheios) {
            valorFinal += this.forma.getGramaRecheio() * recheio.getValorGrama();
        }
        return valorFinal;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public void setCobertura(Caracteristica cobertura) {
        this.cobertura = cobertura;
    }

    public void addRecheio(Caracteristica recheio) {
        this.recheios.add(recheio);
    }

}
