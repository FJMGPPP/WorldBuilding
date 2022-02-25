package com.fjmg.worldbuilding.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
@Entity
public class Categoria implements Serializable , Comparable<Categoria>
{
    @PrimaryKey(autoGenerate = true)
    private  long id;
    private long idUniverso;
    private long idPadre;
    private String titulo;
    @Ignore
    private static int cantidad = 0;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public long getIdUniverso() {
        return idUniverso;
    }

    public void setIdUniverso(long idUniverso) {
        this.idUniverso = idUniverso;
    }
    @Ignore
    public Categoria(long idUniverso,String titulo)
    {
        this.idUniverso = idUniverso;
        this.titulo = titulo;
        this.idPadre = -1;
    }
    @Ignore
    public Categoria(long idUniverso,String titulo,long idPadre)
    {
        id = -1;
        this.idUniverso = idUniverso;
        this.titulo = titulo;
        this.idPadre = idPadre;
    }

    public Categoria(long id, long idUniverso, long idPadre, String titulo) {
        this.id = id;
        this.idUniverso = idUniverso;
        this.idPadre = idPadre;
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(Categoria categoria) {
        if(categoria.titulo.compareTo(this.titulo) == 0)
        {
            if (categoria.id > id)
            {
                return  1;
            } else
                {
                    return  -1;
                }
        }
        return categoria.titulo.compareTo(titulo);
    }

    public long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static void setCantidad(int cantidad) {
        Categoria.cantidad = cantidad;
    }

}
