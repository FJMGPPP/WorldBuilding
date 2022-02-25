package com.fjmg.worldbuilding.ui.universo.bigUniverso;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import androidx.navigation.fragment.NavHostFragment;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.data.model.User;
import com.fjmg.worldbuilding.databinding.FragmentUniversoBigBinding;
import com.fjmg.worldbuilding.ui.MainActivity;
import com.fjmg.worldbuilding.utils.MediaLoader;
import com.fjmg.worldbuilding.utils.Utils;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;

import java.io.File;
import java.util.ArrayList;


public class UniversoBigSelectFragment extends Fragment implements MvpUniversoContract.View {

    String copia;
    FragmentUniversoBigBinding binding;
    private MvpUniversoContract.Presenter presenter;
    private  Universo universo;
    @Override
    public void onCreateOptionsMenu(Menu menu , @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_universo, menu);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                    Delete(universo.getId());
                NavHostFragment.findNavController(this).popBackStack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentUniversoBigBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        Bundle args= ((MainActivity)getActivity()).getBundle();
        universo = (Universo)args.getSerializable(Universo.TAG);
        presenter = new UniversoPresenter(this);

        binding.tituloUniverso.setText(universo.getTitulo());
        binding.descripcionUniverso.setText(universo.getDescripcion());
        try {
            universo.setImageView(binding.portadaUniversos);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        binding.portadaUniversos.setOnClickListener(view1 ->
        {
            Album.initialize(AlbumConfig.newBuilder(getContext()).setAlbumLoader(new MediaLoader()).build());

            Album.image(getContext())
                    .singleChoice().afterFilterVisibility(true)

                    .onResult(result -> {
                            Toast.makeText(getContext(),result.get(0)+"",Toast.LENGTH_LONG).show();
                            binding.portadaUniversos.setImageURI(Uri.parse(result.get(0).getPath()));
                            ActualizarImagen(Utils.DrawdeableEncode(binding.portadaUniversos.getDrawable()),universo.getId());
                    })
                    .onCancel(result -> Toast.makeText(getContext(),"Se cancelo la actualizacion",Toast.LENGTH_LONG).show())
                    .start();


        });
        binding.button2.setOnClickListener(view1 -> {
            Toast.makeText(getContext(),universo.getId()+"",Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putLong(universo.ID,universo.getId());
            NavHostFragment.findNavController(this).navigate(R.id.action_universosBigSelectFragment_to_mainFragment,bundle);
        });

        binding.tituloUniverso.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        String titulo = binding.tituloUniverso.getText().toString().split("\n")[0];
                        Toast.makeText(getContext(),titulo,Toast.LENGTH_SHORT).show();
                        binding.tituloUniverso.setText(titulo);
                        ActualizarNombre(binding.tituloUniverso.getText().toString(),universo.getId());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                return false;
            }
        });
        binding.tituloUniverso.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() > 4) {
                    copia = charSequence.toString();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        binding.descripcionUniverso.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        ActualizarDescripcion(binding.descripcionUniverso.getText().toString(),universo.getId());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                return false;
            }
        });

    }


    @Override
    public void setInvalidName(String error)
    {
        binding.tituloUniverso.setText(copia);
    }

    @Override
    public void setInvalidDescription(String error) {
        binding.descripcionUniverso.setText(copia);
    }

    @Override
    public void setInvalidImagen(String error) throws Exception {
         universo.setImageView(binding.portadaUniversos);
    }

    @Override
    public void Success(Universo universo) throws Exception {
        Toast.makeText(getActivity().getApplicationContext(),"Se actualizo",Toast.LENGTH_SHORT).show();
        binding.tituloUniverso.setText(universo.getTitulo());
        binding.descripcionUniverso.setText(universo.getDescripcion());
        universo.setImageView(binding.portadaUniversos);
    }

    @Override
    public void fail(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ActualizarNombre(String nombre,long id) throws Exception {
        presenter.ActualizarNombre(nombre,id);
    }

    @Override
    public void ActualizarDescripcion(String descripcion , long id) throws Exception {
        presenter.ActualizarDescripcion(descripcion,id);
    }

    @Override
    public void ActualizarImagen(String Base64,long id) {
        presenter.ActualizarImagen(Base64,id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void Delete(long id) {
        presenter.Delete(id);
        Utils.EnviarNotificacion(getContext(),getActivity(),"WorldBuilding","Se elimino una dependencia");
    }

}