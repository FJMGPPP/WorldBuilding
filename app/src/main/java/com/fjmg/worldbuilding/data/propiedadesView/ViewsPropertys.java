package com.fjmg.worldbuilding.data.propiedadesView;

import android.content.Context;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ViewsPropertys extends View implements Serializable
{
     protected ArrayList<String> args;
     protected boolean iniciado;
     protected String tipo;

     public ViewsPropertys(Context context,ArrayList<String> args,String tipo) {
          super(context);
          this.args = args;
          this.tipo = tipo;
     }

     public String getTipo() {
          return tipo;
     }

     abstract boolean isIniciado();
     abstract  void Init();
}
