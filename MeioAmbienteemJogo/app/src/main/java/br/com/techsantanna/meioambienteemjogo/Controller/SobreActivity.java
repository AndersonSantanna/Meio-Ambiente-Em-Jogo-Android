package br.com.techsantanna.meioambienteemjogo.Controller;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.techsantanna.meioambienteemjogo.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class SobreActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sobre);
        String texto = "A Tech Sant'anna é uma empresa fictícia, criada por eu (Anderson Sant'Anna ), mas que um dia pode ser que exista para resolver seus problemas computacionais. Esse app foi desenvolvido com intuito de, tanto quanto conscientizar as pessoas sobre o meio ambiente, quanto um desafio profissional de conseguir desenvolver um software para desktop e android.\n " +
                "Um guia rápido: o objetivo aqui é cuidar da arvore e fazer ela crecer, com a ajuda de algumas ações, localizada na aera do jogo (Adubar, Regar, Detetizar e Podar), para receber mais ações, responda um quiz para testar seu conhecimento sobre meio ambiente, mais perguntas e jogos serão desenvolvidos, espero que goste! :)";
        View sobre = new AboutPage(this).setDescription(texto).addItem(new Element().setTitle("Versão 1.0"))
                .setImage(R.drawable.logotech)
                .addGroup("Fale Conosco")
                .addEmail("anderson.santanna14@gmail.com","E-mail")
                .addWebsite("https://techsantanna.000webhostapp.com", "Visite meu website")
                .addGroup("Rede Sociais")
                .addFacebook("anderson.pereirasantanna", "Me siga no Facebook")
                .addYoutube("UCiu9jG-t1-Auj3x1tYiQ2KQ?view_as=subscriber", "Me siga no Youtube")
                .addGitHub("AndersonSantanna", "Me siga no GitHub")
                .addInstagram("anderson.sant_anna", "Me siga no Instagram")
                .create();
        sobre.setBackgroundColor(R.color.about_background_color);
        setContentView(sobre);
    }
}
