package com.example.musica.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musica.database.dao.MusicaDAO;
import com.example.musica.entity.Musica;

@Database(entities = {Musica.class}, version = 1, exportSchema = false)
public abstract class MusicasDB extends RoomDatabase {

    private static MusicasDB instance;

    public static MusicasDB getInstance(Context context)
    {
        if (instance == null)
        {
            instance =  Room.databaseBuilder(context, MusicasDB.class, "musicas_db")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract MusicaDAO getMusicaDAO();

}
