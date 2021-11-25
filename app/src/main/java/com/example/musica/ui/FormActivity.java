package com.example.musica.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.musica.R;
import com.example.musica.database.MusicasDB;
import com.example.musica.database.dao.MusicaDAO;
import com.example.musica.entity.Musica;

public class FormActivity extends AppCompatActivity {

    EditText etTitulo;
    EditText etGenero;
    EditText etAno;
    Button bSalvar;

    MusicaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitulo = findViewById(R.id.etTitulo);
        etGenero = findViewById(R.id.etGenero);
        etAno    = findViewById(R.id.etAno);
        bSalvar  = findViewById(R.id.bSalvar);

        dao = MusicasDB.getInstance(this).getMusicaDAO();

        Intent intent = getIntent();

        Musica edtMusica;

        if (intent.hasExtra("musica")){

            edtMusica = (Musica) intent.getSerializableExtra("musica");

            etTitulo.setText(edtMusica.getTitulo());
            etAno.setText(edtMusica.getAno() + "");
            etGenero.setText(edtMusica.getGenero());

        } else {
            edtMusica = null;
        }



        bSalvar.setOnClickListener(v->{

            if (etTitulo.getText().toString().isEmpty() ||
            etGenero.getText().toString().isEmpty() ||
            etAno.getText().toString().isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {

                Musica j = new Musica(
                        0,
                        etTitulo.getText().toString(),
                        Integer.parseInt(etAno.getText().toString()),
                        etGenero.getText().toString()
                );

                if (edtMusica == null)
                {
                    dao.salvarMusica(j);
                }
                else
                {
                    j.setId(edtMusica.getId());
                    dao.editarMusica(j);
                }

                Toast.makeText(this, "Música salva!", Toast.LENGTH_SHORT).show();

                finish();
            }

        });

    }
}