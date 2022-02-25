package com.fjmg.worldbuilding.ui.main.categoria;

import com.fjmg.worldbuilding.data.model.Categoria;

import java.util.ArrayList;

//Debido a que no necesito validacion ninguna he saltado la interfaz interactor
public interface MvpCategorias
{
    interface View extends Acciones , Callback
    {


    }
    interface Presenter extends Acciones , Callback
    {

    }
    interface Repository extends Acciones , Callback
    {

    }
    interface Callback
    {
       void loadSuccess(ArrayList<Categoria> categorias);
       void addSuccess(Categoria categoria);
       void deleteSucess(long id);
    }
    interface Acciones
    {
        void load(long idUniverso);
        void add(Categoria categoria);
        void delete(long id);
    }
    interface Adapter
    {
        void update(ArrayList<Categoria> categorias);
        void update(Categoria categoria);
        void delete(long id);
        void order();
    }
}


