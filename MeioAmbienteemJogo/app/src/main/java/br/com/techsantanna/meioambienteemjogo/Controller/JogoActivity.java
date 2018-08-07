package br.com.techsantanna.meioambienteemjogo.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.com.techsantanna.meioambienteemjogo.R;
import br.com.techsantanna.meioambienteemjogo.model.Perguntas;
import br.com.techsantanna.meioambienteemjogo.model.Usuario;

public class JogoActivity extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private Button regar, adubar, detetizar, podar, quiz, save;
    private Usuario usuario = new Usuario();
    private TextView txtRegar, txtPodar, txtDetetizar, txtAdubar, nivel, porcentagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_jogo);
        /**
         * Encontrando Id's de imagem, botões e texto
         * */
        imageView = findViewById(R.id.imageViewArvore);
        progressBar = findViewById(R.id.progressBar);
        regar = findViewById(R.id.buttonRegar);
        adubar = findViewById(R.id.buttonAdubar);
        detetizar = findViewById(R.id.buttonDetetizar);
        podar = findViewById(R.id.buttonPodar);
        txtAdubar = findViewById(R.id.textViewAdubar);
        txtPodar = findViewById(R.id.textViewPodar);
        txtRegar = findViewById(R.id.textViewRegar);
        txtDetetizar = findViewById(R.id.textViewDetetizar);
        nivel = findViewById(R.id.textViewNivel);
        porcentagem = findViewById(R.id.textViewPorcentagem);
        quiz = findViewById(R.id.buttonQuiz);
        save = findViewById(R.id.buttonSalvar);
        /**
         * Iniciando quantidade de ações
         * */
        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("user");
        txtRegar.setText(String.valueOf(usuario.arvore.getQtdRegar()).concat("x"));
        txtPodar.setText(String.valueOf(usuario.getArvore().getQtdPodar()).concat("x"));
        txtAdubar.setText(String.valueOf(usuario.getArvore().getQtdAdubar()).concat("x"));
        txtDetetizar.setText(String.valueOf(usuario.getArvore().getQtdAntiPragas()).concat("x"));
        progressBar.setProgress(usuario.getArvore().getXp());
        porcentagem.setText(String.valueOf(progressBar.getProgress()).concat("%"));
        imageView.getLayoutParams().width = usuario.getArvore().getWidth();
        imageView.getLayoutParams().height = usuario.getArvore().getHeight();
        nivel.setText( String.valueOf(usuario.getArvore().getNivel()));

        /**
         * ações do button regar
         * */
        regar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = aumentarArvore(usuario.arvore.regar(progressBar, porcentagem), usuario);
                txtRegar.setText(String.valueOf(usuario.arvore.getQtdRegar()).concat("x"));

            }
        });
        /**
         * ações do button adubar
         * */
        adubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = aumentarArvore(usuario.getArvore().adubar(progressBar, porcentagem), usuario);
                txtAdubar.setText(String.valueOf(usuario.getArvore().getQtdAdubar()).concat("x"));
            }
        });
        /**
         * ações do button podar
         * */
        podar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = aumentarArvore(usuario.getArvore().podar(progressBar, porcentagem), usuario);
                txtPodar.setText(String.valueOf(usuario.getArvore().getQtdPodar()).concat("x"));

            }
        });
        /**
         * ações do button detetizar
         * */
        detetizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = aumentarArvore(usuario.getArvore().detetizar(progressBar, porcentagem), usuario);
                txtDetetizar.setText(String.valueOf(usuario.getArvore().getQtdAntiPragas()).concat("x"));
            }
        });

        /**
         * Quiz
         * */
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(JogoActivity.this, QuizActivity.class ));
                final Intent intent = new Intent(JogoActivity.this, QuizActivity.class);
                int aux = new Random().nextInt(11);
                final DatabaseReference databaseReference = referencia.child("Quiz").child(""+aux);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        intent.putExtra("pergunta",dataSnapshot.getValue(Perguntas.class) );
                        intent.putExtra("user", usuario);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        /**
         * Salvar
         * */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                //reference.child("usuario").child(Base64.encodeToString(usuario.getEmail().getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "")).setValue(usuario);
                DatabaseReference db = reference.child("usuario");
                Map<String, Object> map = new HashMap<>();
                map.put(Base64.encodeToString(usuario.getEmail().getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", ""), usuario);
                db.updateChildren(map);
                finish();
                Toast.makeText(getApplicationContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * Verificador para saber quando se pode aumentar a imageView da arvore
     * */
    public Usuario  aumentarArvore(boolean b, Usuario usuario){
        if(b){
            nivel.setText(String.valueOf(usuario.getArvore().getNivel()));
            imageView.getLayoutParams().height = imageView.getHeight() + 5;
            imageView.getLayoutParams().width = imageView.getWidth() + 5;
            imageView.requestLayout();
            usuario.getArvore().setWidth(usuario.getArvore().getWidth() + 5);
            usuario.getArvore().setHeight(usuario.getArvore().getHeight() + 5);
            return usuario;
        }else {
            return usuario;
        }
    }

    /**
     * Criação do menu
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jogo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobre:
                startActivity(new Intent(JogoActivity.this, SobreActivity.class));
                break;
            case R.id.sair:
                Toast.makeText(getApplicationContext(), "Saindo...", Toast.LENGTH_SHORT).show();
                auth.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
