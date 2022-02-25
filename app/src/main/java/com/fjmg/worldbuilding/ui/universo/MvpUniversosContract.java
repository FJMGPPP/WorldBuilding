package com.fjmg.worldbuilding.ui.universo;

import com.fjmg.worldbuilding.data.model.Universo;

import java.util.ArrayList;

/**
 * Esta interface se usa para el contrato MVP para listar los diferentes universos
 * En MvpUniversosContract se usa para Modificar Y Eliminar
 * En MVPButtonUniversoFloat se usa para agregar
 */
public interface MvpUniversosContract
{
    interface  View extends  CallBacks
    {
        void showData(ArrayList universos);
    }
    interface  Presenter extends CallBacks
    {
        void load(String uid);
        void agregar(Universo nuevo);
    }
    interface Interactor extends  CallBacks
    {
        void load(String uid);
        void agregar(Universo nuevo);
    }
    interface Repository
    {
        void load(String uid);
        void agregar(Universo nuevo);
    }
    interface CallBacks
    {
        <T> void onSuccessLoad(ArrayList<T> universos);
        <T> void onFailLoad(ArrayList<T> universos);
    }
    interface Adapter
    {
        void update(ArrayList universos);
        void order();
    }
}



