package com.fjmg.worldbuilding.data.propiedadesView;

import android.content.Context;
import android.view.LayoutInflater;

import com.fjmg.worldbuilding.databinding.ViewTituloBinding;

import java.util.ArrayList;


public class TituloView extends ViewsPropertys
{

    ViewTituloBinding binding;
    public TituloView(Context context,ArrayList<String> args)
    {
        super(context,args,"TituloView");
        Init();
    }

    @Override
    boolean isIniciado() {
        return iniciado;
    }

    void Init()
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = ViewTituloBinding.inflate(inflater);
        if (args.get(0) == null)
        {
            iniciado = false;
        }
        else
            {
                iniciado = true;
                binding.ViewTituloPrincipal.setText(args.get(0));
            }
    }

}
