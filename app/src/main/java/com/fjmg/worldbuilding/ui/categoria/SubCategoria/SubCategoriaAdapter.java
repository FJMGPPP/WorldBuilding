package com.fjmg.worldbuilding.ui.categoria.SubCategoria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.Categoria;

import java.util.ArrayList;

public class SubCategoriaAdapter extends RecyclerView.Adapter<SubCategoriaAdapter.ViewHolder> implements MvpSubCategorias.Adapter {

    private ArrayList<Categoria> list;
    Fragment fragmentoOrigen;
    public SubCategoriaAdapter(Fragment fragmentoOrigen, ArrayList<Categoria> categorias)
    {
        this.list = null;
        this.fragmentoOrigen = fragmentoOrigen;
        this.list = categorias;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_categoria_default, parent, false);
        return  new ViewHolder(view);
    }

    /**
     * Se llama tanta veces elemento se vizualicen en pantalla / elementos del arrayList del
     * dispostivo movil siempre y cuando getItemCount > 0
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.Nombre.setText(list.get(position).getTitulo());
        holder.itemView.setOnClickListener( view ->
        {
            NavController nvController =  NavHostFragment.findNavController(fragmentoOrigen);
            Bundle data = new Bundle();
            data.putSerializable("Categoria", list.get(position));
            nvController.navigate(R.id.action_categoriaFragment_self,data);
        });
        holder.itemView.setOnLongClickListener(view -> {
           MvpSubCategorias.View vista =  (MvpSubCategorias.View)fragmentoOrigen;
            vista.delete(list.get(position).getId());
            return true;
        });
    }

    @Override
    public void update(ArrayList categorias) {
        list.addAll(categorias);
        this.notifyDataSetChanged();
    }
    @Override
    public void update(Categoria categoria) {
        list.add(categoria);
        notifyDataSetChanged();
    }

    @Override
    public void delete(long id) {
        Categoria delete = null;
        for (int i = 0;  i < list.size() ; i++)
        {
            if(list.get(i).getId() == id)
            {
                delete = list.get(i);
            }
        }
        list.remove(delete);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void order() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Nombre = itemView.findViewById(R.id.lblNombreCategoria);
        }

    }

}
