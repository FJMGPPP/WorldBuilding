package com.fjmg.worldbuilding.ui.Diagramas;

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
import com.fjmg.worldbuilding.data.model.Diagramas;

import java.util.ArrayList;

public class DiagramasAdapter extends RecyclerView.Adapter<DiagramasAdapter.ViewHolder> {

    //Para usar la view de clase o la mia personalizada
    private ArrayList<Diagramas> list;
    Fragment fragmentoOrigen;
    public DiagramasAdapter(Fragment fragmentoOrigen)
    {
        this.fragmentoOrigen = fragmentoOrigen;
        this.list = new ArrayList<Diagramas>();
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());
        list.add(new Diagramas());

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_diagrama, parent, false);
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Nombre = itemView.findViewById(R.id.lblTituloStoryLinesView);
            itemView.setOnClickListener( view ->{
                NavController nvController =  NavHostFragment.findNavController(fragmentoOrigen);
                nvController.navigate(R.id.action_mainFragment_to_fragmentDiagram);
            });
        }

    }
}
