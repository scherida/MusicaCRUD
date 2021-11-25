package com.example.musica.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.musica.entity.Musica;

import java.util.List;

@Dao
public interface MusicaDAO {

    @Insert
    void salvarMusica(Musica musica);

    @Update
    void editarMusica(Musica musica);

    @Delete
    void excluirMusica(Musica musica);

    @Query("SELECT * FROM musicas_tb")
    List<Musica> buscarTodos();

}
