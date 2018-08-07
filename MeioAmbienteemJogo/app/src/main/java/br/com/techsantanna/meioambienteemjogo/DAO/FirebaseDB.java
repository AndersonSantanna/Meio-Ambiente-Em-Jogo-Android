package br.com.techsantanna.meioambienteemjogo.DAO;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.techsantanna.meioambienteemjogo.model.Usuario;

public class FirebaseDB {
    public Usuario usuario = new Usuario();
    private DatabaseReference database;

    public FirebaseDB() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    public Usuario recuperaDados(String id){
        database.child("Usuario").child(id);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.getValue(Usuario.class);
                Log.i("testeDentro", usuario.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        database.addValueEventListener(listener);
        return usuario;
    }
}
