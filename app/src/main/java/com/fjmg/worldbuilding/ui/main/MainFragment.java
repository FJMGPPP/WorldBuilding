package com.fjmg.worldbuilding.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.databinding.FragmentMainBinding;
import com.fjmg.worldbuilding.ui.Diagramas.DiagramasAdapter;
import com.fjmg.worldbuilding.ui.main.categoria.CategoriaAdapter;
import com.fjmg.worldbuilding.ui.main.categoria.CategoriaPresenter;
import com.fjmg.worldbuilding.ui.main.categoria.MvpCategorias;
import com.fjmg.worldbuilding.ui.storyLines.StoryLinesAdapter;
import com.fjmg.worldbuilding.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;

public class MainFragment extends Fragment implements MvpCategorias.View{
    FragmentMainBinding binding;
    private CategoriaAdapter adapterCategoria;
    private DiagramasAdapter adapterDiagram;
    private StoryLinesAdapter adapterStoryline;
    private MvpCategorias.Presenter presenter;
    private long universoId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu , @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_principal, menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preferencias:
                NavHostFragment.findNavController(this).navigate(R.id.action_mainFragment_to_preferenceFragment);
                return true;
            case R.id.action_order:
                adapterCategoria.order();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        universoId = getArguments().getLong(Universo.ID);
        presenter = new CategoriaPresenter(this);
        initRvCategoriaMain();
        initRvDiagramasMain();
        initRvStoryLinesMain();
        binding.AddCategoriaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                    Categoria nueva = new Categoria(universoId,"editar");
                    add(nueva);
            }
        });
    }

    private void initRvCategoriaMain()
    {
        //1. Iniciar adaptadr
        adapterCategoria = new CategoriaAdapter(this,new ArrayList<Categoria>());
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 4, RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.RvCriaturasMain.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.RvCriaturasMain.setAdapter(adapterCategoria);
    }
    private void initRvDiagramasMain()
    {
        //1. Iniciar adaptadr
        adapterDiagram = new DiagramasAdapter(this);
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.RvDiagramasMain.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.RvDiagramasMain.setAdapter(adapterDiagram);
    }
    private void initRvStoryLinesMain()
    {
        //1. Iniciar adaptadr
        adapterStoryline = new StoryLinesAdapter(this);
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.RvStoryLinesMain.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.RvStoryLinesMain.setAdapter(adapterStoryline);
    }

    @Override
    public void loadSuccess(ArrayList<Categoria> categorias) {
        adapterCategoria.update(categorias);
    }

    @Override
    public void addSuccess(Categoria categoria) {
        adapterCategoria.update(categoria);
        Bundle data = new Bundle();
        data.putSerializable("Categoria", (Serializable) categoria);
        NavHostFragment.findNavController(this).navigate(R.id.action_to_Categoria,data);
    }

    @Override
    public void deleteSucess(long id) {
        adapterCategoria.delete(id);
    }

    @Override
    public void load(long id) {
        presenter.load(id);
    }

    @Override
    public void add(Categoria categoria ) {
        presenter.add(categoria);
    }

    @Override
    public void delete(long id) {
        presenter.delete(id);
    }

    @Override
    public void onStart() {
        super.onStart();
        load(universoId);
    }
}
