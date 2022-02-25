package com.fjmg.worldbuilding.ui.storyLines;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.StoryLines;

import java.util.ArrayList;

public class StoryLinesAdapter extends RecyclerView.Adapter<StoryLinesAdapter.ViewHolder> {

    //Para usar la view de clase o la mia personalizada
    private ArrayList<StoryLines> list;
    Fragment fragmentoOrigen;
    public StoryLinesAdapter(Fragment fragmentoOrigen)
    {
        this.fragmentoOrigen = fragmentoOrigen;
        this.list = new ArrayList<StoryLines>();
        list.add(new StoryLines());
        list.add(new StoryLines());
        list.add(new StoryLines());
        list.add(new StoryLines());
        list.add(new StoryLines());
        list.add(new StoryLines());
        list.add(new StoryLines());

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_story_lines, parent, false);
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
            /*itemView.setOnClickListener( view ->{
                NavController nvController =  NavHostFragment.findNavController(fragmentoOrigen);
                Bundle data = new Bundle();
                data.putString("Titulo", Titulo.getText().toString());
                data.putString("Descripcion", Descripcion.getText().toString());
                nvController.navigate(R.id.action_SelectUniverso,data);
            });*/
        }

    }
}
