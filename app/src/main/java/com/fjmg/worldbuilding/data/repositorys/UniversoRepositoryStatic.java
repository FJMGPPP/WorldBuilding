package com.fjmg.worldbuilding.data.repositorys;

import android.content.Context;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.data.model.Universo;
import com.fjmg.worldbuilding.ui.universo.bigUniverso.MvpUniversoContract;
import com.fjmg.worldbuilding.ui.universo.MvpUniversosContract;
import com.fjmg.worldbuilding.utils.Utils;

import java.util.ArrayList;

public class UniversoRepositoryStatic implements MvpUniversosContract.Repository  , MvpUniversoContract.Repository {
    static UniversoRepositoryStatic instancia;
    MvpUniversosContract.Interactor interactorList;
    MvpUniversoContract.Interactor interactorUpdate;
    ArrayList<Universo> universos;
    int ultimaId= 6;
    static  String UIDPrueba = "zLutQPNdaaUepI6Vd4BTcEUTePY2";

     static  private void initData()
    {
        if (instancia == null)
        {
            instancia = new UniversoRepositoryStatic();
            instancia.universos = new ArrayList<>();
            instancia.universos.add(new Universo(UIDPrueba,1,"Viaje Infinito","El viaje que nunca termina",Utils.IMAGEN_DEFAULT));
            instancia.universos.add(new Universo(UIDPrueba,2,"A por ellos","AH LUCHAR SE DIJO",Utils.IMAGEN_DEFAULT));
            instancia.universos.add(new Universo(UIDPrueba,3,"Viaje Infinito2","El viaje que nunca termina 2 parte",Utils.IMAGEN_DEFAULT));
            instancia.universos.add(new Universo(UIDPrueba,4,"Viaje Infinito3","El viaje que nunca termina 3 parte",Utils.IMAGEN_DEFAULT));
            instancia.universos.add(new Universo(UIDPrueba,5,"Viaje Infinito4","El viaje que nunca termina 4 parte",Utils.IMAGEN_DEFAULT));
            instancia.universos.add(new Universo("Patata",6,"Viaje Infinito5","El viaje que nunca termina 5 parte",Utils.IMAGEN_DEFAULT));
        }
    }




    public static UniversoRepositoryStatic getInstance(MvpUniversoContract.Interactor interactor)
    {
        initData();
        instancia.interactorUpdate = interactor;
        return  instancia;
    }
    public static UniversoRepositoryStatic getInstance(MvpUniversosContract.Interactor interactor)
    {
        initData();
        instancia.interactorList = interactor;
        return  instancia;
    }

    @Override
    public void load(String uid)
    {
        ArrayList<Universo> result = new ArrayList<>();
        int size = instancia.universos.size();
        for (int i = 0; i < size ; i++) {
            if (instancia.universos.get(i).getUidUsuario().equals(uid))
            {
                result.add(instancia.universos.get(i));
            }
        }

        interactorList.onSuccessLoad(result);
    }

    @Override
    public void agregar(Universo nuevo) {
        instancia.universos.add(nuevo);
        ultimaId++;
        nuevo.setId(ultimaId);
    }


    @Override
    public void ActualizarNombre(String nombre,long id) throws Exception {
        int size = instancia.universos.size();
        for (int i = 0; i < size ; i++) {
            if (instancia.universos.get(i).getId() == id)
            {
                instancia.universos.get(i).setTitulo(nombre);
                interactorUpdate.Success(instancia.universos.get(i));
                return;
            }
        }

    }

    @Override
    public void ActualizarDescripcion(String descripcion,long id) throws Exception {
        int size = instancia.universos.size();
        for (int i = 0; i < size ; i++) {
            if (instancia.universos.get(i).getId() == id)
            {
                instancia.universos.get(i).setDescripcion(descripcion);
                interactorUpdate.Success(instancia.universos.get(i));
                return;
            }
        }
    }

    @Override
    public void ActualizarImagen(String Base64,long id) {
        for ( Universo universo: universos)
        {
            if (universo.getId() == id) {
                universo.setImagenBitmap(Utils.BitmapDecode(Base64));
                universo.tieneImagen = true;
                break;
            }
        }
    }

    @Override
    public void Delete(long id) {
            int size = instancia.universos.size();
            for (int i = 0; i < size ; i++) {
                if (instancia.universos.get(i).getId() == id)
                {
                    instancia.universos.remove(i);
                    return;
                }
            }

    }
}
