package br.com.techsantanna.meioambienteemjogo.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.techsantanna.meioambienteemjogo.R;
import br.com.techsantanna.meioambienteemjogo.model.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private Button button;
    private EditText nome, email, senha, nomeArvore, idade;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private RadioGroup radioGroup;
    private String sexo = "Indefinido";
    private ProgressBar bar;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        /**
         * Recuperando id
         * */
        nome = findViewById(R.id.editTextNome);
        email = findViewById(R.id.editTextCEmail);
        senha = findViewById(R.id.editTextCPass);
        idade = findViewById(R.id.editTextIdade);
        nomeArvore = findViewById(R.id.editTextNArvore);
        radioGroup = findViewById(R.id.radioGroup);
        bar = findViewById(R.id.progressBar3);

        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                //Pega valores do RadioGroup
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.radioButton3){
                            sexo = "Masculino";
                        }else if (checkedId == R.id.radioButton4){
                            sexo = "Feminino";
                        }
                    }
                });

                //Autentifica email  de usuario
                auth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString()).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(vereficaCampos(nome, email, senha, idade, nomeArvore)) {
                            if (task.isSuccessful()) {
                                /**
                                 * Passando dados do usuario para o database
                                 * */
                                DatabaseReference usario = reference.child("usuario");
                                usario.push().setValue(new Usuario(nome.getText().toString(), sexo, email.getText().toString(), senha.getText().toString(), Integer.parseInt(idade.getText().toString()), nomeArvore.getText().toString()));
                                Toast.makeText(getApplicationContext(), "Cadastrado!", Toast.LENGTH_LONG).show();
                                bar.setVisibility(View.GONE);
                                finish();
                            } else if (senha.getText().length() < 6) {
                                //Precaução Caso usuario digite senha com menos de 6 caractere
                                AlertDialog.Builder alert = new AlertDialog.Builder(CadastroActivity.this);
                                alert.setMessage("Por favor digite uma senha de 6 digitos!");
                                alert.setIcon(R.drawable.ic_warning_black_24dp);
                                alert.setTitle("Aviso!");
                                alert.show();
                                bar.setVisibility(View.GONE);
                            } else {
                                //aviso Quando email ja esta sendo usado
                                AlertDialog.Builder alert = new AlertDialog.Builder(CadastroActivity.this);
                                alert.setMessage("Por favor digite outro email, pois esse já esta sendo utilizado!");
                                alert.setIcon(R.drawable.ic_warning_black_24dp);
                                alert.setTitle("Aviso!");
                                alert.show();
                                bar.setVisibility(View.GONE);

                            }
                        }else {
                            AlertDialog.Builder alert = new AlertDialog.Builder(CadastroActivity.this);
                            alert.setMessage("Por favor preencha todos os campos!");
                            alert.setIcon(R.drawable.ic_error_outline_black_24dp);
                            alert.setTitle("Atenção!");
                            alert.show();
                            bar.setVisibility(View.GONE);

                        }
                    }
                });

            }
        });


    }

    public boolean vereficaCampos(EditText campo1, EditText campo2,EditText campo3,EditText campo4,EditText campo5 ){
        if (!campo1.getText().toString().isEmpty() && !campo2.getText().toString().isEmpty() && !campo3.getText().toString().isEmpty() &&
                !campo4.getText().toString().isEmpty() && !campo5.getText().toString().isEmpty()){
            return true;
        }else {
            return false;
        }


    }

}
