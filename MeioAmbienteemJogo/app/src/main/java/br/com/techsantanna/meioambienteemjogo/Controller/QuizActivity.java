package br.com.techsantanna.meioambienteemjogo.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.techsantanna.meioambienteemjogo.R;
import br.com.techsantanna.meioambienteemjogo.model.Perguntas;
import br.com.techsantanna.meioambienteemjogo.model.Usuario;

public class QuizActivity extends AppCompatActivity {
    private Perguntas perguntas = new Perguntas();
    private TextView question;
    private Button enviar;
    private RadioGroup radioGroup;
    private RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private Usuario usuario = new Usuario();
    final String[] answer = new String[0];
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        /** Referenciando aos id's* */
        question = findViewById(R.id.textViewPergunta);
        radioGroup = findViewById(R.id.radioGroup2);
        radioButtonA = findViewById(R.id.radioButtonA);
        radioButtonB = findViewById(R.id.radioButtonB);
        radioButtonC = findViewById(R.id.radioButtonC);
        radioButtonD = findViewById(R.id.radioButtonD);
        enviar = findViewById(R.id.buttonEnviar);

        /** puxando perguntas e respostas do banco */
        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("user");
        perguntas = (Perguntas) bundle.getSerializable("pergunta");

        /**Carregando quiz na Activity*/
        question.setText(perguntas.getPergunta());
        radioButtonA.setText(perguntas.getRespostaa());
        radioButtonB.setText(perguntas.getRespostab());
        radioButtonC.setText(perguntas.getRespostac());
        radioButtonD.setText(perguntas.getRespostad());


        /** verifica o radion button */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = group.findViewById(checkedId);
                string = button.getText().toString();
            }
        });
        /**Botão Enviar resposta*/
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (string.equals(perguntas.getRespostaCerta())) {
                        respostaCerta(usuario);
                    } else {
                        respostaErrada(usuario);
                    }
                }catch (NullPointerException e){
                    Toast.makeText(getApplicationContext(), "Selecione uma alternativa", Toast.LENGTH_LONG);
                }
            }
        });

    }
   /**Realiza sorteio do que o usuario ganhou, e manda um pop*/
    public void respostaCerta(final Usuario user){
        String list[] = {"regares","adubares","podadores","detetizadores"};
        int acao = new Random().nextInt(4);
        int sorteio = new Random().nextInt(10);
        sorteio++;

        switch (acao){
            case 0:
                usuario.getArvore().setQtdRegar(usuario.getArvore().getQtdRegar() + sorteio);
                break;
            case 1:
                usuario.getArvore().setQtdAdubar(usuario.getArvore().getQtdAdubar() + sorteio);

                break;
            case 2:
                usuario.getArvore().setQtdPodar(usuario.getArvore().getQtdPodar() + sorteio);
                break;
            case 3:
                usuario.getArvore().setQtdAntiPragas(usuario.getArvore().getQtdAntiPragas() + sorteio);
                break;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(QuizActivity.this, R.style.DialogStyle);
        alert.setTitle("Parabéns você acertou!");
        alert.setIcon(R.drawable.ic_sentiment_satisfied_black_24dp);
        alert.setMessage("Você ganhou " + sorteio+"x " + list[acao] + " por ter acertado a pergunta" );
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                DatabaseReference db = reference.child("usuario");
                db.child(Base64.encodeToString(user.getEmail().getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "")).setValue(user);

            }
        });
        alert.setCancelable(false);
        alert.create();
        alert.show();

    }
    /**Alerta que a reposta do usuario esta errada*/
    public void respostaErrada(final Usuario user){
        AlertDialog.Builder alert = new AlertDialog.Builder(QuizActivity.this, R.style.DialogStyle);
        alert.setTitle("Você Errou!");
        alert.setIcon(R.drawable.ic_sentiment_dissatisfied_black_24dp);
        alert.setMessage("Infelizmente você errou, tente novamente ;)" );
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                DatabaseReference db = reference.child("usuario");
                db.child(Base64.encodeToString(user.getEmail().getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "")).setValue(user);
            }
        });
        alert.create();
        alert.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
