package br.com.techsantanna.meioambienteemjogo.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.techsantanna.meioambienteemjogo.R;

public class TelaLogin extends AppCompatActivity {
    private Button cadastrar, login;
    private EditText email, senha;
    private ProgressBar bar;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        /**
         * Pegando o id de cada elemento
         * */
        cadastrar = findViewById(R.id.button2);
        login = findViewById(R.id.button);
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextPass);
        bar = findViewById(R.id.progressBar2);

        //botao login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando a autentificação do email e senha se estao cadastrados
                bar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Logando..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), JogoActivity.class));
                            bar.setVisibility(View.GONE);
                        }else if (email.getText().toString().equals("") || senha.getText().toString().equals("")){
                            /**
                             * Vererifica se o usuario não digitou email e senha
                             * */
                            bar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Por favor, digite seu email e senha!", Toast.LENGTH_LONG).show();
                        }else {
                            bar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Usuario Inválido!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Caso de falha
                        Toast.makeText(getApplicationContext(), "Verefique se possuí internet e tente novamente!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        //Botao Cadastrar
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
            }
        });

    }

}
