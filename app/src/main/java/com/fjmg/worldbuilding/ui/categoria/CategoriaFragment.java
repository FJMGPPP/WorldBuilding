package com.fjmg.worldbuilding.ui.categoria;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.base.BaseDialogFragment;
import com.fjmg.worldbuilding.data.model.Categoria;
import com.fjmg.worldbuilding.databinding.FragmentCategoriaBinding;
import com.fjmg.worldbuilding.ui.MainActivity;
import com.fjmg.worldbuilding.ui.categoria.SubCategoria.MvpSubCategorias;
import com.fjmg.worldbuilding.ui.categoria.SubCategoria.SubCategoriaAdapter;
import com.fjmg.worldbuilding.ui.categoria.SubCategoria.SubCategoriaPresenter;
import com.fjmg.worldbuilding.utils.Utils;

import java.util.ArrayList;

public class CategoriaFragment extends Fragment implements MvpSubCategorias.View , MvpCategoriaUpdate.View
{
        FragmentCategoriaBinding binding;
        private SubCategoriaAdapter subCategoriaAdapter;
        private MvpSubCategorias.Presenter presenter;
        private MvpCategoriaUpdate.Presenter presenterUpdate;
        Categoria principal;
    @Override
    public void onCreateOptionsMenu(Menu menu , @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categoria, menu);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        presenter = new SubCategoriaPresenter(this);
        presenterUpdate = new CategoriaUpdatePresenter(this);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                //todo delete
                return true;
            case R.id.action_renombrar:
                Bundle bundle = new Bundle();
                bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_rename_categoria));
                bundle.putString(BaseDialogFragment.MESSAGE, getString(R.string.nombre_Categoria));
                NavHostFragment.findNavController(this).navigate(R.id.action_categoriaFragment_to_baseDialogFragment, bundle);
                getActivity().getSupportFragmentManager().setFragmentResultListener(BaseDialogFragment.REQUEST, this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        //Si la respuesta es true en deletedDependency se procede con el caso de uso DELETE
                        if (bundle.getBoolean(BaseDialogFragment.KEY_BUNDLE))
                        {
                            //@todo ActualizarNombre
                            String nombre =  bundle.getString("nombre");
                            update(nombre,principal);
                        }
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            binding = FragmentCategoriaBinding.inflate(inflater, container, false);
            return binding.getRoot();
        }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
        {
            super.onViewCreated(view, savedInstanceState);
            initRvSubCategorias(new ArrayList<>());
        }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args= ((MainActivity)getActivity()).getBundle();
        principal = (Categoria) args.getSerializable("Categoria");
        binding.lblTituloCategoriaFragment.setText(principal.getTitulo() + " Existentes ");
        presenter.loadSubCategoria(principal.getIdUniverso(), principal.getId());
    }

    private void initRvSubCategorias(ArrayList<Categoria> categorias)
    {
        //1. Iniciar adaptadr
        subCategoriaAdapter = new SubCategoriaAdapter(this,categorias);
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.RvSubCategoriaView.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.RvSubCategoriaView.setAdapter(subCategoriaAdapter);
    }

    @Override
    public void subCategoriaLoadSucess(ArrayList<Categoria> categorias) {
        subCategoriaAdapter.update(categorias);
    }

    @Override
    public void subCategoriaAddSuccess(Categoria categoria) {
        subCategoriaAdapter.update(categoria);
    }

    @Override
    public void subCategoriaDeleteSucess(long id) {
        subCategoriaAdapter.delete(id);
    }

    @Override
    public void loadSubCategoria(long idUniverso, long idCategoriaPadre)
    {
        presenter.loadSubCategoria(
                idUniverso,
                idCategoriaPadre
        );

    }

    @Override
    public void add(Categoria categoria) {
        presenter.add(categoria);
    }

    @Override
    public void delete(long id) {
        presenter.delete(id);
    }


    @Override
    public void onUpdateNameSucess(String name) {
        binding.lblTituloCategoriaFragment.setText(name + " Existentes");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onUpdateNameFail(String msg) {
        Utils.EnviarNotificacion(getContext(),getActivity(),"WorldBuilding: Error","ingreso un nombre invalido");

    }

    @Override
    public void update(String name, Categoria categoria) {
        presenterUpdate.update(name,categoria);
    }
}
