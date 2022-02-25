package com.fjmg.worldbuilding.ui.categoria;

import com.fjmg.worldbuilding.data.model.Categoria;

import java.util.ArrayList;

//Debido a que no necesito validacion ninguna he saltado la interfaz interactor
public interface MvpCategoriaUpdate
{
    interface View extends Acciones , Callback
    {

    }
    interface Presenter extends Acciones , Callback
    {

    }
    interface Interactor extends Acciones , Callback
    {
        void verifyName(String name,Categoria categoria);
    }
    interface Repository extends Acciones , Callback
    {

    }
    interface Callback
    {
       void onUpdateNameSucess(String name);
       void onUpdateNameFail(String msg);
    }
    interface Acciones
    {
        void update(String name,Categoria categoria);
    }
}


