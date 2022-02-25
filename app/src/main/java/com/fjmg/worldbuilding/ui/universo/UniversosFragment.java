package com.fjmg.worldbuilding.ui.universo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.WorldBuildingApplication;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.databinding.FragmentUniversosBinding;
import com.fjmg.worldbuilding.ui.MainActivity;
import com.fjmg.worldbuilding.utils.Utils;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.ArrayList;
import java.util.Random;


public class UniversosFragment extends Fragment implements MvpUniversosContract.View {

    static String ORDENADO = "ordenado";
    String uid;
    FragmentUniversosBinding binding;
    MvpUniversosContract.Presenter presenter;
    private UniversosAdapter adapter;
    @Override
    public void onCreateOptionsMenu(Menu menu , @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_universos, menu);
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
            case R.id.action_order:
                adapter.order();
                Bundle paquete = new Bundle();
                paquete.putString(User.UID,uid);
                Utils.EnviarNotificacion(getContext(),getActivity(),paquete,"Worldbuilding","Se ordeno los universos");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentUniversosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     Este metodo inicia el componente recycle view
     **/
    private void initRvUniversos()
    {
        //1. Iniciar adaptadr
        adapter = new UniversosAdapter(this,new ArrayList());
        //2. Indicar el diseño tendra el layout;
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2,RecyclerView.VERTICAL,false);
        //3. Asigno el layauot al recycleview
        binding.RycleUniversos.setLayoutManager(linearLayoutManager);
        //4. Asignar datos;
        binding.RycleUniversos.setAdapter(adapter);
    }

    @Override
    public void showData(ArrayList universos)
    {
        adapter.update(universos);
    }


    @Override
    public <T> void onSuccessLoad(ArrayList<T> universos)
    {
        if (universos.size() > 0)
        {
            showData(universos);
        }
    }


    @Override
    public <T> void onFailLoad(ArrayList<T> universos)
    {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new UniversosPresenter(this);
        initRvUniversos();
    }

    @Override
    public void onStart() {
        super.onStart();
        uid =  getArguments().getString(User.UID);
        presenter.load(uid);
        binding.addButtonUniverso.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Universo nuevo = new Universo(uid,"CambiarTitulo","");
                NavController nvController =  NavHostFragment.findNavController(getParentFragment());
                Bundle data = ((MainActivity)getActivity()).getBundle();
                data.putSerializable(Universo.TAG,nuevo);
                presenter.agregar(nuevo);
                Bundle paquete = ((MainActivity)getActivity()).getBundle();
                paquete.putString(User.UID,uid);
                Utils.EnviarNotificacion(getContext(),getActivity(),paquete,"Worldbuilding","Se agrego un universo");
                nvController.navigate(R.id.action_SelectUniverso,data);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        presenter = null;
        super.onDestroy();
    }
}