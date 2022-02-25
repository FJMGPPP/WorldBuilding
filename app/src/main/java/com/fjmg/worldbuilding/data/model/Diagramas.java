package com.fjmg.worldbuilding.data.model;

import java.io.Serializable;

public class Diagramas implements Serializable
{
    static int id;
    String titulo;
    int tipo;
    public  Diagramas()
    {
        titulo = "Diagrama" + id;
        tipo = DiagramasTipo.JERARQUICO;
        id++;
    }
    public  Diagramas(String Titulo)
    {
        this.titulo = Titulo;
        tipo = DiagramasTipo.JERARQUICO;
        id++;
    }
    public  Diagramas(String Titulo,int Tipo)
    {
        this.titulo = Titulo;
        tipo = Tipo;
        id++;
    }

    public static int getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}

