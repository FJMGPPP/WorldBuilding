package com.fjmg.worldbuilding.ui.universo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.ui.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

 class UniversosAdapter extends RecyclerView.Adapter<UniversosAdapter.ViewHolder> implements MvpUniversosContract.Adapter {


    //Para usar la view de clase o la mia personalizada
    private ArrayList<Universo> list;
    Fragment fragmentoOrigen;
    public UniversosAdapter(Fragment fragmentoOrigen,ArrayList<Universo> lista)
    {
        this.fragmentoOrigen = fragmentoOrigen;
        this.list = new ArrayList<Universo>();
        this.list.addAll(lista);
    }
    @NonNull
    @Override
    public UniversosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_universo, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.titulo.setText(list.get(position).getTitulo());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.descripcion.setEnabled(false);
        try {
            list.get(position).setImageView(holder.imagen);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void update(ArrayList universos) {
        list.addAll(universos);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void order() {
        Collections.sort(list, new Comparator<Universo>() {
            @Override
            public int compare(Universo universo, Universo t1) {
                return universo.getTitulo().compareTo(t1.getTitulo());
            }
        });
        Collections.sort(list, new Comparator<Universo>() {
            @Override
            public int compare(Universo universo, Universo t1) {
                return universo.getDescripcion().compareTo(t1.getDescripcion());
            }
        });
         notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView descripcion;
        Button boton;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            titulo = itemView.findViewById(R.id.TituloUniverso);
            descripcion = itemView.findViewById(R.id.DescripcionUniverso);
            boton = itemView.findViewById(R.id.btnEntrar);
            imagen = itemView.findViewById(R.id.portadaUniverso);

            itemView.setOnClickListener( view ->
            {
                NavegateToBigUniverso();
            });
            descripcion.setOnClickListener(view ->
            {
                NavegateToBigUniverso();
            });
            boton.setOnClickListener(view ->
            {
                NavegateToBigUniverso();
            });


        }
        public void NavegateToBigUniverso()
        {
            NavController nvController =  NavHostFragment.findNavController(fragmentoOrigen);
            Bundle data = ((MainActivity)fragmentoOrigen.getActivity()).getBundle();
            data.putSerializable(Universo.TAG,list.get(getAdapterPosition()));
            nvController.navigate(R.id.action_SelectUniverso,data);

        }

    }

}
