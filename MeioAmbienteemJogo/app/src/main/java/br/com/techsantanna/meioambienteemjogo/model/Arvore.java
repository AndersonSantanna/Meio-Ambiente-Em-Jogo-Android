package br.com.techsantanna.meioambienteemjogo.model;

public class Arvore {
    protected String nome;
    private int xp, nivel;
    private int qtdAdubar, qtdReagar, qtdPodar, qtdAntiPragas;

    public Arvore(String nome, int xp, int nivel) {
        this.nome = nome;
        this.xp = xp;
        this.nivel = nivel;
    }

    public Arvore(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getQtdAdubar() {
        return qtdAdubar;
    }

    public void setQtdAdubar(int qtdAdubar) {
        this.qtdAdubar = qtdAdubar;
    }

    public int getQtdReagar() {
        return qtdReagar;
    }

    public void setQtdReagar(int qtdReagar) {
        this.qtdReagar = qtdReagar;
    }

    public int getQtdPodar() {
        return qtdPodar;
    }

    public void setQtdPodar(int qtdPodar) {
        this.qtdPodar = qtdPodar;
    }

    public int getQtdAntiPragas() {
        return qtdAntiPragas;
    }

    public void setQtdAntiPragas(int qtdAntiPragas) {
        this.qtdAntiPragas = qtdAntiPragas;
    }
}
