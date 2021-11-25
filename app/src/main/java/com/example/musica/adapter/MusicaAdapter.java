package com.example.musica.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musica.R;
import com.example.musica.database.MusicasDB;
import com.example.musica.database.dao.MusicaDAO;
import com.example.musica.entity.Musica;
import com.example.musica.ui.FormActivity;

import java.util.List;

public class MusicaAdapter extends RecyclerView.Adapter {

    Context context;
    List<Musica> listaMusicas;
    MusicaDAO dao;

    public MusicaAdapter(Context context) {
        this.context = context;
        dao = MusicasDB.getInstance(context).getMusicaDAO();
        listaMusicas = dao.buscarTodos();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.musica_layout, parent, false);

        MusicaViewHolder jvh = new MusicaViewHolder(itemView);

        return jvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Musica j = listaMusicas.get(position);

        MusicaViewHolder jvh = (MusicaViewHolder) holder;

        jvh.tvTitulo.setText(j.getTitulo());
        jvh.tvGenero.setText("Gênero: " + j.getGenero());
        jvh.tvAno.setText("(Lançamento: " + j.getAno() + ")");

        jvh.ibExcluir.setOnClickListener(v->{

            new AlertDialog.Builder(context)
                    .setTitle("Excluir")
                    .setMessage("Tem certeza que deseja excluir esta música?")
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton("Sim, exclua!", (dialogInterface, i) -> {
                        dao.excluirMusica(j);
                        Toast.makeText(context, "Música excluída!", Toast.LENGTH_SHORT).show();
                        updateDataSet();
                    })
                    .show();


        });

        jvh.ibEditar.setOnClickListener(v->{

            Intent editarIntent = new Intent(context, FormActivity.class);
            editarIntent.putExtra("musica", j);
            context.startActivity(editarIntent);

        });

    }

    @Override
    public int getItemCount() {
        return listaMusicas.size();
    }

    public void updateDataSet(){
        listaMusicas.clear();
        listaMusicas = dao.buscarTodos();
        notifyDataSetChanged();
    }
}
