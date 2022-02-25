package com.fjmg.worldbuilding.data.model;

import android.view.View;
import android.view.ViewGroup;

import com.fjmg.worldbuilding.data.propiedadesView.ViewsPropertys;

import java.io.Serializable;
import java.util.ArrayList;

public class Propiedad implements Serializable
{
    int id;
    static int cantidad = 0;
    ViewsPropertys ViewAnfritrion;
    ArrayList<String> args;
    public Propiedad(ViewsPropertys ViewAnfritrion)
    {
        this.ViewAnfritrion = ViewAnfritrion;
        cantidad++;
        id = cantidad;
    }

    public ViewsPropertys getViewAnfritrion() {
        return ViewAnfritrion;
    }

    public void setViewAnfritrion(ViewsPropertys viewAnfritrion) {
        ViewAnfritrion = viewAnfritrion;
    }

}
