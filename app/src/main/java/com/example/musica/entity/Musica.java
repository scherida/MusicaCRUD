package com.example.musica.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "musicas_tb")
public class Musica implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String titulo;
    private int ano;
    private String genero;

    public Musica() {
    }

    @Ignore
    public Musica(int id, String titulo, int ano, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
