package br.com.techsantanna.meioambienteemjogo.model;

import java.io.Serializable;

public class Perguntas implements Serializable{

    private String pergunta, respostaa, respostab, respostac, respostad, respostaCerta;

    public Perguntas() {
    }

    public Perguntas(String pergunta, String respostaa, String respostab, String respostac, String respostad, String respostaCerta) {
        this.pergunta = pergunta;
        this.respostaa = respostaa;
        this.respostab = respostab;
        this.respostac = respostac;
        this.respostad = respostad;
        this.respostaCerta = respostaCerta;
    }

    @Override
    public String toString() {
        return "Perguntas{" +
                "pergunta='" + pergunta + '\'' +
                ", respostaa='" + respostaa + '\'' +
                ", respostab='" + respostab + '\'' +
                ", respostac='" + respostac + '\'' +
                ", respostad='" + respostad + '\'' +
                ", respostaCerta='" + respostaCerta + '\'' +
                '}';
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getRespostaa() {
        return respostaa;
    }

    public void setRespostaa(String respostaa) {
        this.respostaa = respostaa;
    }

    public String getRespostab() {
        return respostab;
    }

    public void setRespostab(String respostab) {
        this.respostab = respostab;
    }

    public String getRespostac() {
        return respostac;
    }

    public void setRespostac(String respostac) {
        this.respostac = respostac;
    }

    public String getRespostad() {
        return respostad;
    }

    public void setRespostad(String respostad) {
        this.respostad = respostad;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(String respostaCerta) {
        this.respostaCerta = respostaCerta;
    }
}
