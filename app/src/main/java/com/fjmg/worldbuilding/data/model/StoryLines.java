package com.fjmg.worldbuilding.data.model;

import java.io.Serializable;

public class StoryLines implements Comparable<StoryLines> , Serializable
{
    int id;
    static int cantidad = 0;
    String titulo;

    public  StoryLines()
    {
        titulo = "StoryLine" + id;
        cantidad++;
        id = cantidad;
    }
    public  StoryLines(String Titulo)
    {
        this.titulo = Titulo;
        cantidad++;
        id = cantidad;
    }

    public int getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int compareTo(StoryLines storyLines) {
        if(storyLines.titulo.compareTo(this.titulo) == 0)
        {
            if (storyLines.id > id)
            {
                return  1;
            } else
            {
                return  -1;
            }
        }
        return storyLines.titulo.compareTo(titulo);
    }
}
