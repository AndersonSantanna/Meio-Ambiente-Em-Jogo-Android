package br.com.techsantanna.meioambienteemjogo.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.techsantanna.meioambienteemjogo.R;

public class JogoActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_jogo);

        imageView = findViewById(R.id.imageViewArvore);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.getLayoutParams().height = imageView.getHeight() + 5;
                imageView.getLayoutParams().width = imageView.getWidth() + 5;
                imageView.requestLayout();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jogo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sobre:
                startActivity(new Intent(getApplicationContext(), SobreActivity.class));
                break;
            case R.id.sair:
                finish();
                Toast.makeText(getApplicationContext(), "Saindo...", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        /*AlertDialog.Builder alert = new AlertDialog.Builder(JogoActivity.this);
        alert.setTitle("Deseja voltar ?");
        alert.setMessage("Isso sairá do seu login mas salvará sim seus dados. ");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Saindo...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alert.setNegativeButton("Não",null);
        alert.show();*/
        super.onBackPressed();
    }
}
