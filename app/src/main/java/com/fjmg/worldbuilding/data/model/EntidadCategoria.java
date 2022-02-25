package com.fjmg.worldbuilding.data.model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class EntidadCategoria implements Serializable, Comparable<EntidadCategoria>
{
    private  int id;
    private  static int cantidad = 0;
    String nombre;
    Drawable icon;
    Categoria padre;

    public EntidadCategoria(String nombre, Categoria padre) {
        this.nombre = nombre;
        this.padre = padre;
        cantidad++;
        id = cantidad;
    }
    public EntidadCategoria(String nombre, Drawable icon, Categoria padre) {
        this.nombre = nombre;
        this.icon = icon;
        this.padre = padre;
        cantidad++;
        id = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Drawable getIcon() {
        return icon;
    }

    public Categoria getPadre() {
        return padre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setPadre(Categoria padre) {
        this.padre = padre;
    }

    @Override
    public int compareTo(EntidadCategoria entidadCategoria) {
        if(entidadCategoria.nombre.compareTo(this.nombre) == 0)
        {
            if (entidadCategoria.id > id)
            {
                return  1;
            } else
            {
                return  -1;
            }
        }
        return entidadCategoria.nombre.compareTo(nombre);
    }

}
