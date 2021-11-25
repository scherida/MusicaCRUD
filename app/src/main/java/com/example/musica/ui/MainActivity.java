package com.example.musica.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.musica.R;
import com.example.musica.adapter.MusicaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMusicas;
    FloatingActionButton fabAdd;
    MusicaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMusicas = findViewById(R.id.rvMusicas);
        fabAdd  = findViewById(R.id.fabAdd);

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new MusicaAdapter(this);

        rvMusicas.setLayoutManager(layout);
        rvMusicas.setAdapter(adapter);

        fabAdd.setOnClickListener(v->{ startActivity(new Intent(this, FormActivity.class));} );

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateDataSet();
    }
}