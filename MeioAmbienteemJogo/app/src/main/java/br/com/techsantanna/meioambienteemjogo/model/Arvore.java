package br.com.techsantanna.meioambienteemjogo.model;

import android.util.Base64;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.Random;

public class Arvore implements Serializable {
    protected String nome;
    private int xp, nivel, width = 217, height = 207;
    private int qtdAdubar, qtdRegar, qtdPodar, qtdAntiPragas;


    public Arvore(){

    }

    @Override
    public String toString() {
        return "Arvore{" +
                "nome='" + nome + '\'' +
                ", xp=" + xp +
                ", nivel=" + nivel +
                ", width=" + width +
                ", height=" + height +
                ", qtdAdubar=" + qtdAdubar +
                ", qtdReagar=" + qtdRegar +
                ", qtdPodar=" + qtdPodar +
                ", qtdAntiPragas=" + qtdAntiPragas +
                '}';
    }

    public String getNome() {
        return nome;
    }
    /**Verifica se o usuario passou de nivel a cada ação,
     *e se sim aumenta o width e height da imagem da arvore
     * */
    public boolean verificaNivel(ProgressBar progressBar, TextView textView){
        if (getXp() > 99){
            setXp(getXp() - 100);
            progressBar.setProgress(getXp());
            textView.setText(String.valueOf(progressBar.getProgress()).concat("%"));
            setNivel(getNivel() + 1);
            return true;
        }else {
            progressBar.setProgress(getXp());
            textView.setText(String.valueOf(progressBar.getProgress()).concat("%"));
            return false;
        }
    }
    /**
     * Ações relizadas no jogo
     * */
    public boolean adubar(ProgressBar progressBar, TextView textView){
        if(getQtdAdubar() > 0) {
            setXp(getXp() + 5);
            this.setQtdAdubar(getQtdAdubar() - 1);
        }
        return verificaNivel(progressBar, textView);
    }
    public boolean regar(ProgressBar progressBar, TextView textView){
        if (getQtdRegar() > 0 ) {
            setXp(getXp() + 7);
            this.setQtdRegar(getQtdRegar() - 1);
        }
        return verificaNivel(progressBar, textView);
    }
    public boolean detetizar(ProgressBar progressBar, TextView textView){
        if (getQtdAntiPragas() > 0) {
            int aux = new Random().nextInt(2);
            if (aux == 0 && getXp() > 2) {
                setXp(getXp() - 3);
            }else {
                setXp(getXp() + 3);
            }
            this.setQtdAntiPragas(getQtdAntiPragas() - 1);
        }
        return verificaNivel(progressBar,textView);
    }
    public boolean podar(ProgressBar progressBar, TextView textView){
        if (getQtdPodar() > 0) {
            setXp(getXp() + 4);
            this.setQtdPodar(getQtdPodar() - 1);
        }
        return verificaNivel(progressBar, textView);
    }


    /**
     * Get e set de todos os atributos
     * */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public int getQtdRegar() {
        return qtdRegar;
    }

    public void setQtdRegar(int qtdRegar) {
        this.qtdRegar = qtdRegar;
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
